package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

// 数据访问（映射）层接口
@Mapper
public interface DWMapper {
    // 根据部门编号查询指定的员工信息
    @Select("select * from folders where parent_id = #{parent_id}")
    List<Map<String, Object>> select(Map<String, Object> map);

    public List<User> queryUsers();
    public User findUserById(@Param("id") Long id);
    public void insertUser(@Param("user") User user);

}
