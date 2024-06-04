package com.example.docwingsbe.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface DWMapper {
    // 根据部门编号查询指定的员工信息
    @Select("select * from emp where deptno = #{deptno}")
    List<Map<String, Object>> select(Map<String, Object> map);
}
