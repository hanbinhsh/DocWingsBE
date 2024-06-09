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
}
