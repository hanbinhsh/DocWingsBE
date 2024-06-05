package com.example.docwingsbe.servers;

import java.util.Map;

// 业务层接口
public interface DWServer {
    // 从数据访问层（映射层）获取查询信息
    Map<String, Object> searchInfo(Map<String, Object> map);
}
