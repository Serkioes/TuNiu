package com.tjetc.controller;

import com.tjetc.domain.User;
import com.tjetc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/checkAccount")
    public boolean checkUsername(String account){
        User user =userService.findByAccount(account);
        return user!=null; //用户名存在返回true;否则返回false,代表用户名不存在
    }


}
