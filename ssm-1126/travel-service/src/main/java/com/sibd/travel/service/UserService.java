package com.sibd.travel.service;

import com.sibd.travel.pojo.Role;
import com.sibd.travel.pojo.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<Users> findAll(Integer page, Integer pageSize);
    Users findById(String id);

    void save(Users users);

    List<Role> findUserByIdAndAllRole(String id);

    void addRoleToUser(String[] ids,String userId);
}
