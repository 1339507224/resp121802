package com.sibd.travel.mapper;

import com.sibd.travel.pojo.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UsersMapper {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.sibd.travel.mapper.RoleMapper.findRoleByUserId")) })
    Users findUserByName(String name);

    @Select("select * from users")
    List<Users> findAll();

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.sibd.travel.mapper.RoleMapper.findRoleByUserId")) })
    Users findById(String id);

    @Insert("insert into users(id,email,username,password,phoneNum,status) value(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(Users users);

    @Insert("insert into users_role (roleId,userId) values(#{roleId},#{userId})")
    void addRoleToUser(@Param("roleId") String roleId,@Param("userId") String userId);
}
