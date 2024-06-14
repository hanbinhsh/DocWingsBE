package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.multy.FolderPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FilesMapper {
    public Files findDiskFileById(@Param("id") Long id);
    public List<FolderPage> findFodersByParentId(@Param("parentId") Long parentId);
}
