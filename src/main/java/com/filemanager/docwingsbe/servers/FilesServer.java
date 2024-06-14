package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.multy.FolderPage;

import java.util.List;

public interface FilesServer {
    Files findDiskFileById(long id);
    List<FolderPage> findFodersByParentId(long parentId);
}
