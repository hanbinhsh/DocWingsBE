package com.example.docwingsbe.servers;

import java.util.Map;

public interface DWServer {
    // 从数据访问层（映射层）获取查询信息
    Map<String, Object> searchInfo(Map<String, Object> map);
}
