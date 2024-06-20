package com.filemanager.docwingsbe.entity;


import java.sql.Timestamp;

public class Shares {

  private long shareId;
  private long fileId;
  private long folderId;
  private long sharerId;
  private long auth;
  private java.sql.Timestamp shareTime;
  private java.sql.Timestamp dueTime;
  private long accepterId;
  private long isFolder;

  public Timestamp getDueTime() {
    return dueTime;
  }

  public void setDueTime(Timestamp dueTime) {
    this.dueTime = dueTime;
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


  public long getFolderId() {
    return folderId;
  }

  public void setFolderId(long folderId) {
    this.folderId = folderId;
  }


  public long getSharerId() {
    return sharerId;
  }

  public void setSharerId(long sharerId) {
    this.sharerId = sharerId;
  }


  public long getAuth() {
    return auth;
  }

  public void setAuth(long auth) {
    this.auth = auth;
  }


  public java.sql.Timestamp getShareTime() {
    return shareTime;
  }

  public void setShareTime(java.sql.Timestamp shareTime) {
    this.shareTime = shareTime;
  }


  public long getAccepterId() {
    return accepterId;
  }

  public void setAccepterId(long accepterId) {
    this.accepterId = accepterId;
  }


  public long getIsFolder() {
    return isFolder;
  }

  public void setIsFolder(long isFolder) {
    this.isFolder = isFolder;
  }

}
