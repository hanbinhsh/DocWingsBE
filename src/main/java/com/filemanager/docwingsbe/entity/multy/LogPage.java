package com.filemanager.docwingsbe.entity.multy;

import java.sql.Timestamp;

public class LogPage {
    private String act;
    private long importance;
    private java.sql.Timestamp logTime;
    private String userName;
    private String email;
    private String groupName;

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

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
