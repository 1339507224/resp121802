package com.sibd.travel.mapper;

import com.sibd.travel.pojo.Role;
import com.sibd.travel.pojo.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {
    @Select("select * from role where id in(select roleId from users_role where userId =#{uid})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",many = @Many(select = "com.sibd.travel.mapper.PermissionMapper.findAllById"))
    })
    List<Role> findRoleByUserId(String uid);

    @Select("select * from role")
    List<Role> findAll();

    @Select("select * from role where id in(select roleId from role_permission where permissionId =#{permissionId})")
    List<Role> findRoleBypermissionId(String permissionId);

    @Insert("insert into role (id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
    void save(Users users);

    @Select("select * from role where id not in(select roleId from users_role where userId = #{id})")
    List<Role> findUserByIdAndAllRole(String id);

    @Select("select * from role where id =#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",many = @Many(select = "com.sibd.travel.mapper.PermissionMapper.findAllById"))
    })
    Role findById(String id);

    @Insert("insert into role_permission (permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("permissionId") String permissionId,@Param("roleId") String roleId);
}
