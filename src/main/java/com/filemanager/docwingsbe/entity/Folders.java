package com.filemanager.docwingsbe.entity;

public class Folders {

  private long folderId;
  private String folderName;
  private long parentId;
  private java.sql.Timestamp createTime;
  private long createrId;
  private String tag;
  private long isDeleted;
  private long lastModifierId;
  private java.sql.Timestamp lastModifyTime;


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


  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getCreaterId() {
    return createrId;
  }

  public void setCreaterId(long createrId) {
    this.createrId = createrId;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }


  public long getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(long isDeleted) {
    this.isDeleted = isDeleted;
  }


  public long getLastModifierId() {
    return lastModifierId;
  }

  public void setLastModifierId(long lastModifierId) {
    this.lastModifierId = lastModifierId;
  }


  public java.sql.Timestamp getLastModifyTime() {
    return lastModifyTime;
  }

  public void setLastModifyTime(java.sql.Timestamp lastModifyTime) {
    this.lastModifyTime = lastModifyTime;
  }

}
