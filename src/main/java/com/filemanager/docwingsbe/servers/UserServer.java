package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.entity.multy.UserAndGroup;

import java.time.Instant;
import java.util.List;

public interface UserServer {
    public User loginVerification(String userName, String password);
    void insertUser(User user);
    boolean selectUserByUserName(String userName);
    boolean selectUserByEmail(String email);
    boolean selectUserByPhone(String phone);
    public void UserDelete(long userId);
    public void UserCollectionDelete(long userId);
    public void UpdatePassword(long userId, String newPassword);
    public void UpdateEmail(long userId, String newEmail);
    public void UpdatePhone(long userId, String newPhone);
    public User findUserByName(String userName);
    public void UpdateByAccountLockedTrueAndLockTimeBefore();
    public void SaveUser(int failedLoginAttempts, boolean accountLocked, Instant lockTime,long userId);
    public void resetPsw(long userId);
    public User findUserById(long userId);
    public List<UserAndGroup> findAllUsers();
    public void updateGroup(long userId, long groupId);
    public void insertGroup(long auth, String groupName);
    public String findGroupNameByUserId(long userId);
    public User findUserByEmail(String email);
    public User findUserByPhone(String phone);
    public void setFreezingTime(long userId,long time);

}
