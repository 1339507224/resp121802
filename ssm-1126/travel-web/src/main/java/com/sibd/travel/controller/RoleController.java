package com.sibd.travel.controller;

import com.github.pagehelper.PageInfo;
import com.sibd.travel.pojo.Permission;
import com.sibd.travel.pojo.Role;
import com.sibd.travel.pojo.Users;
import com.sibd.travel.service.RoleService;
import com.sibd.travel.service.UserService;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize){
        ModelAndView mv=new ModelAndView();
        List<Role> roles=roleService.findAll(page,pageSize);
        PageInfo<Role> pageInfo=new PageInfo<>(roles);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(Users users){
        roleService.save(users);
        return "redirect:findAll.do";
    }

    @RequestMapping("findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id) {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions=roleService.findRoleByIdAndAllPermission(id);
        Role role=roleService.findById(id);
        mv.addObject("role", role);
        mv.addObject("permissionList", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("addPermissionToRole.do")
    public String addPermissionToRole(String[] ids,String roleId){
        roleService.addPermissionToRole(ids,roleId);
        return "redirect:findAll.do";
    }
    @RequestMapping("findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
}
