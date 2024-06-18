package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.servers.DWServer;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


// 控制层的类
// 使用注解--表明该类是控制类--特征：返回JSON格式
@RestController
public class DWController {
    // 依赖注入--业务层的接口
    @Resource
    private DWServer dwSever;

    // 获取前端请求并推送响应结果
    // 此处注解使用@PostMpping/@GetMapping
    // 形参加入@RequestBody注解(JSON格式)
    @RequestMapping("/search")
    public Map<String, Object> search(@RequestBody Map<String, Object> map) {
        // 调用业务层接口的方法
        return this.dwSever.searchInfo(map);
    }

    @RequestMapping("/allUser")
    public List<User> searchUser() {
        return this.dwSever.searchUser();
    }

    @GetMapping("/findUserByID")
    public User findUserByID(@RequestParam Long id) {
        return this.dwSever.getUser(id);
    }

    @PostMapping(value = "/registerUser")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        this.dwSever.insertUser(user);
        return ResponseEntity.ok("Success"); // 此处应封装数据
    }
}
