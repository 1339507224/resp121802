package com.sibd.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.sibd.travel.mapper.RoleMapper;
import com.sibd.travel.mapper.UsersMapper;
import com.sibd.travel.pojo.Product;
import com.sibd.travel.pojo.Role;
import com.sibd.travel.pojo.Users;
import com.sibd.travel.service.UserService;
import com.sibd.travel.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
/**
 * @author lenovo
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users users = usersMapper.findUserByName(name);
        List<Role> roles = users.getRoles();
        List<SimpleGrantedAuthority> authority = getAuthority(roles);
        User user=new User(users.getUsername(),users.getPassword(),users.getStatus()==0?false:true,true,true,true,authority);
        return user;
    }
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }

    @Override
    public List<Users> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Users> users = usersMapper.findAll();
        return users;
    }

    @Override
    public Users findById(String id) {
        return usersMapper.findById(id);
    }

    @Override
    public void save(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setId(UuidUtil.getId());
        usersMapper.save(users);
    }

    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
       List<Role> roles=roleMapper.findUserByIdAndAllRole(id);
        return roles;
    }

    @Override
    public void addRoleToUser(String[] ids,String userId) {
        for (String roleId : ids) {
            usersMapper.addRoleToUser(roleId,userId);
        }
    }
}
