package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.Usergroup;

import java.util.List;

public interface UserGroupServer {
    List<Usergroup> findAllUserGroups();
}
