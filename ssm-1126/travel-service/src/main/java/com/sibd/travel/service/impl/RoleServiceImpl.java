package com.sibd.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.sibd.travel.mapper.PermissionMapper;
import com.sibd.travel.mapper.RoleMapper;
import com.sibd.travel.pojo.Permission;
import com.sibd.travel.pojo.Role;
import com.sibd.travel.pojo.Users;
import com.sibd.travel.service.RoleService;
import com.sibd.travel.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Role> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Role> roles=roleMapper.findAll();
        return roles;
    }

    @Override
    public void save(Users users) {
        users.setId(UuidUtil.getId());
        roleMapper.save(users);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) {
        List<Permission> permissions=permissionMapper.findRoleByIdAndAllPermission(id);
        return permissions;
    }

    @Override
    public Role findById(String id) {
        Role role=roleMapper.findById(id);
        return role;
    }

    @Override
    public void addPermissionToRole(String[] ids,String roleId) {
        for (String permissionId : ids) {
            roleMapper.addPermissionToRole(permissionId,roleId);
        }
    }

}
