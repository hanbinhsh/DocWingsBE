package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.Usergroup;
import com.filemanager.docwingsbe.entity.multy.UserAndGroup;
import com.filemanager.docwingsbe.servers.UserGroupServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserGroupController {
    @Resource
    UserGroupServer userGroupServer;

    @RequestMapping("/findUserGroups")
    public Map<String,Object> findUserGroups() {  //FINISHED
        List<Usergroup> userGroups = userGroupServer.findAllUserGroups();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("usergroups",userGroups);
        result.put("data", data);
        return result;
    }

    @RequestMapping("/findUserGroupByName")
    public Map<String,Object> findUserGroupByName(@RequestParam String name) {  //FINISHED
        Usergroup userGroup = userGroupServer.findUserGroupByName(name);
        int state = 1;
        Map<String, Object> data = new HashMap<>();
        if(userGroup == null) {
            state = 0;
        }
        data.put("state",state);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("userGroup",userGroup);
        result.put("data", data);
        return result;
    }

    @RequestMapping("/deleteUserGroupByGroupId")
    public void deleteUserGroupByGroupId(@RequestBody Map<String, String> map) {
         this.userGroupServer.deleteUserGroupByGroupId(Long.parseLong(map.get("groupId")));
    }

    @RequestMapping("/updateAuth")
    public void updateAuth(@RequestBody Map<String, String> map) throws Exception {
        this.userGroupServer.updateAuth(Long.parseLong(map.get("groupId")),Long.parseLong(map.get("auth")));
    }

    @RequestMapping("/updateGroupName")
    public void updateGroupName(@RequestBody Map<String, String> map) throws Exception {
        this.userGroupServer.updateGroupName(Long.parseLong(map.get("groupId")),map.get("groupName"));
    }

}
