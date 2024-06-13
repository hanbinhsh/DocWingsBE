package com.filemanager.docwingsbe.entity;


public class Logs {

  private long logId;
  private long userId;
  private String act;
  private long importance;
  private java.sql.Timestamp logTime;


  public long getLogId() {
    return logId;
  }

  public void setLogId(long logId) {
    this.logId = logId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getAct() {
    return act;
  }

  public void setAct(String act) {
    this.act = act;
  }


  public long getImportance() {
    return importance;
  }

  public void setImportance(long importance) {
    this.importance = importance;
  }


  public java.sql.Timestamp getLogTime() {
    return logTime;
  }

  public void setLogTime(java.sql.Timestamp logTime) {
    this.logTime = logTime;
  }

}
