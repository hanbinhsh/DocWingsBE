package com.filemanager.docwingsbe.entity;


public class Collections {

  private long colId;
  private long userId;
  private long fileId;
  private long folderId;
  private long isFolder;


  public long getColId() {
    return colId;
  }

  public void setColId(long colId) {
    this.colId = colId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getFileId() {
    return fileId;
  }

  public void setFileId(long fileId) {
    this.fileId = fileId;
  }


  public long getFolderId() {
    return folderId;
  }

  public void setFolderId(long folderId) {
    this.folderId = folderId;
  }


  public long getIsFolder() {
    return isFolder;
  }

  public void setIsFolder(long isFolder) {
    this.isFolder = isFolder;
  }

}
