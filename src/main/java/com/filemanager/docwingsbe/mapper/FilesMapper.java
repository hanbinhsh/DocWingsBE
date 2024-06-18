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
    public List<Files> findImagesByParentId(@Param("parentId") Long parentId);
    public void insertFiles(@Param("files") List<Files> files);
    public void insertFolders(@Param("folders") List<Folders> folders);
    public long countFFsByParentId(@Param("parentId") Long parentId);
    public void changeFileRouteById(@Param("id") Long fileId, @Param("parentId") Long parentId);
    public void changeFolderRouteById(@Param("id") Long fileId, @Param("parentId") Long parentId);
    public void renameFile(@Param("fileId") long fileId, @Param("fileName") String fileName);
    public void renameFolder(@Param("folderId") long folderId, @Param("folderName") String folderName);
    public void recycleBinFile(@Param("fileId") long fileId, @Param("status") boolean status);
    public void recycleBinFolder(@Param("folderId") long folderId, @Param("status") long status);
    public List<FilesPage> findFileByDelete(@Param("status") long status);
    public List<FolderPage> findFolderByDelete(@Param("status") long status);
    public List<String> findPathsByFileName(String fileName);
}
