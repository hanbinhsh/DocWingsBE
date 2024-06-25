package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.Usergroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserGroupServer {
    List<Usergroup> findAllUserGroups();
    Usergroup findUserGroupByName(String name);
}
