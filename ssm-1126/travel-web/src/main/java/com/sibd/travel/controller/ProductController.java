package com.sibd.travel.controller;

import com.github.pagehelper.PageInfo;
import com.sibd.travel.pojo.Product;
import com.sibd.travel.service.ProductService;
import com.sibd.travel.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

/**
 * @author lenovo
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @RequestMapping("findAll.do")
    @RolesAllowed("USER")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page,pageSize);
        PageInfo<Product> pageInfo=new PageInfo<>(products);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
    @RequestMapping("save.do")
    public String save(Product product){
        product.setId(UuidUtil.getId());
        productService.save(product);
        return "redirect:findAll.do";
    }
    @RequestMapping("delete.do")
    public String delete(String[] ids){
        productService.delete(ids);
        System.out.println(ids);
        return "redirect:findAll.do";
    }
    @RequestMapping("onStatus.do")
    public String onStatus(String[] ids){
        productService.onStatus(ids);
        return "redirect:findAll.do";
    }
    @RequestMapping("offStatus.do")
    public String offStatus(String[] ids){
        productService.offStatus(ids);
        return "redirect:findAll.do";
    }
}
