package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.Files;
import com.filemanager.docwingsbe.entity.Folders;
import com.filemanager.docwingsbe.entity.multy.FilesPage;
import com.filemanager.docwingsbe.entity.multy.FolderPage;
import com.filemanager.docwingsbe.servers.FilesServer;
import jakarta.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.Map;

@RestController
public class FilesController {
    @Resource
    private FilesServer filesServer;

    @RequestMapping("/downloadFile")
    @CrossOrigin(origins = "*")  // 跨域
    public ResponseEntity<byte[]> downloadFile(@RequestParam long fileID) throws IOException {
        Files file = filesServer.findFileById(fileID);
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();  // 设置响应对象为二进制流
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = URLEncoder.encode(file.getFileName(),"UTF-8");  // 设置下载的文件名
        builder.header("Access-Control-Expose-Headers", "Content-Disposition");
        builder.header("Content-Disposition", "attachment;filename*=UTF-8''" + fileName);
        File dFile = new File(file.getPath());
        return builder.body(FileUtils.readFileToByteArray(dFile));
    }

    @RequestMapping("/findFoldersByParentId")
    public List<FolderPage> findFoldersByParentId(@RequestParam long parentId) {
        return this.filesServer.findFoldersByParentId(parentId);
    }

    @RequestMapping("/findFilesByParentId")
    public List<FilesPage> findFilesByParentId(@RequestParam long parentId) {
        return this.filesServer.findFilesByParentId(parentId);
    }

    @RequestMapping("/findFileById")
    public Files findFileById(@RequestParam long id) {
        return this.filesServer.findFileById(id);
    }

    @RequestMapping("/findFolderById")
    public Folders findFolderById(@RequestParam long id) {
        return this.filesServer.findFolderById(id);
    }

    @RequestMapping("/insertOneFolder")
    @CrossOrigin(origins = "*")  // 跨域
    public Map<String, Object> insertFolders(@RequestBody Folders folders){
        Timestamp timestamp = Timestamp.from(ZonedDateTime.now().toInstant());
        folders.setCreateTime(timestamp);
        folders.setLastModifyTime(timestamp);
        folders.setIsDeleted(0);
        folders.setTag("");
        filesServer.insertFolders(List.of(folders));
        Map<String, Object> result = new HashMap<>();
        result.put("code", 201 );
        result.put("msg", "创建成功并返回相应资源数据");
        result.put("data",folders);
        return result;
    }

    @RequestMapping("/changeFileRoteById")
    @CrossOrigin(origins = "*")  // 跨域
    public Map<String, Object> changeFileRoteById(@RequestParam long id, @RequestParam long parentId){
        filesServer.changeFileRoteById(id, parentId);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 201 );
        result.put("msg", "创建成功并返回相应资源数据");
        data.put("id",id);
        data.put("parentId",parentId);
        result.put("data",data);
        return result;
    }

    @RequestMapping("/countFFsByParentId")
    public long countFFsByParentId(@RequestParam long parentId){
        return filesServer.countFFsByParentId(parentId);
    }

    @RequestMapping("/findImagesByParentId")
    public List<String> findImagesByParentId(@RequestParam long parentId){
        List<Files> images =  this.filesServer.findImagesByParentId(parentId);
        List<String> urls = new ArrayList<>();
        for (Files file : images) {
            urls.add("api/downloadFile?fileID="+file.getFileId());
        }
        return urls;
    }

    @PostMapping("/uploadOneFile")
    @CrossOrigin(origins = "*")  // 跨域
    public Map<String, Object> uploadOneFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("userId") long userId,
                                             @RequestParam("parentId") long parentId) throws IOException {
        String fileName = file.getOriginalFilename();  // 文件名
        String contentType = file.getContentType();  // 内容类型
        String name = file.getName();  // 表单域名
        System.out.println(name+" "+fileName+" "+contentType);
        DecimalFormat df = new DecimalFormat("#.##");
        double size = Double.parseDouble(df.format(file.getSize() / (1024.0 * 1024.0)));  // 保留两位小数
        Timestamp timestamp = Timestamp.from(ZonedDateTime.now().toInstant());
        // 支持重复上传，uuid重新命名
        String randomFileName = UUID.randomUUID().toString();
        // 路径获取
        int suffixIndex = fileName.lastIndexOf(".");
        if(suffixIndex > 0){  // 有后缀名
            randomFileName = randomFileName + fileName.substring(suffixIndex);
        }
        String realFilePath = "c:/DocWings/"+randomFileName;
        // 数据库包装
        Files dBFile = new Files();
        dBFile.setFileName(fileName);
        dBFile.setParentId(parentId);
        dBFile.setUploadTime(timestamp);
        dBFile.setFileSize(size);
        dBFile.setFileType(contentType);
        dBFile.setUploaderId(userId);
        dBFile.setLastModifierId(userId);
        //dBFile.setTag("");
        dBFile.setLastModifyTime(timestamp);
        //dBFile.setIsDeleted(0);
        dBFile.setPath(realFilePath);  // 数据库路径字段
        filesServer.insertFiles(List.of(dBFile));
        // 文件操作
        file.transferTo(new File(realFilePath));  // 移动到目标文件
        // 返回信息
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("filename",fileName);
        result.put("filepath",realFilePath);
        result.put("filetype",contentType);
        return result;
    }

    @RequestMapping("/renameFile")
    public void renameFile(@RequestBody Map<String, String> map) throws Exception {
        Files file = filesServer.findFileById(Long.parseLong(map.get("fileId")));
        if (file == null){
            throw new Exception("File not found");
        }
        filesServer.renameFile(Long.parseLong(map.get("fileId")), map.get("fileName"));
    }

    @RequestMapping("/renameFolder")
    public void renameFolder(@RequestBody Map<String, String> map) throws Exception {
        filesServer.renameFolder(Long.parseLong(map.get("folderId")), map.get("folderName"));
    }

    @RequestMapping("/deleteFile")
    public void deleteFile(@RequestBody long fileId) throws Exception {
        filesServer.deleteFile(fileId);
    }
}
