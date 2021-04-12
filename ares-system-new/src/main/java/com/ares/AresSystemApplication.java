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

package com.ares;

import com.ares.config.mybatis.MyBatisConfig;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


/**
 * @description:
 * @author: Young
 * @date: 2020/10/19
 * @see: com.ares.system AresSystemApplication.java
 **/
@Import({MyBatisConfig.class})
@MapperScan("com.ares.**.dao")
@SpringBootApplication(exclude = {RocketMQAutoConfiguration.class, DataSourceAutoConfiguration.class
       // , SecurityAutoConfiguration.class
})
public class AresSystemApplication extends SpringBootServletInitializer {

    /**
     * 打war包所需配置
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AresSystemApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AresSystemApplication.class, args);
        System.out.println("         ______                                     __    __ ");
        System.out.println("		/      \\                                   /  |  /  |");
        System.out.println("       /$$$$$$  |  ______   _____  ____    ______  $$ | _$$ |_     _____");
        System.out.println("       $$ |  $$/  /      \\ /     \\/   \\  /     \\   $$ |/ $$   |   /     \\");
        System.out.println("       $$ |      /$$$$$$  |$$$$$$ $$$$  |/$$$$$$  |$$ |$$$$$$/   /$$$$$$ |");
        System.out.println("	   $$ |   __ $$ |  $$ |$$ | $$ | $$ |$$ |  $$ |$$ |  $$ | __ $$    $$|");
        System.out.println("       $$ \\__/  |$$ \\__$$ |$$ | $$ | $$ |$$ |__$$ |$$ |  $$ |/  |$$$$$$$$/");
        System.out.println("	   $$    $$/ $$    $$/ $$ | $$ | $$ |$$    $$/ $$ |  $$  $$/ $$      |");
        System.out.println("	   $$$$$$/    $$$$$$/  $$/  $$/  $$/ $$$$$$$/  $$/    $$$$/   $$$$$$$/");
        System.out.println("				                         $$ |");
        System.out.println("		                                 $$ |");
        System.out.println("		                                 $$/");
    }
}
