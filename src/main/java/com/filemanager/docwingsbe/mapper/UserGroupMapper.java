package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.Usergroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserGroupMapper {
    public List<Usergroup> findAllUserGroups();
}