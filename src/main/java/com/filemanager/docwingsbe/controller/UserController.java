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
        return userServer.loginVerification(map.get("userName"),map.get("password"));
    }

    @RequestMapping("/UserDelete")
    public void UserDelete(@RequestBody Map<String, String> map) {//FINISHED
        userServer.UserDelete(Long.parseLong(map.get("userId")));
    }

    @RequestMapping("/UserCollectionDelete")
    public void UserCollectionDelete(@RequestBody Map<String, String> map) {//FINISHED
        userServer.UserCollectionDelete(Long.parseLong(map.get("userId")));
    }

    @RequestMapping("/UpdatePassword")
    public void UpdatePassword(@RequestBody Map<String, String> map) {//FINISHED
        userServer.UpdatePassword(Long.parseLong(map.get("userId")),map.get("newPassword"));
    }

    @RequestMapping("/UpdatePhone")
    public void UpdatePhone(@RequestBody Map<String, String> map) {//FINISHED
        userServer.UpdatePhone(Long.parseLong(map.get("userId")),map.get("newPhone"));
    }

    @RequestMapping("/UpdateEmail")
    public void UpdateEmail(@RequestBody Map<String, String> map) {//FINISHED
        userServer.UpdateEmail(Long.parseLong(map.get("userId")),map.get("newEmail"));
    }
}
