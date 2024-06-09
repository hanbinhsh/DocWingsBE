package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.User;

public interface UserServer {
    public User loginVerification(String userName, String password);
}
