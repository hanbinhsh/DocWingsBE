package com.filemanager.docwingsbe.entity.multy;

import java.time.Instant;

public class UserAndGroup {
    private long userId;
    private String userName;
    private String psw;
    private String email;
    private long groupId;
    private long isAdmin;
    private String phone;

    private String groupName;
    private long auth;

    private int failedAttempts;
    private boolean accountLocked;
    private Instant lockTime;

    public int getFailedAttempts() {return failedAttempts;}

    public void setFailedAttempts(int failedAttempts) {this.failedAttempts = failedAttempts;}

    public boolean isAccountLocked() {return accountLocked;}

    public void setAccountLocked(boolean accountLocked) {this.accountLocked = accountLocked;}

    public Instant getLockTime() {return lockTime;}

    public void setLockTime(Instant lockTime) {this.lockTime = lockTime;}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }


    public long getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(long isAdmin) {
        this.isAdmin = isAdmin;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
