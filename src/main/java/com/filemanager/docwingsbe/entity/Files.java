package com.filemanager.docwingsbe.entity;


public class Files {

  private long fileId;
  private String fileName;
  private long parentId;
  private java.sql.Timestamp uploadTime;
  private double fileSize;
  private String fileType;
  private long uploaderId;
  private long lastModifierId;
  private String tag;
  private java.sql.Timestamp lastModifyTime;
  private long isDeleted;
  private String path;


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


  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }


  public java.sql.Timestamp getUploadTime() {
    return uploadTime;
  }

  public void setUploadTime(java.sql.Timestamp uploadTime) {
    this.uploadTime = uploadTime;
  }


  public double getFileSize() {
    return fileSize;
  }

  public void setFileSize(double fileSize) {
    this.fileSize = fileSize;
  }


  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }


  public long getUploaderId() {
    return uploaderId;
  }

  public void setUploaderId(long uploaderId) {
    this.uploaderId = uploaderId;
  }


  public long getLastModifierId() {
    return lastModifierId;
  }

  public void setLastModifierId(long lastModifierId) {
    this.lastModifierId = lastModifierId;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }


  public java.sql.Timestamp getLastModifyTime() {
    return lastModifyTime;
  }

  public void setLastModifyTime(java.sql.Timestamp lastModifyTime) {
    this.lastModifyTime = lastModifyTime;
  }


  public long getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(long isDeleted) {
    this.isDeleted = isDeleted;
  }


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

}
