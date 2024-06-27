package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.entity.multy.LogPage;
import com.filemanager.docwingsbe.mapper.LogMapper;
import com.filemanager.docwingsbe.servers.LogServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogMapperImpl implements LogServer {
    @Resource
    private LogMapper logMapper;

    @Override
    @Transactional(readOnly = true)
    public List<LogPage> getLogPage() {
        return logMapper.getLogPage();
    }

    @Override
    public void insertLog(long userId,String act ,long importance){
        logMapper.insertLog(userId, act,importance);
    }

    @Override
    public void updateLogoutUserLog(long userId){
        logMapper.updateLogoutUserLog(userId);
    }
}
