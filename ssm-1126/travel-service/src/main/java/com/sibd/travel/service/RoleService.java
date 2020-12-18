package com.sibd.travel.service;

import com.sibd.travel.pojo.Permission;
import com.sibd.travel.pojo.Role;
import com.sibd.travel.pojo.Users;

import java.util.List;

public interface RoleService {
    List<Role> findAll(Integer page, Integer pageSize);

    void save(Users users);

    List<Permission> findRoleByIdAndAllPermission(String id);

    Role findById(String id);

    void addPermissionToRole(String[] ids, String roleId);
}
