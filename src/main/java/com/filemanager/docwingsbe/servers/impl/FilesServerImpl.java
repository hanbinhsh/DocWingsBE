package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.entity.Collections;
import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.Folders;
import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.entity.multy.FilesPage;
import com.filemanager.docwingsbe.entity.multy.FolderPage;
import com.filemanager.docwingsbe.mapper.FilesMapper;
import com.filemanager.docwingsbe.servers.FilesServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilesServerImpl implements FilesServer {
    @Resource
    private FilesMapper filesMapper;
    @Override
    @Transactional(readOnly = true)
    public Files findFileById(long id) {
        return this.filesMapper.findFileById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FolderPage> findFoldersByParentId(long parentId) {
        return filesMapper.findFoldersByParentId(parentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilesPage> findFilesByParentId(long parentId) {
        return filesMapper.findFilesByParentId(parentId);
    }

    @Override
    @Transactional
    public void insertFiles(List<Files> files) {
        filesMapper.insertFiles(files);
    }

    @Override
    public void insertFolders(List<Folders> folders) {
        filesMapper.insertFolders(folders);
    }

    @Override
    @Transactional(readOnly = true)
    public Folders findFolderById(long id) {
        return filesMapper.findFolderById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countFFsByParentId(long parentId) {
        return filesMapper.countFFsByParentId(parentId);
    }

    @Override
    public void changeFileRouteById(long id, long parentId) {
        filesMapper.changeFileRouteById(id, parentId);
    }

    @Override
    public void changeFolderRouteById(long id, long parentId) {
        filesMapper.changeFolderRouteById(id, parentId);
    }

    @Override
    public List<Files> findImagesByParentId(long parentId) {
        return filesMapper.findImagesByParentId(parentId);
    }


    @Override
    public void renameFile(long fileId, String fileName) {
        filesMapper.renameFile(fileId, fileName);
    }

    @Override
    public void renameFolder(long folderId, String folderName) {
        filesMapper.renameFolder(folderId, folderName);
    }

    @Override
    public void recycleBinFile(long fileId, long status) { filesMapper.recycleBinFile(fileId, status);}

    @Override
    public void recycleBinFolder(long folderId, long status) { filesMapper.recycleBinFolder(folderId, status); }

    @Override
    public List<FilesPage> findFileByDelete(long status) { return filesMapper.findFileByDelete(status); }

    @Override
    public List<FolderPage> findFolderByDelete(long status) { return filesMapper.findFolderByDelete(status); }

    @Override
    public List<String> findPathsByFileName(String fileName){ return filesMapper.findPathsByFileName(fileName); }

    @Override
    public void CollectionsInsertFolder(long folderId, long userId){
        filesMapper.CollectionsInsertFolder(folderId, userId);
    }

    @Override
    public void CollectionsDeleteFolder(long folderId, long userId){
        filesMapper.CollectionsDeleteFolder(folderId, userId);
    }

    @Override
    public void CollectionsInsertFile(long folderId, long userId){
        filesMapper.CollectionsInsertFile(folderId, userId);
    }

    @Override
    public void CollectionsDeleteFile(long folderId, long userId){
        filesMapper.CollectionsDeleteFile(folderId, userId);
    }

    @Override
    public List<Collections> findCollectionFFs(long userId) {
        return filesMapper.findCollectionFFs(userId);
    }

    @Override
    public void deleteFile(long fileId) { filesMapper.deleteFile(fileId); }

    @Override
    public void deleteFolder(long folderId) { filesMapper.deleteFolder(folderId); }
}