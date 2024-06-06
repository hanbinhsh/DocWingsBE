package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.mapper.DWMapper;
import com.filemanager.docwingsbe.servers.DWServer;
import com.filemanager.docwingsbe.entity.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 业务层接口的实现类
@Service
public class DWServerImpl implements DWServer {
    // 依赖注入--数据访问层的接口
    // 可使用@Autowired
    @Resource
    private DWMapper dwmMapper;

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> searchInfo(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();
        // 调用数据访问层的接口中的方法
        List<Map<String, Object>> list = this.dwmMapper.select(map);
        // 逻辑处理
        if (!list.isEmpty()) {
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

    @Transactional(readOnly = true)
    public List<User> searchUser() {
        return dwmMapper.queryUsers();
    }

    @Override
    public User getUser(Long id) {
        return dwmMapper.findUserById(id);
    }

    @Override
    public void insertUser(User user) {
        dwmMapper.insertUser(user);
    }
}
