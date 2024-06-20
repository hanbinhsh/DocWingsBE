package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.Folders;
import com.filemanager.docwingsbe.entity.Collections;
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
    public List<Files> findImagesByCollection(@Param("userId") Long userId);
    public List<Files> findAudioByParentId(@Param("parentId") long parentId);
    public void insertFiles(@Param("files") List<Files> files);
    public void insertFolders(@Param("folders") List<Folders> folders);
    public long countFFsByParentId(@Param("parentId") Long parentId);
    public void changeFileRouteById(@Param("id") Long fileId, @Param("parentId") Long parentId);
    public void changeFolderRouteById(@Param("id") Long fileId, @Param("parentId") Long parentId);
    public List<String> findTags();
    public List<FolderPage> findFoldersByTag(@Param("tag") String tag);
    public List<FilesPage> findFilesByTag(@Param("tag") String tag);
    public List<FilesPage> findDocumentFiles();
    public List<FilesPage> findImageFiles();
    public List<FilesPage> findAudioFiles();
    public List<FilesPage> findVideoFiles();
    public List<FilesPage> findOtherFiles();
    public double countFileSize();
    public double countTrashFileSize();

    public void renameFile(@Param("fileId") long fileId, @Param("fileName") String fileName);
    public void renameFolder(@Param("folderId") long folderId, @Param("folderName") String folderName);
    public void recycleBinFile(@Param("fileId") long fileId, @Param("status") long status);
    public void recycleBinFolder(@Param("folderId") long folderId, @Param("status") long status);
    public List<FilesPage> findFileByDelete(@Param("status") long status);
    public List<FolderPage> findFolderByDelete(@Param("status") long status);
    public List<String> findPathsByFileName(String fileName);

    public void CollectionsInsertFolder(@Param("folderId") long folderId, @Param("userId") long userId);
    public void CollectionsDeleteFolder(@Param("folderId") long folderId, @Param("userId") long userId);
    public void CollectionsInsertFile(@Param("fileId") long fileId, @Param("userId") long userId);
    public void CollectionsDeleteFile(@Param("fileId") long fileId, @Param("userId") long userId);
    public List<Collections> findCollectionFFs(@Param("userId") long userId);
    public void deleteCollectionsFile(@Param("fileId") long fileId);
    public void deleteFile(@Param("fileId") long fileId);
    public void deleteFolder(@Param("folderId") long folderId);
    public List<FolderPage> findCollectionFoldersByUserId(@Param("userId") Long userId);
    public List<FilesPage> findCollectionFilesByUserId(@Param("userId") Long userId);

    public List<String> findPathByParentId(@Param("parentId") long parentId);
    public List<Folders> findFolderDeleteByParentId(@Param("parentId") long parentId);
    public long findParentFolderByFileId(@Param("fileId") long fileId);
    public long findParentFolderByFolderId(@Param("folderId") long folderId);
    public void updateParentFolderByFolderId(@Param("folderId") long folderId);

}
