package com.filemanager.docwingsbe.mapper;

import ch.qos.logback.core.joran.action.ParamAction;
import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.entity.multy.UserAndGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.util.List;

@Mapper
public interface UserMapper {
    public User loginVerification(@Param("userName") String userName, @Param("password") String password);
    public String selectUserByUserName(@Param("userName") String userName);
    public String selectUserByEmail(@Param("email") String email);
    public String selectUserByPhone(@Param("phone") String phone);
    public void insertUser(@Param("user") User user);
    public void UserDelete(@Param("userId") long userId);
    public void UserCollectionDelete(@Param("userId") long userId);
    public void UpdatePassword(@Param("userId") long userId, @Param("newPassword") String newPassword);
    public void UpdateEmail(@Param("userId") long userId, @Param("newEmail") String newEmail);
    public void UpdatePhone(@Param("userId") long userId, @Param("newPhone") String newPhone);
    public User findUserByName(@Param("userName") String userName);
    public void UpdateByAccountLockedTrueAndLockTimeBefore();
    public void SaveUser(@Param("failedLoginAttempts") int failedLoginAttempts, @Param("accountLocked") boolean accountLocked,@Param("lockTime") Instant lockTime,@Param("userId") long userId);
    public void resetPsw(@Param("userId") long userId);
    public User findUserById(@Param("userId") long userId);
    public List<UserAndGroup> findAllUsers();
    public void updateGroup(@Param("userId") long userId, @Param("groupId") long groupId);
    public void insertGroup(@Param("auth") long auth,@Param("groupName") String groupName);
    public String findGroupNameByUserId(@Param("userId") long userId);
    public User findUserByEmail(@Param("email") String email);
    public User findUserByPhone(@Param("phone") String phone);
    public void setFreezingTime(@Param("userId") long userId, @Param("time") long time);
}
