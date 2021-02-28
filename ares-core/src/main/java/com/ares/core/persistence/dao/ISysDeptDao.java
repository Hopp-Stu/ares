package com.ares.core.persistence.dao;


import com.ares.core.persistence.model.system.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysDeptDao extends IBaseDao<SysDept>{

    List<SysDept> getAllDept();

    SysDept getByDeptId(@Param("id") String id);
}