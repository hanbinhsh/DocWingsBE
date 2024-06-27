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

    @Override
    public List<SharePage> getSharesByUserId(@Param("userId") long userId){
        return shareMapper.getSharesByUserId(userId);
    }

    @Override
    public List<SharePage> getAllShares(){
        return shareMapper.getAllShares();
    }

    @Override
    public int countSharesByUserId(long userId) {
        return shareMapper.countSharesByUserId(userId);
    }

    @Override
    public void insertShare(Shares share) {
        shareMapper.insertShare(share);
    }

    @Override
    public void deleteShareByShareId(long shareId) {
        shareMapper.deleteShareByShareId(shareId);
    }

    @Override
    public void updateShare(Shares share) {
        shareMapper.updateShare(share);
    }

    @Override
    public SharePage getSharesByShareId(long shareId) {
        return shareMapper.getSharesByShareId(shareId);
    }

    @Override
    public List<SharePage> getShareByUserIdGroupId(long userId, long groupId) {
        return shareMapper.getShareByUserIdGroupId(userId, groupId);
    }

    @Override
    public long getDownloadCount(long shareId, long fileId) {
        return shareMapper.getDownloadCount(shareId, fileId);
    }
}
