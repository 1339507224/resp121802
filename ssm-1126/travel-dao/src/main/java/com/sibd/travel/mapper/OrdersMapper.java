package com.sibd.travel.mapper;

import com.sibd.travel.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersMapper {
    @Select("select * from orders")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select =
                    "com.sibd.travel.mapper.ProductMapper.findById"))
    })
    List<Orders> findAll();
    @Select("select * from orders where id =#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "com.sibd.travel.mapper.ProductMapper.findById")),
            @Result(column = "memberId",property = "member",one =@One(select = "com.sibd.travel.mapper.MemberMapper.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "com.sibd.travel.mapper.TravellerMapper.findByIds"))
    })
    Orders findById(String id);

    @Delete("delete from orders where productId =#{id}")
    void delete(String id);
    @Update("update orders set orderStatus=1 where id =#{id}")
    void onStatus(String id);
    @Update("update orders set orderStatus=0 where id =#{id}")
    void offStatus(String id);
}
