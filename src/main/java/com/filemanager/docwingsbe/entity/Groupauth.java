package com.filemanager.docwingsbe.entity;


public class Groupauth {

  private long groupauthId;
  private long groupId;
  private long folderId;
  private long auth;


  public long getGroupauthId() {
    return groupauthId;
  }

  public void setGroupauthId(long groupauthId) {
    this.groupauthId = groupauthId;
  }


  public long getGroupId() {
    return groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }


  public long getFolderId() {
    return folderId;
  }

  public void setFolderId(long folderId) {
    this.folderId = folderId;
  }


  public long getAuth() {
    return auth;
  }

  public void setAuth(long auth) {
    this.auth = auth;
  }

}
