package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.multy.LogPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.util.List;

@Mapper
public interface LogMapper {
    public List<LogPage> getLogPage();
    public void insertLog(@Param("userId") long userId, @Param("act") String act, @Param("importance") long importance);
    public void updateLogoutUserLog(@Param("userId") long userId);
}
