package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.multy.LogPage;
import com.filemanager.docwingsbe.servers.LogServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {
    @Resource
    private LogServer logServer;

    @RequestMapping("/allLog")
    public List<LogPage> searchUser() {
        return this.logServer.getLogPage();
    }
}
