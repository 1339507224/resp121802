package com.sibd.travel.service;

import com.sibd.travel.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll(Integer page,Integer pageSize);
    void save(Product product);
    void delete(String[] id);

    void onStatus(String[] ids);

    void offStatus(String[] ids);
}
