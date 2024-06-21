package com.filemanager.docwingsbe.mapper;

import com.filemanager.docwingsbe.entity.Shares;
import com.filemanager.docwingsbe.entity.multy.SharePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShareMapper {
    public List<SharePage> getSharesByUserId(@Param("userId") long userId);
    public int countSharesByUserId(@Param("userId") long userId);
}
