package com.sibd.travel.mapper;

import com.sibd.travel.pojo.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
    @Select("select * from member where id =#{id}")
    Member findById(String id);
}
