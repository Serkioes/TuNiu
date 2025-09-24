package com.tjetc.service.impl;

import com.tjetc.dao.UserMapper;
import com.tjetc.domain.User;
import com.tjetc.service.UserService;
import com.tjetc.util.SaltMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper UserMapper;
    private static int num = 0;
    @Override
    public User findByAccount(String account){
        return UserMapper.findByUsername(account);
    }
    @Override
    public User findByName(String name){
        return UserMapper.findByName(name);
    }
    @Override
    public String register(User user){
        if(user == null || user.getPassword() == null || user.getPassword().isEmpty() || user.getName() == null || user.getName().isEmpty()){
            throw new IllegalArgumentException("用户名和密码不能为空");
        }
        String account = "";
        for (String s : LocalDate.now().toString().split("-")) {
            account += s;
        }
            account +=  (num++);   //系统自动生成账号
            user.setAccount(account);

            String password = user.getPassword();  //原始密码！
            String saltMd5Password = SaltMD5Util.generateSaltPassword(password);//  MD5+盐   加密后，再加盐，防止被破解
            user.setPassword(saltMd5Password);   //重新放回到user的password中去 ，再存入数据库

            int rows = UserMapper.register(user);
            if(rows != 1){
                throw new RuntimeException("注册失败，未插入记录");
            }
            return account;

        }
    @Override
    public String getAccount(Integer id){
        return UserMapper.getAccount(id);
    }
}
