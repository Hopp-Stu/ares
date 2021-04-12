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

import com.ares.core.persistence.dao.ISysLogDao;
import com.ares.core.persistence.model.system.SysLog;
import com.ares.core.utils.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Young 2020/01/27
 **/
@Service
public class SysLogService {

    @Resource
    ISysLogDao sysLogDao;

    public void insert(SysLog sysLog) {
        sysLog.setId(SnowflakeIdWorker.getUUID());
        sysLog.setCreateTime(new Date());
        sysLogDao.insert(sysLog);
    }

    public PageInfo<SysLog> list(int pageNo, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysLog> userList = sysLogDao.list(map);
        PageInfo<SysLog> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }
}
