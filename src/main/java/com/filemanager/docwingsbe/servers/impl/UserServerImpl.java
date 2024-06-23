package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.mapper.UserMapper;
import com.filemanager.docwingsbe.servers.UserServer;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


@Service
public class UserServerImpl implements UserServer {
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public User loginVerification(String userName, String password) {
        return userMapper.loginVerification(userName, password);
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    @Transactional
    public boolean selectUserByUserName(String userName) { return userMapper.selectUserByUserName(userName) != null;}

    @Override
    @Transactional
    public boolean selectUserByEmail(String email) { return userMapper.selectUserByEmail(email) != null;}

    @Override
    @Transactional
    public boolean selectUserByPhone(String phone) { return userMapper.selectUserByPhone(phone) != null;}

    @Override
    public void UserDelete(long userId){
        userMapper.UserDelete(userId);
    }

    @Override
    public void UserCollectionDelete(long userId){
        userMapper.UserCollectionDelete(userId);
    }

    @Override
    public void UpdatePassword(long userId,String newPassword){
        userMapper.UpdatePassword(userId,newPassword);
    }

    @Override
    public void UpdateEmail(long userId,String newEmail){
        userMapper.UpdateEmail(userId,newEmail);
    }

    @Override
    public void UpdatePhone(long userId,String newPhone){
        userMapper.UpdatePhone(userId,newPhone);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByName(String userName){
        return userMapper.findUserByName(userName);
    }

    @Scheduled(fixedRate = 3600)
    public void UpdateByAccountLockedTrueAndLockTimeBefore(){
        userMapper.UpdateByAccountLockedTrueAndLockTimeBefore();
    }

    @Override
    public void SaveUser(int failedLoginAttempts, boolean accountLocked, Instant lockTime,long userId){
        userMapper.SaveUser(failedLoginAttempts,accountLocked,lockTime,userId);
    }

    @Override
    public void resetPsw(long userId){
        userMapper.resetPsw(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(long userId){
        return userMapper.findUserById(userId);
    }
}
