package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.Folders;
import com.filemanager.docwingsbe.entity.multy.FilesPage;
import com.filemanager.docwingsbe.entity.multy.FolderPage;
import com.filemanager.docwingsbe.mapper.FilesMapper;
import com.filemanager.docwingsbe.servers.FilesServer;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Transactional(readOnly = true)
    public void insertFiles(List<Files> files) {
        filesMapper.insertFiles(files);
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
    public void renameFile(long fileId, String fileName) {
        filesMapper.renameFile(fileId, fileName);
    }

    @Override
    public void renameFolder(long folderId, String folderName) {
        filesMapper.renameFolder(folderId, folderName);
    }

    @Override
    public void deleteFile(long fileId) {

    }

}
