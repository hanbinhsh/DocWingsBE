package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.servers.UserServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Resource
    private UserServer userServer;

    @RequestMapping("/login")
    public User login(@RequestBody Map<String, String> map) {
        // 调用业务层接口的方法
        // 寻找用户
        User user = userServer.loginVerification(map.get("userName"),map.get("password"));
        if(user!=null){
            if(user.getIsAdmin()==1){
                return user;  // 管理员
            }else{
                return user;  // 普通用户
            }
        }
        return null;
    }
}
