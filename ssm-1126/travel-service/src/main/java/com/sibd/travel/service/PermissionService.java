package com.sibd.travel.service;

import com.sibd.travel.pojo.Permission;
import com.sibd.travel.pojo.Users;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll(Integer page, Integer pageSize);

    void save(Users users);
}
