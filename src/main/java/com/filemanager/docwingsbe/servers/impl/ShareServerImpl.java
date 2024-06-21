package com.filemanager.docwingsbe.servers.impl;

import com.filemanager.docwingsbe.entity.Shares;
import com.filemanager.docwingsbe.entity.multy.SharePage;
import com.filemanager.docwingsbe.mapper.ShareMapper;
import com.filemanager.docwingsbe.servers.ShareServer;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareServerImpl implements ShareServer {
    @Resource
    private ShareMapper shareMapper;

    public List<SharePage> getSharesByUserId(@Param("userId") long userId){
        return shareMapper.getSharesByUserId(userId);
    }
}
