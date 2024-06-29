package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.Collections;
import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.Folders;
import com.filemanager.docwingsbe.entity.multy.FilesPage;
import com.filemanager.docwingsbe.entity.multy.FolderPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesServer {
    Files findFileById(long id);
    List<FolderPage> findFoldersByParentId(long parentId);
    List<FilesPage> findFilesByParentId(long parentId);
    void insertFiles(List<Files> files);
    void insertFolders(List<Folders> folders);
    Folders findFolderById(long id);
    long countFFsByParentId(long parentId);
    long countFFsByParentIdUserId(long userId);
    void changeFileRouteById(long id, long parentId);
    void changeFolderRouteById(long id, long parentId);
    List<Files> findImagesByParentId(long parentId);
    List<Files> findImagesByCollection(Long userId);
    List<String> findTags();
    List<FolderPage> findFoldersByTag(String tag);
    List<FilesPage> findFilesByTag(String tag);
    void renameFile(long fileId, String fileName, long userId);
    void renameFolder(long folderId, String folderName, long userId);
    void renameFileTag(long fileId, String tag, long userId);
    void renameFolderTag(long folderId, String tag, long userId);
    void recycleBinFile(long fileId, long status);
    void recycleParentFolder(long folderId);
    void recycleBinFolder(long folderId, long status);
    List<FilesPage> findFileByDelete(long status);
    List<FolderPage> findFolderByDelete(long status);
    List<String> findPathsByFileName(String fileName);
    void CollectionsInsertFolder(long folderId, long userId);
    void CollectionsDeleteFolder(long folderId, long userId);
    void CollectionsInsertFile(long folderId, long userId);
    void CollectionsDeleteFile(long folderId, long userId);
    List<Collections> findCollectionFFs(long userId);
    void deleteFile(long fileId);
    void deleteFolder(long folderId);
    List<FolderPage> findCollectionFoldersByUserId(long userId);
    List<FilesPage> findCollectionFilesByUserId(long userId);
    void deleteNode(long nodeId);

    // 空间计算
    double countFileSize();
    double countTrashFileSize();
    double countImageSize();
    double countDocumentSize();
    double countVideoSize();
    double countAudioSize();
    // 类别寻找
    List<FilesPage> findDocumentFiles();
    List<FilesPage> findImageFiles();
    List<FilesPage> findAudioFiles();
    List<FilesPage> findVideoFiles();
    List<FilesPage> findOtherFiles();
    // 文件(夹)搜索
    List<Files> findFilesByName(String fileName);
    List<Folders> findFoldersByName(String folderName);
}
