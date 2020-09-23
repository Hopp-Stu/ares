package com.ares.core.dao;


import com.ares.core.model.SysNotice;

import java.util.List;

public interface ISysNoticeDao extends IBaseDao<SysNotice>{

    int noticeNum();

    List<SysNotice> getNotices();

}