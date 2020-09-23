package com.ares.core.dao;


import com.ares.core.model.SysDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysDictDataDao extends IBaseDao<SysDictData>{
    List<SysDictData> getDicts(@Param("dictType") String dictType);
}