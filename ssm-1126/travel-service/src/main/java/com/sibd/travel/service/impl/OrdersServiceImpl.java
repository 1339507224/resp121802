package com.sibd.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.sibd.travel.mapper.OrdersMapper;
import com.sibd.travel.pojo.Orders;
import com.sibd.travel.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("orderService")
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Override
    public List<Orders> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Orders> orders = ordersMapper.findAll();
        return orders;
    }

    @Override
    public Orders findById(String id) {
        return  ordersMapper.findById(id);

    }

    @Override
    public void onStatus(String[] ids) {
        for (String id : ids) {
            Integer status = ordersMapper.findById(id).getOrderStatus();
            if (status==0){
                ordersMapper.onStatus(id);
            }
        }
    }

    @Override
    public void offStatus(String[] ids) {
        for (String id : ids) {
            Integer status = ordersMapper.findById(id).getOrderStatus();
            if (status==1){
                ordersMapper.offStatus(id);
            }
        }
    }
}
