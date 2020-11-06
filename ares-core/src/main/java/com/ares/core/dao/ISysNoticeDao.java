package com.ares.core.dao;


import com.ares.core.model.SysNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysNoticeDao extends IBaseDao<SysNotice> {

    int noticeNum(@Param("userId") String userId);

    List<SysNotice> getNotices();

}