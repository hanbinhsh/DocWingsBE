package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.multy.LogPage;
import com.filemanager.docwingsbe.servers.LogServer;
import jakarta.annotation.Resource;
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
    public Map<String, Object> searchUser() {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("logPage", this.logServer.getLogPage());
        result.put("code", 200);
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data", data);
        return result;
    }
}
