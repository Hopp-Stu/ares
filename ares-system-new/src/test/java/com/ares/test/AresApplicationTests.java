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

package com.ares.test;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.ares.core.persistence.model.listener.ElasticsearchEvent;
import com.ares.core.persistence.model.system.SysUser;
import com.ares.core.persistence.service.SysUserService;
import com.ares.core.utils.SpringUtils;
import com.ares.message.persistence.dao.AresDocumentRepository;
import com.ares.message.persistence.model.AresDocument;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.InputStream;

public class AresApplicationTests extends AresBaseTest {

    @Resource
    AresDocumentRepository repository;



    @Test
    public void testES() {

        AresDocument demo = new AresDocument("1", "ares-name", "ares-key", "this is a test content.", "this is a test body!");
        //repository.save(demo);
        ElasticsearchEvent event = new ElasticsearchEvent(demo);
        SpringUtils.publishEvent(event);

        System.out.println("==================================");
        Pageable pageable = PageRequest.of(0, 10);
        Page<AresDocument> page = repository.findByContentIsContaining("content", pageable);
        System.out.println("search content:" + JSON.toJSON(page.getContent()));
        System.out.println("==================================");
        System.out.println("all:" + JSON.toJSON(repository.findAll()));
    }

    @Test
    public void testExcel() throws Exception {
        InputStream in = new FileInputStream("E:\\GitHub\\ares\\doc\\test.xlsx");
        SysUserService userService = new SysUserService();
        AnalysisEventListener listener = userService.new UserDataListener(true,"");
        EasyExcel.read(in,SysUser.class, listener).sheet().doRead();
    }

}
