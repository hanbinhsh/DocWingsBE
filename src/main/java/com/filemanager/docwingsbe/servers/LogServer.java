package com.filemanager.docwingsbe.servers;


import com.filemanager.docwingsbe.entity.multy.LogPage;

import java.util.List;

public interface LogServer {
    List<LogPage> getLogPage();
}
