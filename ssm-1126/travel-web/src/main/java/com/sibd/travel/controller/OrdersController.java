package com.sibd.travel.controller;

import com.github.pagehelper.PageInfo;
import com.sibd.travel.pojo.Orders;
import com.sibd.travel.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize){
        ModelAndView mv=new ModelAndView();
        List<Orders> orders=ordersService.findAll(page,pageSize);
        PageInfo<Orders> pageInfo=new PageInfo<>(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }
    @RequestMapping("findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        Orders orders=ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
    @RequestMapping("onStatus.do")
    public String onStatus(String[] ids){
        ordersService.onStatus(ids);
        return "redirect:findAll.do";
    }
    @RequestMapping("offStatus.do")
    public String offStatus(String[] ids){
        ordersService.offStatus(ids);
        return "redirect:findAll.do";
    }
}
