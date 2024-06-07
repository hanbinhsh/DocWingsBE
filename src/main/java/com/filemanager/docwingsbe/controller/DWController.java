package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.servers.DWServer;
import jakarta.annotation.Resource;
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

    @RequestMapping("/searchUser")
    public List<User> searchUser() {
        return this.dwSever.searchUser();
    }

    @GetMapping("/findUserByID")
    public User findUserByID(@RequestParam Long id) {
        return this.dwSever.getUser(id);
    }

    @PostMapping(value = "/registerUser")
    public String registerUser(@RequestBody User user) {
        this.dwSever.insertUser(user);
        return "Success";  // 此处应封装数据
    }

    @PostMapping("/uploadOneFile")
    @CrossOrigin(origins = "*")  // 跨域
    public Map<String, Object> uploadOneFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();  // 文件名
        String contentType = file.getContentType();  // 内容类型
        String name = file.getName();  // 表单域名
        System.out.println(name+" "+fileName+" "+contentType);
        // 支持重复上传，uuid重新命名
        String randomFileName = UUID.randomUUID().toString();
        int suffixIndex = fileName.lastIndexOf(".");
        if(suffixIndex > 0){  // 有后缀名
            randomFileName = randomFileName + fileName.substring(suffixIndex);
        }
        String realFilePath = "c:/DocWings/"+randomFileName;
        file.transferTo(new File(realFilePath));  // 移动到目标文件
        Map<String, Object> result = new HashMap<>();
        result.put("filename",fileName);
        result.put("filepath",realFilePath);
        result.put("filetype",contentType);
        return result;
    }


}
