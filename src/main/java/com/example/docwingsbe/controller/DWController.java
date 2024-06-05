package com.example.docwingsbe.controller;

import com.example.docwingsbe.servers.DWServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// 控制层的类
// 使用注解--表明该类是控制类--特征：返回JSON格式
@RestController
public class DWController {
    // 依赖注入--业务层的接口
    @Resource
    private DWServer dwSever;

    // 获取前端请求并推送响应结果
    @RequestMapping("/search")
    public Map<String, Object> searchInfo(@RequestBody Map<String, Object> map) {
        // 调用业务层接口的方法
        return this.dwSever.searchInfo(map);
    }
}
