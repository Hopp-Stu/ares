package com.ares.core.persistence.dao;


import com.ares.core.persistence.model.system.SysTemplate;
import org.apache.ibatis.annotations.Param;

public interface ISysTemplateDao extends IBaseDao<SysTemplate>{
    SysTemplate getByName(@Param("name") String name);
}