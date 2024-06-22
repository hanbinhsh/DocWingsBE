package com.filemanager.docwingsbe.mapper;

import ch.qos.logback.core.joran.action.ParamAction;
import com.filemanager.docwingsbe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.util.List;

@Mapper
public interface UserMapper {
    public User loginVerification(@Param("userName") String userName, @Param("password") String password);
    public String selectUserByUserName(@Param("userName") String userName);
    public void insertUser(@Param("user") User user);
    public void UserDelete(@Param("userId") long userId);
    public void UserCollectionDelete(@Param("userId") long userId);
    public void UpdatePassword(@Param("userId") long userId, @Param("newPassword") String newPassword);
    public void UpdateEmail(@Param("userId") long userId, @Param("newEmail") String newEmail);
    public void UpdatePhone(@Param("userId") long userId, @Param("newPhone") String newPhone);
    public User findUserByName(@Param("userName") String userName);
    public void UpdateByAccountLockedTrueAndLockTimeBefore();
    public void SaveUser(@Param("failedLoginAttempts") int failedLoginAttempts, @Param("accountLocked") boolean accountLocked,@Param("lockTime") Instant lockTime,@Param("userId") long userId);
}
