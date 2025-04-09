package com.filemanager.docwingsbe.controller;

import java.io.File;

public class Utils {
    public static String getUploadLocation(){
        String baseDir = System.getProperty("user.dir"); // 获取当前项目的根目录
        String tempDirPath = baseDir + "/temp/Files";
        File tempDir = new File(tempDirPath);
        if (!tempDir.exists()) {
            tempDir.mkdirs(); // 递归创建目录
        }
        return tempDirPath + "/";
    }
}
