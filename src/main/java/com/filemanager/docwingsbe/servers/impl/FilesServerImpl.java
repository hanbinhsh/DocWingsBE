package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.entity.Collections;
import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.Folders;
import com.filemanager.docwingsbe.entity.multy.FilesPage;
import com.filemanager.docwingsbe.entity.multy.FolderPage;
import com.filemanager.docwingsbe.mapper.FilesMapper;
import com.filemanager.docwingsbe.servers.FilesServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
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
    @Transactional(readOnly = true)
    public long countFFsByParentIdUserId(long userId) {
        return filesMapper.countFFsByParentIdUserId(userId);
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
    public List<Files> findImagesByCollection(Long userId) {
        return filesMapper.findImagesByCollection(userId);
    }

    @Override
    public List<String> findTags() {
        return filesMapper.findTags();
    }

    @Override
    public List<FolderPage> findFoldersByTag(String tag) {
        return filesMapper.findFoldersByTag(tag);
    }

    @Override
    public List<FilesPage> findFilesByTag(String tag) {
        return filesMapper.findFilesByTag(tag);
    }

    @Override
    public List<FilesPage> findDocumentFiles() {
        return filesMapper.findDocumentFiles();
    }

    @Override
    public List<FilesPage> findImageFiles() {
        return filesMapper.findImageFiles();
    }

    @Override
    public List<FilesPage> findAudioFiles() {
        return filesMapper.findAudioFiles();
    }

    @Override
    public List<FilesPage> findVideoFiles() {
        return filesMapper.findVideoFiles();
    }

    @Override
    public List<FilesPage> findOtherFiles() {
        return filesMapper.findOtherFiles();
    }

    @Override
    public double countFileSize() {
        return filesMapper.countFileSize();
    }

    @Override
    public double countTrashFileSize() {
        return filesMapper.countTrashFileSize();
    }

    @Override
    public List<Files> findDocxByParentId(long parentId) {
        return filesMapper.findDocxByParentId(parentId);
    }

    @Override
    public void renameFile(long fileId, String fileName) {
        filesMapper.renameFile(fileId, fileName);
    }

    @Override
    public double countImageSize() {
        return filesMapper.countImageSize();
    }

    @Override
    public double countDocumentSize() {
        return filesMapper.countDocumentSize();
    }

    @Override
    public double countVideoSize() {
        return filesMapper.countVideoSize();
    }

    @Override
    public double countAudioSize() {
        return filesMapper.countAudioSize();
    }

    @Override
    public void renameFolder(long folderId, String folderName) {
        filesMapper.renameFolder(folderId, folderName);
    }

    @Override
    public void renameFileTag(long fileId, String tag) {
        filesMapper.renameFileTag(fileId, tag);
    }

    @Override
    public void renameFolderTag(long folderId, String tag) {
        filesMapper.renameFolderTag(folderId, tag);
    }

    @Override
    public void recycleBinFile(long fileId, long status) {
        filesMapper.recycleBinFile(fileId, status);
        if(status==0){
            recycleParentFolder(filesMapper.findParentFolderByFileId(fileId));
        }
    }

    @Override
    public void recycleParentFolder(long folderId) {
        filesMapper.updateParentFolderByFolderId(folderId);
        folderId = filesMapper.findParentFolderByFolderId(folderId);
        if(folderId != 0){
            recycleParentFolder(folderId);
        }
    }

    @Override
    public void recycleBinFolder(long folderId, long status) {
        filesMapper.recycleBinFolder(folderId, status);
        if(status==0){
            recycleParentFolder(filesMapper.findParentFolderByFolderId(folderId));
        }
    }

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
    public void deleteFile(long fileId) {
        filesMapper.deleteCollectionsFile(fileId);
        filesMapper.deleteFile(fileId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FolderPage> findCollectionFoldersByUserId(long userId) {
        return filesMapper.findCollectionFoldersByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilesPage> findCollectionFilesByUserId(long userId) {
        return filesMapper.findCollectionFilesByUserId(userId);
    }
    public void deleteFolder(long folderId) {
        deleteNode(folderId);
        filesMapper.deleteFolder(folderId);
    }
    @Override
    public void deleteNode(long nodeId){
        List<Folders> childFolders = filesMapper.findFolderDeleteByParentId(nodeId);
        List<String> path = filesMapper.findPathByParentId(nodeId);
        for (String s : path) {
            File file = new File(s);
            boolean status = file.delete();
        }
        for (Folders f : childFolders) {
            deleteNode(f.getFolderId());
        }
    }


}