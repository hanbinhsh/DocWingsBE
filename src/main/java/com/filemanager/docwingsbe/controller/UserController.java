package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.servers.UserServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class UserController {
    @Resource
    private UserServer userServer;

    @RequestMapping("/login")
    public User login(@RequestBody Map<String, String> map) {
        // 调用业务层接口的方法
        userServer.UpdateByAccountLockedTrueAndLockTimeBefore();
        User user = userServer.loginVerification(map.get("userName"),map.get("password"));
        User user2= userServer.findUserByName(map.get("userName"));
        if(user!=null&& !user2.isAccountLocked()){
            user2.setFailedAttempts(0);
            user2.setAccountLocked(false); // 确保解冻
            userServer.SaveUser(user2.getFailedAttempts(),user2.isAccountLocked(),user2.getLockTime(),user2.getUserId());
        }else if(user2!=null){
            user2.setFailedAttempts(user2.getFailedAttempts()+1);
            userServer.SaveUser(user2.getFailedAttempts(),user2.isAccountLocked(),user2.getLockTime(),user2.getUserId());
            if (user2.getFailedAttempts() >= 5) {
                // 达到尝试次数限制，冻结用户
                user2.setAccountLocked(true);
                user2.setLockTime(Instant.now());
                userServer.SaveUser(user2.getFailedAttempts(),user2.isAccountLocked(),user2.getLockTime(),user2.getUserId());
                return user2;
            }
            userServer.SaveUser(user2.getFailedAttempts(),user2.isAccountLocked(),user2.getLockTime(),user2.getUserId());
        }
        return user;
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
