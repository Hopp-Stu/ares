package com.ares.core.dao;

import com.ares.core.model.SysNoticeRead;

import java.util.List;

public interface ISysNoticeReadDao extends IBaseDao<SysNoticeRead> {

    int batchInsert(List<SysNoticeRead> noticeReadList);

    List<String> getByUser(String userId);
}