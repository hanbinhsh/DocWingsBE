package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.entity.Usergroup;
import com.filemanager.docwingsbe.mapper.UserGroupMapper;
import com.filemanager.docwingsbe.servers.UserGroupServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServerImpl implements UserGroupServer {
    @Resource
    UserGroupMapper userGroupMapper;

    @Override
    public List<Usergroup> findAllUserGroups() {
        return userGroupMapper.findAllUserGroups();
    }

    @Override
    public Usergroup findUserGroupByName(String name) {
        return userGroupMapper.findUserGroupByName(name);
    }

    @Override
    public void deleteUserGroupByGroupId(long groupId) {
        userGroupMapper.deleteUserGroupByGroupId(groupId);
    }

    @Override
    public void updateAuth(long groupId, long auth) { userGroupMapper.updateAuth(groupId, auth); }

    @Override
    public void updateGroupName(long groupId, String groupName) { userGroupMapper.updateGroupName(groupId, groupName); }
}
