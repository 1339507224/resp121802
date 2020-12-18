package com.sibd.travel.mapper;

import com.sibd.travel.pojo.Permission;
import com.sibd.travel.pojo.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PermissionMapper {
    @Select("select * from permission")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "permissionName",property = "permissionName"),
            @Result(column = "url",property = "url"),
            @Result(column = "id",property = "roles", many = @Many(select ="com.sibd.travel.mapper.RoleMapper.findRoleBypermissionId" ))
    })
    List<Permission> findAll();

    @Insert("insert into permission(id,permissionName,url) values(#{id},#{permissionName},#{url})")
    void save(Users users);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{id})")
    List<Permission> findRoleByIdAndAllPermission(String id);
    @Select("select * from permission where id  in(select permissionId from role_permission where roleId = #{id})")
    List<Permission> findAllById(String id);
}
