package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.Shares;
import com.filemanager.docwingsbe.entity.multy.SharePage;
import com.filemanager.docwingsbe.servers.ShareServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShareController {
    @Resource
    private ShareServer shareServer;

    @RequestMapping("/getSharesByUserId")
    public Map<String, Object> getSharesByUserId(@RequestParam long userId) {
        List<SharePage> shares = this.shareServer.getSharesByUserId(userId);
        for (SharePage share : shares) {
            share.generateThings();
        }
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("shares", shares);
        result.put("code", 200);
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data", data);
        return result;
    }
}