package com.tjetc.controller;

import com.tjetc.domain.User;
import com.tjetc.service.UserService;
import com.tjetc.util.JwtUtil;
import com.tjetc.util.SaltMD5Util;

import com.tjetc.vo.Result;
import com.tjetc.vo.TokenUserIdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/checkAccount")
    public boolean checkUsername(String account){
        User user =userService.findByAccount(account);
        return user!=null; //用户名存在返回true;否则返回false,代表用户名不存在
    }
    @RequestMapping("/checkPassword")
    public boolean checkPassword(String account,String password){
        User user = userService.findByAccount(account);
        return user!=null && SaltMD5Util.verifySaltPassword(password,user.getPassword());
    }
    @RequestMapping("/checkToken")
    public boolean checkToken(@RequestHeader("token")String  token){
        System.out.println("token= " +  token);
        return JwtUtil.checkToken(token);
    }

    @RequestMapping("/login")
    public Result login(@RequestBody User user){
        System.out.println("user= " + user);
        String account = user.getAccount();
        String password = user.getPassword();
        User user1 = userService.findByAccount(account);
        if(user1 ==null){
            return Result.error(500,"用户不存在");
        } else if (!SaltMD5Util.verifySaltPassword(password,user1.getPassword())) {
            return Result.error(500,"密码错误");
        }
        else{
            TokenUserIdVo tokenUserIdVo = new TokenUserIdVo();
            tokenUserIdVo.setToken(JwtUtil.getToken(account));
            tokenUserIdVo.setUserId(user1.getId());
            tokenUserIdVo.setName(user1.getName());
            return Result.ok(tokenUserIdVo);
        }
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        System.out.println("user= " + user);
        String account = userService.register(user);
        return Result.ok(200,"注册成功,你的账号为:"+account+"请牢记");
    }
    @RequestMapping("/getAccount")
    public String getAccount(Integer  id){
        return userService.getAccount(id);
    }

    @GetMapping("/checkName")
    public String checkName(@RequestParam String name) {
        return "Name is valid: " + name;
    }
}
