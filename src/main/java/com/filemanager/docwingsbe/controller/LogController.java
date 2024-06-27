package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.multy.LogPage;
import com.filemanager.docwingsbe.servers.LogServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class    LogController {
    @Resource
    private LogServer logServer;

    @RequestMapping("/allLog")
    public Map<String, Object> searchUser() {  // FINISHED
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("logPage", this.logServer.getLogPage());
        result.put("code", 200);
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data", data);
        return result;
    }

    @RequestMapping("/insertDeleteFileLog")
    public void insertDeleteFileLog(@RequestBody Map<String, String> map) {
        String fileName = map.get("fileName");
        String act = "删除文件:" + fileName;
        this.logServer.insertLog(Long.parseLong(map.get("userId")),act,2);
    }

    @RequestMapping("/insertDeleteFolderLog")
    public void insertDeleteFolderLog(@RequestBody Map<String, String> map) {
        String folderName = map.get("folderName");
        String act = "删除文件夹:" + folderName;
        this.logServer.insertLog(Long.parseLong(map.get("userId")),act,2);
    }

    @RequestMapping("/insertRecycleFolderLog")
    public void insertRecycleFolderLog(@RequestBody Map<String, String> map) {
        String folderName = map.get("folderName");
        String act = "将文件夹" + folderName + "放入回收站";
        this.logServer.insertLog(Long.parseLong(map.get("userId")),act,1);
    }

    @RequestMapping("/insertRecycleFileLog")
    public void insertRecycleFileLog(@RequestBody Map<String, String> map) {
        String fileName = map.get("fileName");
        String act = "将文件" + fileName + "放入回收站";
        this.logServer.insertLog(Long.parseLong(map.get("userId")),act,1);
    }

    @RequestMapping("/updatePhoneLog")
    public void updatePhoneLog(@RequestBody Map<String, String> map) {
        long oldPhone = Long.parseLong(map.get("oldPhone"));
        long newPhone = Long.parseLong(map.get("newPhone"));
        String act = "将旧电话号码"+oldPhone+"更改为"+newPhone;
        this.logServer.insertLog(Long.parseLong(map.get("userId")),act,1);
    }

    @RequestMapping("/updateEmailLog")
    public void updateEmailLog(@RequestBody Map<String, String> map) {
        String oldEmail =map.get("oldEmail");
        String newEmail = map.get("newEmail");
        String act = "将旧邮箱"+oldEmail+"更改为"+newEmail;
        this.logServer.insertLog(Long.parseLong(map.get("userId")),act,1);
    }

    @RequestMapping("/logoutLog")
    public void logoutLog(@RequestBody Map<String, String> map) {
        String act = "用户注销";
        this.logServer.updateLogoutUserLog(Long.parseLong(map.get("userId")));
        this.logServer.insertLog(Long.parseLong(map.get("updateId")),act,2);
    }
}
