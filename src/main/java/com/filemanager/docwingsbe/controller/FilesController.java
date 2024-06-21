package com.filemanager.docwingsbe.controller;

import com.filemanager.docwingsbe.entity.Collections;
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
        builder.header("Accept-Ranges", "bytes");
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

    @RequestMapping("/findFFsByParentId")
    public Map<String, Object> findFFsByParentId(@RequestParam long parentId) {  // FINISHED
        List<FilesPage> files = this.filesServer.findFilesByParentId(parentId);
        List<FolderPage> folders = this.filesServer.findFoldersByParentId(parentId);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 201 );
        result.put("msg", "创建成功并返回相应资源数据");
        data.put("files",files);
        data.put("folders",folders);
        result.put("data",data);
        return result;
    }

    @RequestMapping("/findFileById")
    public Files findFileById(@RequestParam long id) {
        return this.filesServer.findFileById(id);
    }

    @RequestMapping("/findFolderById")
    public Folders findFolderById(@RequestParam long id) {
        return this.filesServer.findFolderById(id);
    }

    @RequestMapping("/insertOneFolder")  // FINISHED
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

    @RequestMapping("/changeFileRouteById")
    @CrossOrigin(origins = "*")  // 跨域
    public Map<String, Object> changeFileRouteById(@RequestParam long id, @RequestParam long parentId){  // FINISHED
        filesServer.changeFileRouteById(id, parentId);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 201 );
        result.put("msg", "创建成功并返回相应资源数据");
        data.put("id",id);
        data.put("parentId",parentId);
        result.put("data",data);
        return result;
    }

    @RequestMapping("/changeFolderRouteById")
    @CrossOrigin(origins = "*")  // 跨域
    public Map<String, Object> changeFolderRouteById(@RequestParam long id, @RequestParam long parentId){  // FINISHED
        filesServer.changeFolderRouteById(id, parentId);
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

    @RequestMapping("/countFFsByParentIdUserId")
    public long countFFsByParentIdUserId( @RequestBody Map<String, String> map){
        return filesServer.countFFsByParentIdUserId(Long.parseLong(map.get("userId")));
    }

    @RequestMapping("/findImagesByParentId")
    public Map<String,Object> findImagesByParentId(@RequestParam long parentId){  // FINISHED
        List<Files> images =  this.filesServer.findImagesByParentId(parentId);
        List<String> urls = new ArrayList<>();
        for (Files file : images) {
            urls.add("api/downloadFile?fileID="+file.getFileId());
        }
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("imageList",urls);
        result.put("data",data);
        return result;
    }

    @RequestMapping("/findImagesByCollection")
    public Map<String,Object> findImagesByCollection(@RequestParam long userId){  // FINISHED
        List<Files> images =  this.filesServer.findImagesByCollection(userId);
        List<String> urls = new ArrayList<>();
        for (Files file : images) {
            urls.add("api/downloadFile?fileID="+file.getFileId());
        }
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("imageList",urls);
        result.put("data",data);
        return result;
    }

    @RequestMapping("/findImages")
    public Map<String,Object> findImages(){  // FINISHED
        List<FilesPage> images =  this.filesServer.findImageFiles();
        List<String> urls = new ArrayList<>();
        for (FilesPage file : images) {
            urls.add("api/downloadFile?fileID="+file.getFileId());
        }
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("imageList",urls);
        result.put("data",data);
        return result;
    }

    @RequestMapping("/queryCapacity")
    public Map<String,Object> queryCapacity(){
        //  TODO 加入分类统计
        DecimalFormat df = new DecimalFormat("#.##");
        final int MAX_CAPACITY = 32;  // 最大容量
        double files = filesServer.countFileSize()/1024.0;
        double trashFiles = filesServer.countTrashFileSize()/1024.0;
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("filesCapacity",df.format(files));
        data.put("trashFilesCapacity",df.format(trashFiles));
        data.put("maxCapacity",df.format(MAX_CAPACITY));
        data.put("leftCapacity",df.format(MAX_CAPACITY-trashFiles-files));
        result.put("data",data);
        return result;
    }

    @RequestMapping("/queryCategoryCapacity")
    public Map<String,Object> queryCategoryCapacity(){
        DecimalFormat df = new DecimalFormat("#.##");
        final int MAX_CAPACITY = 32;  // 最大容量
        double files = filesServer.countFileSize()/1024.0;
        double trashFiles = filesServer.countTrashFileSize()/1024.0;
        double imageFiles = filesServer.countImageSize()/1024.0;
        double documentFiles = filesServer.countDocumentSize()/1024.0;
        double videoFiles = filesServer.countVideoSize()/1024.0;
        double audioFiles = filesServer.countAudioSize()/1024.0;
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("filesCapacity",df.format(files));
        data.put("trashFilesCapacity",df.format(trashFiles));
        data.put("maxCapacity",df.format(MAX_CAPACITY));
        data.put("leftCapacity",df.format(MAX_CAPACITY-trashFiles-files));
        data.put("imageCapacity",df.format(imageFiles));
        data.put("documentCapacity",df.format(documentFiles));
        data.put("videoCapacity",df.format(videoFiles));
        data.put("audioCapacity",df.format(audioFiles));
        data.put("otherCapacity",df.format(files - imageFiles - documentFiles - videoFiles - audioFiles));
        result.put("data",data);
        return result;
    }

    @RequestMapping("/findTags")
    public List<String> findTags(){
        return filesServer.findTags();
    }

    @RequestMapping("/findFFsByTag")
    public Map<String,Object> findFFsByTag(@RequestParam String tag){  // FINISHED
        List<FilesPage> filesPage = filesServer.findFilesByTag(tag);
        List<FolderPage> folderPage = filesServer.findFoldersByTag(tag);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("folders",folderPage);
        data.put("files",filesPage);
        result.put("data",data);
        return result;
    }

    @RequestMapping("/findFilesByCategory")
    public Map<String,Object> findFilesByCategory(@RequestParam int category) {  // FINISHED
        List<FilesPage> filesPage = switch (category) {
            case 0 ->  // 图片
                    filesServer.findImageFiles();
            case 1 ->  // 文档
                    filesServer.findDocumentFiles();
            case 2 ->  // 音频
                    filesServer.findAudioFiles();
            case 3 ->  // 视频
                    filesServer.findVideoFiles();
            case 4 ->  // 其他
                    filesServer.findOtherFiles();
            default -> null;
        };
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("files",filesPage);
        result.put("data",data);
        return result;
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
    public void renameFile(@RequestBody Map<String, String> map)  {  // FINISHED
        filesServer.renameFile(Long.parseLong(map.get("fileId")), map.get("fileName"));
    }

    @RequestMapping("/renameFolder")
    public void renameFolder(@RequestBody Map<String, String> map) {  // FINISHED
        filesServer.renameFolder(Long.parseLong(map.get("folderId")), map.get("folderName"));
    }

    @RequestMapping("/renameFileTag")
    public void renameFileTag(@RequestBody Map<String, String> map) {  // FINISHED
        filesServer.renameFileTag(Long.parseLong(map.get("fileId")), map.get("tag"));
    }

    @RequestMapping("/renameFolderTag")
    public void renameFolderTag(@RequestBody Map<String, String> map) {  // FINISHED
        filesServer.renameFolderTag(Long.parseLong(map.get("folderId")), map.get("tag"));
    }

    @RequestMapping("/recycleBinFile")
    public void recycleBinFile(@RequestBody Map<String, String> map) {
        filesServer.recycleBinFile(Long.parseLong(map.get("fileId")), Long.parseLong(map.get("status")));
    }

    @RequestMapping("/recycleBinFolder")
    public void recycleBinFolder(@RequestBody Map<String, String> map) {
        filesServer.recycleBinFolder(Long.parseLong(map.get("folderId")), Long.parseLong(map.get("status")));
    }

    @RequestMapping("/findFileByDelete")
    public List<FilesPage> findFileByDelete(@RequestBody Map<String, String> map) {
        return this.filesServer.findFileByDelete(Long.parseLong(map.get("status")));
    }

    @RequestMapping("/findFolderByDelete")
    public List<FolderPage> findFolderByDelete(@RequestBody Map<String, String> map) {
        return this.filesServer.findFolderByDelete(Long.parseLong(map.get("status")));
    }

    @RequestMapping("/deleteFile")
    public void deleteFile(@RequestBody Map<String, String> map) {
        Files dbFile = filesServer.findFileById(Long.parseLong(map.get("fileId")));
        filesServer.deleteFile(Long.parseLong(map.get("fileId")));
        File file = new File(dbFile.getPath());
        boolean status = file.delete();
    }

    @RequestMapping("/deleteFolder")
    public void deleteFolder(@RequestBody Map<String, String> map) {
        filesServer.deleteFolder(Long.parseLong(map.get("folderId")));
    }

    @RequestMapping("/CollectionsInsertFolder")
    public void CollectionsInsertFolder(@RequestBody Map<String, String> map) throws Exception {//FINISHED
        filesServer.CollectionsInsertFolder(Long.parseLong(map.get("folderId")),Long.parseLong(map.get("userId")));
    }
    @RequestMapping("/CollectionsDeleteFolder")
    public void CollectionsDeleteFolder(@RequestBody Map<String, String> map) throws Exception {//FINISHED
        filesServer.CollectionsDeleteFolder(Long.parseLong(map.get("folderId")),Long.parseLong(map.get("userId")));
    }
    @RequestMapping("/CollectionsInsertFile")
    public void CollectionsInsertFile(@RequestBody Map<String, String> map) throws Exception {//FINISHED
        filesServer.CollectionsInsertFile(Long.parseLong(map.get("fileId")),Long.parseLong(map.get("userId")));
    }
    @RequestMapping("/CollectionsDeleteFile")
    public void CollectionsDeleteFile(@RequestBody Map<String, String> map) throws Exception {//FINISHED
        filesServer.CollectionsDeleteFile(Long.parseLong(map.get("fileId")),Long.parseLong(map.get("userId")));
    }
    @RequestMapping("/findCollectionFFs")
    public List<Collections> findCollectionFFs(@RequestParam long userId) throws Exception {
        return filesServer.findCollectionFFs(userId);
    }
    @GetMapping("/searchFile")
    public ResponseEntity<List<String>> searchFile(@RequestParam(value = "fileName", required = false) String fileName){
        List<String> paths = filesServer.findPathsByFileName(fileName);
        return ResponseEntity.ok(paths);
    }
    @RequestMapping("/findCollectionFFsByUserId")
    public Map<String,Object> findCollectionFFsByUserId(@RequestParam long userId) {  // FINISHED
        List<FilesPage> filesPage = filesServer.findCollectionFilesByUserId(userId);
        List<FolderPage> folderPage = filesServer.findCollectionFoldersByUserId(userId);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200 );
        result.put("msg", "请求执行成功并返回相应数据");
        data.put("files",filesPage);
        data.put("folders",folderPage);
        result.put("data",data);
        return result;
    }
}