package com.ares.quartz.dao;


import com.ares.core.dao.IBaseDao;
import com.ares.quartz.model.SysQuartzJobLog;

public interface ISysQuartzJobLogDao extends IBaseDao<SysQuartzJobLog> {

    int cleanJobLog();

}