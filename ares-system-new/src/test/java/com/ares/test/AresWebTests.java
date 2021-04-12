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

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @description:
 * @author: Young
 * @date: 2021/03/01
 * @see: com.ares.test AresWebTests.java
 **/
public class AresWebTests extends AresBaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        try {
            log.info("setup mockmvc");
            mockMvc = MockMvcBuilders
                    .webAppContextSetup(webApplicationContext)
                    .apply(springSecurity())
                    .build();
        } catch (Exception e) {
            log.error("setup mockmvc error", e);
        }
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/ares/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", is(empty())));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/ares/system/user/list"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(header().string("Location", "/ares/login"));
    }

    @Test
    @WithUserDetails("admin")
    public void testAuth() throws Exception {
        MvcResult result = mockMvc.perform(get("/ares/system/user/list")).andReturn();
        System.out.println(result.getResponse());
    }
}
