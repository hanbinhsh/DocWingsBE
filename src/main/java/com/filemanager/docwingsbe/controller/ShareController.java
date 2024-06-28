package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.Shares;
import com.filemanager.docwingsbe.entity.multy.SharePage;
import com.filemanager.docwingsbe.servers.ShareServer;
import org.springframework.web.bind.annotation.RequestBody;
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
        int shareCount = shares.size();
        for (SharePage share : shares) {
            share.generateThings();
        }
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("shares", shares);
        data.put("shareCount", shareCount);
        result.put("code", 200);
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data", data);
        return result;
    }

    @RequestMapping("/getAllShares")
    public Map<String, Object> getAllShares() {
        List<SharePage> shares = this.shareServer.getAllShares();
        int shareCount = shares.size();
        for (SharePage share : shares) {
            share.generateThings();
        }
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("shares", shares);
        data.put("shareCount", shareCount);
        result.put("code", 200);
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data", data);
        return result;
    }

    @RequestMapping("/insertShare")
    public Map<String, Object> insertShare(@RequestBody List<Shares> shares) {
        int count = shares.size();
        for (Shares share : shares) {
            shareServer.insertShare(share);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code", 201 );
        result.put("msg", "创建成功并返回相应资源数据");
        result.put("count",count);
        return result;
    }

    @RequestMapping("/deleteShareByShareId")
    public Map<String, Object> deleteShareByShareId(@RequestBody Map<String, String> map) {
        shareServer.deleteShareByShareId(Long.parseLong(map.get("shareId")));
        Map<String, Object> result = new HashMap<>();
        result.put("code", 201 );
        result.put("msg", "删除成功并返回相应资源数据");
        return result;
    }

    @RequestMapping("/getSharesByShareId")
    public Map<String, Object> getSharesByShareId(@RequestParam long shareId) {  // FINISHED
        SharePage share = this.shareServer.getSharesByShareId(shareId);
        int isExist = 1;
        if(share == null) {
            isExist = 0;
        }else{
            share.generateThings();
        }
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("share", share);
        data.put("isExist", isExist);
        result.put("code", 200);
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data", data);
        return result;
    }

    @RequestMapping("/getMyAcceptByUserIdGroupId")
    public Map<String, Object> getMyAcceptByUserIdGroupId(@RequestParam long userId,@RequestParam long groupId) {  // FINISHED
        List<SharePage> shares = this.shareServer.getShareByUserIdGroupId(userId, groupId);
        for(SharePage share : shares){
            share.generateThings();
        }
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("shares", shares);
        data.put("acceptCount", shares.size());
        result.put("code", 200);
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data", data);
        return result;
    }

    @RequestMapping("/updateShare")
    public Map<String, Object> updateShare(@RequestBody List<Shares> shares) {
        int count = shares.size();
        for (Shares share : shares) {
            shareServer.updateShare(share);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code", 201 );
        result.put("msg", "修改成功并返回相应资源数据");
        result.put("count",count);
        return result;
    }

    @RequestMapping("/getDownloadCount")
    public Map<String, Object> getDownloadCount(@RequestBody Map<String, String> map) {
        long count = shareServer.getDownloadCount(Long.parseLong(map.get("shareId")), Long.parseLong(map.get("fileId")));
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("count",count);
        return result;
    }

    @RequestMapping("/getDownloadCountByShareId")
    public Map<String, Object> getDownloadCountByShareId(@RequestBody Map<String, String> map) {
        long count = shareServer.getDownloadCountByShareId(Long.parseLong(map.get("shareId")));
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("count", count);
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        result.put("data",data);
        return result;
    }
}