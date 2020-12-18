package com.sibd.travel.service;

import com.sibd.travel.pojo.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findAll(Integer page,Integer pageSize);
    Orders findById(String id);

    void onStatus(String[] ids);

    void offStatus(String[] ids);
}
