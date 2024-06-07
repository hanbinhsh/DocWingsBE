package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.mapper.FilesMapper;
import com.filemanager.docwingsbe.servers.FilesServer;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FilesServerImpl implements FilesServer {
    @Resource
    private FilesMapper filesMapper;

    @Override
    @Transactional(readOnly = true)
    public Files findDiskFileById(long id) {
        return this.filesMapper.findDiskFileById(id);
    }
}