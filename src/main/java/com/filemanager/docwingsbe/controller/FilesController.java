package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.User;
import com.filemanager.docwingsbe.entity.multy.FolderPage;
import com.filemanager.docwingsbe.servers.DWServer;
import com.filemanager.docwingsbe.servers.FilesServer;
import com.filemanager.docwingsbe.servers.impl.FilesServerImpl;
import jakarta.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class FilesController {
    @Resource
    private FilesServer filesServer;

    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(long fileID) throws IOException {
        Files file = filesServer.findDiskFileById(fileID);
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);  // 设置响应对象为二进制流
        String fileName = file.getPath();  // 设置下载的文件名
        fileName = URLEncoder.encode(fileName,"UTF-8");
        builder.header("Access-Control-Expose-Headers", "Content-Disposition");
        builder.header("Content-Disposition", "attachment;filename*=UTF-8''" + fileName);
        File dFile = new File(file.getPath());
        ResponseEntity<byte[]> responseEntity = builder.body(FileUtils.readFileToByteArray(dFile));
        return responseEntity;
    }

    @RequestMapping("/findFodersByParentId")
    public List<FolderPage> findFodersByParentId(@RequestParam long parentId) {
        return this.filesServer.findFodersByParentId(parentId);
    }
}
