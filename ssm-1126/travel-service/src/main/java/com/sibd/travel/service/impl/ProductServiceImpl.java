package com.sibd.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.sibd.travel.mapper.OrdersMapper;
import com.sibd.travel.mapper.ProductMapper;
import com.sibd.travel.pojo.Product;
import com.sibd.travel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author lenovo
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Override
    public List<Product> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Product> products = productMapper.findAll();
        return products;
    }

    @Override
    public void save(Product product) {
        productMapper.save(product);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            ordersMapper.delete(id);
            productMapper.delete(id);
        }
    }

    @Override
    public void onStatus(String[] ids) {
        for (String id : ids) {
            Integer status = productMapper.findById(id).getProductStatus();
            if (status==0){
                productMapper.onStatus(id);
            }
        }
    }

    @Override
    public void offStatus(String[] ids) {
        for (String id : ids) {
            Integer status = productMapper.findById(id).getProductStatus();
            if (status==1){
                productMapper.offStatus(id);
            }
        }
    }
}
