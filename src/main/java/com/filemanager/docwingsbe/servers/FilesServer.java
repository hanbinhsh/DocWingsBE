package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.Collections;
import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.Folders;
import com.filemanager.docwingsbe.entity.multy.FilesPage;
import com.filemanager.docwingsbe.entity.multy.FolderPage;

import java.util.List;

public interface FilesServer {
    Files findFileById(long id);
    List<FolderPage> findFoldersByParentId(long parentId);
    List<FilesPage> findFilesByParentId(long parentId);
    void insertFiles(List<Files> files);
    void insertFolders(List<Folders> folders);
    Folders findFolderById(long id);
    long countFFsByParentId(long parentId);
    void changeFileRouteById(long id, long parentId);
    void changeFolderRouteById(long id, long parentId);
    List<Files> findImagesByParentId(long parentId);
    void renameFile(long fileId, String fileName);
    void renameFolder(long folderId, String folderName);
    void recycleBinFile(long fileId, boolean status);
    void recycleBinFolder(long folderId, long status);
    List<FilesPage> findFileByDelete(long status);
    List<FolderPage> findFolderByDelete(long status);
    List<String> findPathsByFileName(String fileName);


    void CollectionsInsertFolder(long folderId, long userId);
    void CollectionsDeleteFolder(long folderId, long userId);
    void CollectionsInsertFile(long folderId, long userId);
    void CollectionsDeleteFile(long folderId, long userId);
    List<Collections> findCollectionFFs(long userId);
}
