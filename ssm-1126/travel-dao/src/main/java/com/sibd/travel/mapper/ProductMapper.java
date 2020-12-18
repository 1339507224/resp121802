package com.sibd.travel.mapper;

import com.sibd.travel.pojo.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductMapper {
    @Select("select * from product")
    List<Product> findAll();
    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
    @Select("select * from product where id =#{id}")
    Product findById(String id);
    @Delete("delete from product where id =#{id}")
    void delete(String id);
    @Update("update product set productStatus=1 where id =#{id} ")
    void onStatus(String id);
    @Update("update product set productStatus=0 where id =#{id} ")
    void offStatus(String id);
}
