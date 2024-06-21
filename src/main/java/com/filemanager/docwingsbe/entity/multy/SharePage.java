package com.filemanager.docwingsbe.entity.multy;

import java.sql.Timestamp;

public class SharePage {
    private long shareId;
    private long fileId;
    private String fileName;
    private long folderId;
    private String folderName;
    private long sharerId;
    private String sharerName;
    private long auth;
    private java.sql.Timestamp shareTime;
    private java.sql.Timestamp dueTime;
    private long accepterId;
    private String accepterName;
    private long isFolder;

    // 仅后端
    private long validate;
    private java.sql.Timestamp lastTime;
    private double lastRatio;
    // functions
    private void generateLastTime(){
        lastTime = new java.sql.Timestamp(dueTime.getTime()-System.currentTimeMillis());
    }
    private void generateLastRatio(){
        lastRatio = (double) (dueTime.getTime() - System.currentTimeMillis()) /(dueTime.getTime()-shareTime.getTime());
    }
    public void generateThings(){
        if(accepterName == null){
            accepterName = "所有人";
        }
        if(dueTime==null){
            validate = 2;  // 无限
            return;
        }
        if(dueTime.getTime() < System.currentTimeMillis()){
            validate = 0;  // 已过期
        }else{
            validate = 1;  // 正常
        }
        generateLastTime();
        generateLastRatio();
    }
    // getter&setter
    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public double getLastRatio() {
        return lastRatio;
    }

    public void setLastRatio(double lastRatio) {
        this.lastRatio = lastRatio;
    }

    public long getValidate() {
        return validate;
    }

    public void setValidate(long validate) {
        this.validate = validate;
    }

    public long getShareId() {
        return shareId;
    }

    public void setShareId(long shareId) {
        this.shareId = shareId;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFolderId() {
        return folderId;
    }

    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public long getSharerId() {
        return sharerId;
    }

    public void setSharerId(long sharerId) {
        this.sharerId = sharerId;
    }

    public String getSharerName() {
        return sharerName;
    }

    public void setSharerName(String sharerName) {
        this.sharerName = sharerName;
    }

    public long getAuth() {
        return auth;
    }

    public void setAuth(long auth) {
        this.auth = auth;
    }

    public Timestamp getShareTime() {
        return shareTime;
    }

    public void setShareTime(Timestamp shareTime) {
        this.shareTime = shareTime;
    }

    public Timestamp getDueTime() {
        return dueTime;
    }

    public void setDueTime(Timestamp dueTime) {
        this.dueTime = dueTime;
    }

    public long getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(long accepterId) {
        this.accepterId = accepterId;
    }

    public String getAccepterName() {
        return accepterName;
    }

    public void setAccepterName(String accepterName) {
        this.accepterName = accepterName;
    }

    public long getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(long isFolder) {
        this.isFolder = isFolder;
    }
}
