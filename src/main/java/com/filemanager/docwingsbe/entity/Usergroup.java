package com.filemanager.docwingsbe.entity;


public class Usergroup {

  private long groupId;
  private String groupName;
  private long auth;


  public long getGroupId() {
    return groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }


  public long getAuth() {
    return auth;
  }

  public void setAuth(long auth) {
    this.auth = auth;
  }

}
