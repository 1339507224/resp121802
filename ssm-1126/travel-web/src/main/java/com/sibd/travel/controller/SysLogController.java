package com.sibd.travel.controller;

import com.sibd.travel.pojo.SysLog;
import com.sibd.travel.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lenovo
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;
    @RequestMapping("findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<SysLog> sysLogs=sysLogService.findAll();
        mv.setViewName("syslog-list");
        mv.addObject("sysLogs",sysLogs);
        return mv;
    }
}
