package com.filemanager.docwingsbe.servers;

import com.filemanager.docwingsbe.entity.Shares;
import com.filemanager.docwingsbe.entity.multy.SharePage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareServer {
    public List<SharePage> getSharesByUserId(@Param("userId") long userId);
    int countSharesByUserId(long userId);
    void insertShare(Shares share);
}
