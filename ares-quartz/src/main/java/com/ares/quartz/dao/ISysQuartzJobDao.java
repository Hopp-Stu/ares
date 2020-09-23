package com.ares.quartz.dao;


import com.ares.core.dao.IBaseDao;
import com.ares.quartz.model.SysQuartzJob;

public interface ISysQuartzJobDao extends IBaseDao<SysQuartzJob> {

    Integer checkUnique(String jobName);

}