package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FilesMapper {
    public Files findDiskFileById(@Param("id") Long id);
}
