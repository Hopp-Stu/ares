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

package com.ares.system.common.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Young 2020/4/21
 **/
public class ShiroCacheManager implements CacheManager {

    private long globExpire = 1800;

    public ShiroCacheManager() {

    }

    public ShiroCacheManager(long expire) {
        this.globExpire = expire;
    }

    @Resource(name = "redisTemplateObj")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        if (null == s) {
            return null;
        }
        return new ShiroCache<K, V>(s, redisTemplate, globExpire);
    }


}
