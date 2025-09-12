package com.tjetc.service.impl;

import com.tjetc.dao.UserMapper;
import com.tjetc.domain.User;
import com.tjetc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper UserMapper;
    @Override
    public User findByAccount(String account){
        return UserMapper.findByAccount(account);
    }
    @Override
    public User findByName(String name){
        return UserMapper.findByName(name);
    }
    @Override
    public void register(User user){
        UserMapper.register(user);
    }
    @Override
    public String getAccount(Integer id){
        return UserMapper.getAccount(id);
    }
}
