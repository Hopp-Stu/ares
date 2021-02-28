package com.ares.quartz.persistence.dao;


import com.ares.core.persistence.dao.IBaseDao;
import com.ares.quartz.persistence.model.SysQuartzJobLog;

public interface ISysQuartzJobLogDao extends IBaseDao<SysQuartzJobLog> {

    int cleanJobLog();

}