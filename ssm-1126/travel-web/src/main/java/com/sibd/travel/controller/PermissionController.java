package com.sibd.travel.controller;

import com.github.pagehelper.PageInfo;
import com.sibd.travel.pojo.Permission;
import com.sibd.travel.pojo.Role;
import com.sibd.travel.pojo.Users;
import com.sibd.travel.service.PermissionService;
import com.sibd.travel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lenovo
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize){
        ModelAndView mv=new ModelAndView();
        List<Permission> permissions=permissionService.findAll(page,pageSize);
        PageInfo<Permission> pageInfo=new PageInfo<>(permissions);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(Users users){
        permissionService.save(users);
        return "redirect:findAll.do";
    }
}
