package com.example.docwingsbe.controller;

import com.example.docwingsbe.servers.DWServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public class DWController {
    // 依赖注入--业务层的接口
    @Resource
    private DWServer dwSever;

    // 获取前端请求并推送响应结果
    @RequestMapping("/searchInfo")
    public Map<String, Object> searchInfo(@RequestBody Map<String, Object> map) {
        // 调用业务层接口的方法
        return this.dwSever.searchInfo(map);
    }
}
