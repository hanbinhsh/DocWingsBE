package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.User;

public interface UserServer {
    public User loginVerification(String userName, String password);
    void insertUser(User user);
    boolean selectUserByUserName(String userName);
    public void UserDelete(long userId);
    public void UserCollectionDelete(long userId);
    public void UpdatePassword(long userId, String newPassword);
    public void UpdateEmail(long userId, String newEmail);
    public void UpdatePhone(long userId, String newPhone);
}
