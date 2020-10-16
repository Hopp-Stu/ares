package com.ares.core.dao;


import com.ares.core.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysDeptDao extends IBaseDao<SysDept>{

    List<SysDept> getAllDept();

    SysDept getByDeptId(@Param("id") String id);
}