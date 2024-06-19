package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.servers.UserServer;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Resource
    private UserServer userServer;

    @RequestMapping("/login")
    public User login(@RequestBody Map<String, String> map) {
        // 调用业务层接口的方法
        return userServer.loginVerification(map.get("userName"),map.get("password"));
    }

    @PostMapping(value = "/registerUser")
    public String registerUser(@RequestBody User user) {
        if(userServer.selectUserByUserName(user.getUserName()))
        {
            return "用户名已存在";
        }
        else
        {
            this.userServer.insertUser(user);
            return "注册成功";
        }
    }

    @RequestMapping("/UserDelete")
    public void UserDelete(@RequestBody Map<String, String> map) {
        userServer.UserDelete(Long.parseLong(map.get("userId")));
    }

    @RequestMapping("/UserCollectionDelete")
    public void UserCollectionDelete(@RequestBody Map<String, String> map) {
        userServer.UserCollectionDelete(Long.parseLong(map.get("userId")));
    }
}
