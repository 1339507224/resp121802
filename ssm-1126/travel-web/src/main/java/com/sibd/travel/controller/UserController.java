package com.sibd.travel.controller;

import com.github.pagehelper.PageInfo;
import com.sibd.travel.pojo.Role;
import com.sibd.travel.pojo.Users;
import com.sibd.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lenovo
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize){
        ModelAndView mv=new ModelAndView();
        List<Users> users=userService.findAll(page,pageSize);
        PageInfo<Users> pageInfo=new PageInfo<>(users);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        Users users=userService.findById(id);
        mv.addObject("user",users);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(Users users){
        userService.save(users);
        return "redirect:findAll.do";
    }

    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv=new ModelAndView();
        List<Role> roles=userService.findUserByIdAndAllRole(id);
        Users users = userService.findById(id);
        mv.addObject("user",users);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(String[] ids,String userId){
        userService.addRoleToUser(ids,userId);
        return "redirect:findAll.do";
    }
}
