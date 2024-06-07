package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.Files;

public interface FilesServer {
    Files findDiskFileById(long id);
}
