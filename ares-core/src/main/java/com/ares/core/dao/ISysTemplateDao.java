package com.ares.core.dao;


import com.ares.core.model.SysTemplate;
import org.apache.ibatis.annotations.Param;

public interface ISysTemplateDao extends IBaseDao<SysTemplate>{
    SysTemplate getByName(@Param("name") String name);
}