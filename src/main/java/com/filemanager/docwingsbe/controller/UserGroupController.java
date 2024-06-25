package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.entity.Usergroup;
import com.filemanager.docwingsbe.servers.UserGroupServer;
import jakarta.annotation.Resource;
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
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data",userGroups);
        return result;
    }
}
