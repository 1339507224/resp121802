package com.sibd.travel.mapper;

import com.sibd.travel.pojo.Traveller;
import org.apache.ibatis.annotations.Select;

public interface TravellerMapper {
    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId =#{id})")
    Traveller findByIds(String id);
}
