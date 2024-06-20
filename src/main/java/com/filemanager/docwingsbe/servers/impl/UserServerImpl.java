package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.mapper.UserMapper;
import com.filemanager.docwingsbe.servers.UserServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName) != null;
    }

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
}
