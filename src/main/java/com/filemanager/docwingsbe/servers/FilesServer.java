package com.filemanager.docwingsbe.servers;

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
    void deleteFile(long fileId);
}
