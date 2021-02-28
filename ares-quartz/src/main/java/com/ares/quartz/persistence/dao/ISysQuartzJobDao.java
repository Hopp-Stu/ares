package com.ares.quartz.persistence.dao;


import com.ares.core.persistence.dao.IBaseDao;
import com.ares.quartz.persistence.model.SysQuartzJob;

public interface ISysQuartzJobDao extends IBaseDao<SysQuartzJob> {

    Integer checkUnique(String jobName);

}