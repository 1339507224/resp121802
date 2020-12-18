package com.sibd.travel.controller;

import com.sibd.travel.pojo.SysLog;
import com.sibd.travel.service.SysLogService;
import com.sibd.travel.util.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author lenovo
 */
@Component
@Aspect
public class SysLogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    // 访问时间
    private Date startTime;
    // 访问的类
    private Class executionClass;
    // 访问的方法
    private Method executionMethod;

    @Before("execution(* com.sibd.travel.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        //获取访问的时间
        startTime = new Date();
        //获取访问的类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法
        String methodName = jp.getSignature().getName();
        // 获取访问的方法的参数
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            // 无参数，只能获取无参数方法
            executionMethod = executionClass.getMethod(methodName);
        } else {
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            // 获取有参数方法
            executionMethod = executionClass.getMethod(methodName, classArgs);
        }
    }

    @After("execution(* com.sibd.travel.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        // 获取类上的@RequestMapping对象
        if (executionClass != SysLogController.class) {
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                // 获取方法上的@RequestMapping对象
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    // 它的值应该是类上的@RequestMapping的value+方法上的@RequestMapping的value
                    String url = "";
                    url = classAnnotation.value()[0] +"/"+ methodAnnotation.value()[0];
                    SysLog sysLog = new SysLog();
                    sysLog.setId(UuidUtil.getId());
                    long executionTime = System.currentTimeMillis() - startTime.getTime();
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setIp(request.getLocalAddr());
                    sysLog.setMethod("[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName());
                    sysLog.setUrl(url);
                    // sysLog.setUrl(request.getRequestURI());
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    sysLog.setUsername(user.getUsername());
                    sysLog.setVisitTime(startTime);
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
