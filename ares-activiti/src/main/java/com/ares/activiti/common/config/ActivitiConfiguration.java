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

package com.ares.activiti.common.config;


import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @description:
 * @author: Young
 * @date: 2020/08/27
 * @see: com.ares.activiti.common ActivitiConfiguration.java
 **/
@Configuration
public class ActivitiConfiguration {

    @Autowired
    private DataSource dataSource;

    @javax.annotation.Resource
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(){
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(platformTransactionManager);
        configuration.setDatabaseSchemaUpdate("true");
        Resource[] resources = null;
        try {
            resources = new PathMatchingResourcePatternResolver().getResources("classpath*:processes/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.setDeploymentResources(resources);
        return configuration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(){
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration());
        return processEngineFactoryBean;
    }

    @Bean
    public RepositoryService repositoryService() throws Exception {
        return processEngineFactoryBean().getObject().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception {
        return processEngineFactoryBean().getObject().getRuntimeService();
    }

    @Bean
    public TaskService taskService() throws Exception {
        return processEngineFactoryBean().getObject().getTaskService();
    }

    @Bean
    public HistoryService historyService() throws Exception {
        return processEngineFactoryBean().getObject().getHistoryService();
    }
}
