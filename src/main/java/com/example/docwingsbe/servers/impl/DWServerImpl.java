package com.example.docwingsbe.servers.impl;

import com.example.docwingsbe.mapper.DWMapper;
import com.example.docwingsbe.servers.DWServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 业务层接口的实现类
@Service
public class DWServerImpl implements DWServer {
    //依赖注入--数据访问层的接口
    @Resource
    private DWMapper dwmMapper;

    @Override
    public Map<String, Object> searchInfo(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();
        // 调用数据访问层的接口中的方法
        List<Map<String, Object>> list = this.dwmMapper.select(map);
        // 逻辑处理
        if (list.size() > 0) {
            result.put("message", "查询成功");
            result.put("data", list);
            result.put("count", list.size());
            result.put("flag", true);
            result.put("code", 0);
        }else{
            result.put("message", "查询失败");
            result.put("data", list);
            result.put("count", list.size());
            result.put("flag", false);
            result.put("code", 1);
        }
        return result;
    }
}
