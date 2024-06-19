package com.filemanager.docwingsbe.mapper;

import ch.qos.logback.core.joran.action.ParamAction;
import com.filemanager.docwingsbe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public User loginVerification(@Param("userName") String userName, @Param("password") String password);
    public void UserDelete(@Param("userId") long userId);
    public void UserCollectionDelete(@Param("userId") long userId);
}
