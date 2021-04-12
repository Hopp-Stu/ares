/*******************************************************************************
 * Copyright (c) 2021 - 9999, ARES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.ares.core.persistence.service;

import com.ares.core.persistence.dao.ISysDictDataDao;
import com.ares.core.persistence.model.system.SysDictData;
import com.ares.core.utils.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysDictDataService implements BaseService<SysDictData>{

    @Resource
    ISysDictDataDao sysDictDataDao;

    public PageInfo<SysDictData> list(int pageNo, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysDictData> lists = sysDictDataDao.list(map);
        PageInfo<SysDictData> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    public void insert(SysDictData obj) {
        obj.setId(SnowflakeIdWorker.getUUID());
        obj.setCreateTime(new Date());
        sysDictDataDao.insert(obj);
    }

    @Override
    public void update(SysDictData obj) {
        obj.setModifyTime(new Date());
        sysDictDataDao.update(obj);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        sysDictDataDao.deleteByIds(ids);
    }

    @Override
    public SysDictData getById(String id) {
        return sysDictDataDao.getById(id);
    }

    public List<SysDictData> list(SysDictData obj) {
        List<SysDictData> lists = sysDictDataDao.selectList(obj);
        return lists;
    }

    public List<SysDictData> getDicts(String dictType){
        return sysDictDataDao.getDicts(dictType);
    }

}