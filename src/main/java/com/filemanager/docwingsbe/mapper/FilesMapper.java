package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.Folders;
import com.filemanager.docwingsbe.entity.multy.FilesPage;
import com.filemanager.docwingsbe.entity.multy.FolderPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FilesMapper {
    public Files findFileById(@Param("id") Long id);
    public Folders findFolderById(@Param("id") Long id);
    public List<FolderPage> findFoldersByParentId(@Param("parentId") Long parentId);
    public List<FilesPage> findFilesByParentId(@Param("parentId") Long parentId);
    public void insertFiles(@Param("files") List<Files> files);
}
