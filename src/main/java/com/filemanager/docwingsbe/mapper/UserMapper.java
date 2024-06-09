package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public User loginVerification(@Param("userName") String userName, @Param("password") String password);
}
