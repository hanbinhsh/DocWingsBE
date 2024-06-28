package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.Shares;
import com.filemanager.docwingsbe.entity.multy.SharePage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareServer {
    public List<SharePage> getSharesByUserId(@Param("userId") long userId);
    public List<SharePage> getAllShares();
    int countSharesByUserId(long userId);
    void deleteShareByShareId(long shareId);
    void insertShare(Shares share);
    void updateShare(Shares share);
    SharePage getSharesByShareId(long shareId);
    List<SharePage> getShareByUserIdGroupId(long userId, long groupId);
    long getDownloadCount(long shareId, long fileId);
    long getDownloadCountByShareId(long shareId);
}
