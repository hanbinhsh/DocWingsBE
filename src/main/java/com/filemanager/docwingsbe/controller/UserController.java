package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.entity.multy.UserAndGroup;
import com.filemanager.docwingsbe.servers.UserServer;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
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
        }else if(user2!=null ){
            if(!user2.isAccountLocked()){
                user2.setFailedAttempts(user2.getFailedAttempts()+1);
                userServer.SaveUser(user2.getFailedAttempts(),user2.isAccountLocked(),user2.getLockTime(),user2.getUserId());
            }
            if (user2.getFailedAttempts() >= 5) {
                // 达到尝试次数限制，冻结用户
                if(!user2.isAccountLocked()){
                    user2.setAccountLocked(true);
                    user2.setLockTime(Instant.now());
                    userServer.SaveUser(user2.getFailedAttempts(),user2.isAccountLocked(),user2.getLockTime(),user2.getUserId());
                }
                return user2;
            }
            if(!user2.isAccountLocked()){
                userServer.SaveUser(user2.getFailedAttempts(),user2.isAccountLocked(),user2.getLockTime(),user2.getUserId());
            }

        }
        return user;
    }

    @RequestMapping("/findAuthByUserId")
    public long findAuthByUserId(@RequestParam long userId){
        return userServer.findAuthByUserId(userId);
    }

    @PostMapping(value = "/registerUser")
    public String registerUser(@RequestBody User user) {
        if(userServer.selectUserByUserName(user.getUserName()))
        {
            return "用户名已存在";
        }
        else if(userServer.selectUserByEmail(user.getEmail()))
        {
            return "邮箱已注册";
        }
        else if(userServer.selectUserByPhone(user.getPhone()))
        {
            return "电话号码已注册";
        }
        else
        {
            this.userServer.insertUser(user);
            return "注册成功";
        }
    }

    @RequestMapping("/UserDelete")
    public void UserDelete(@RequestBody Map<String, String> map) {//FINISHED
        userServer.UserDelete(Long.parseLong(map.get("userId")));
    }

    @RequestMapping("/UserCollectionDelete")
    public void UserCollectionDelete(@RequestBody Map<String, String> map) {//FINISHED
        userServer.UserCollectionDelete(Long.parseLong(map.get("userId")));
    }

    @RequestMapping("/updateUserName")
    public void UpdateUserName(@RequestBody Map<String, String> map) {//FINISHED
        userServer.updateUserName(Long.parseLong(map.get("userId")),map.get("userName"));
    }

    @RequestMapping("/UpdatePassword")
    public void UpdatePassword(@RequestBody Map<String, String> map) {//FINISHED
        userServer.UpdatePassword(Long.parseLong(map.get("userId")),map.get("newPassword"));
    }

    @RequestMapping("/UpdatePhone")
    public boolean UpdatePhone(@RequestBody Map<String, String> map) {//FINISHED
        User user = userServer.findUserByPhone(map.get("newPhone"));
        if(user!=null){
            return false;
        }
        userServer.UpdatePhone(Long.parseLong(map.get("userId")),map.get("newPhone"));
        return true;
    }

    @RequestMapping("/UpdateEmail")
    public boolean UpdateEmail(@RequestBody Map<String, String> map) {//FINISHED
        User user = userServer.findUserByEmail(map.get("newEmail"));
        if(user!=null){
            return false;
        }
        userServer.UpdateEmail(Long.parseLong(map.get("userId")),map.get("newEmail"));
        return true;
    }

    @RequestMapping("/queryIfExistsUserByUserName")
    public Map<String,Object> queryIfExistsUserByUserName(@RequestParam String userName) {  //FINISHED
        Map<String, Object> data = new HashMap<>();
        User user = userServer.findUserByName(userName);
        if(user!=null){
            data.put("state",1);
            data.put("userId",user.getUserId());
        }else{
            data.put("state",0);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data",data);
        return result;
    }

    @RequestMapping("/resetPsw")
    public User resetPsw(@RequestBody Map<String, String> map) {//FINISHED
        User user= userServer.findUserById(Long.parseLong(map.get("userId")));
        if(user!=null){
            userServer.resetPsw(Long.parseLong(map.get("userId")));
            return user;
        }
        return null;
    }

    @RequestMapping("/freeze")
    public User freeze(@RequestBody Map<String, String> map) {//FINISHED
        User user= userServer.findUserById(Long.parseLong(map.get("userId")));
        if(user!=null){
            user.setFailedAttempts(5);
            user.setAccountLocked(true);
            user.setLockTime(Instant.now());
            userServer.SaveUser(user.getFailedAttempts(),user.isAccountLocked(),user.getLockTime(),user.getUserId());
            return user;
        }
        return null;
    }

    @RequestMapping("/defrost")
    public User defrost(@RequestBody Map<String, String> map) {//FINISHED
        User user= userServer.findUserById(Long.parseLong(map.get("userId")));
        if(user!=null){
            user.setFailedAttempts(0);
            user.setAccountLocked(false);
            user.setLockTime(null);
            userServer.SaveUser(user.getFailedAttempts(),user.isAccountLocked(),user.getLockTime(),user.getUserId());
            return user;
        }
        return null;
    }

    @RequestMapping("/findUserByName")
    public User findUserByName(@RequestBody Map<String, String> map) {
        return userServer.findUserByName(map.get("userName"));
    }

    @RequestMapping("/findAllUsers")
    public List<UserAndGroup> findAllUsers() {
        return this.userServer.findAllUsers();
    }

    @RequestMapping("/updateGroup")
    public void updateGroup(@RequestBody Map<String, String> map) {
        this.userServer.updateGroup(Long.parseLong(map.get("userId")),Long.parseLong(map.get("groupId")));
    }

    @RequestMapping("/insertGroup")
    public void insertGroup(@RequestBody Map<String, String> map) {
        System.out.println(map.get("auth"));
        this.userServer.insertGroup(Long.parseLong(map.get("auth")),map.get("name"));
    }

    @RequestMapping("/findGroupNameByUserId")
    public String findGroupNameByUserId(@RequestBody Map<String, String> map) {
        return this.userServer.findGroupNameByUserId(Long.parseLong(map.get("userId")));

    }

    @RequestMapping("/findUserByEmail")
    public boolean findUserByEmail(@RequestBody Map<String, String> map) {
        return this.userServer.selectUserByEmail(map.get("email"));
    }

    @RequestMapping("/findUserByPhone")
    public boolean findUserByPhone(@RequestBody Map<String, String> map) {
        return this.userServer.selectUserByPhone(map.get("phone"));
    }

    @RequestMapping("/setFreezingTime")
    public void setFreezingTime(@RequestBody Map<String, String> map) {
        this.userServer.setFreezingTime(Long.parseLong(map.get("userId")),Long.parseLong(map.get("time"))-2);
    }

    @RequestMapping("/deleteUserByGroupId")
    public void deleteUserByGroupId(@RequestBody Map<String, String> map) {
        this.userServer.deleteUserByGroupId(Long.parseLong(map.get("groupId")));
    }

    @RequestMapping("/updateUsersGroup")
    public void updateUsersGroup(@RequestBody Map<String, String> map) {
        this.userServer.updateUsersGroup(Long.parseLong(map.get("groupId")));
    }
}
