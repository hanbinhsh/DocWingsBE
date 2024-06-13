package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.multy.LogPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    public List<LogPage> getLogPage();
}
