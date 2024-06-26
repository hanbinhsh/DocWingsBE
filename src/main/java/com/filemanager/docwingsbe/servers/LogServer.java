package com.filemanager.docwingsbe.servers;


import com.filemanager.docwingsbe.entity.multy.LogPage;

import java.time.Instant;
import java.util.List;

public interface LogServer {
    List<LogPage> getLogPage();
    public void insertLog(long userId, String act,long importance);

}
