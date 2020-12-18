package com.sibd.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.sibd.travel.mapper.PermissionMapper;
import com.sibd.travel.pojo.Permission;
import com.sibd.travel.pojo.Users;
import com.sibd.travel.service.PermissionService;
import com.sibd.travel.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Permission> permissions=permissionMapper.findAll();
        return permissions;
    }

    @Override
    public void save(Users users) {
        users.setId(UuidUtil.getId());
        permissionMapper.save(users);
    }
}
