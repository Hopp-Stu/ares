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

import request from '@/utils/request'

export function getExchangeList() {
    return request({
        url: '/ares/config/getExchanges',
        method: 'get'
    })
}

export function getQueueList() {
    return request({
        url: '/ares/config/getQueues',
        method: 'get'
    })
}

export function getQueueBindings(params) {
    return request({
        url: '/ares/config/getQueueBindings',
        method: 'get',
        params: params
    })
}

export function addExchange(data) {
    return request({
        url: '/ares/config/addExchange',
        method: 'post',
        data: data
    })
}

export function deleteExchange(params) {
    return request({
        url: '/ares/config/deleteExchange',
        method: 'delete',
        data: params
    })
}

export function addQueue(data) {
    return request({
        url: '/ares/config/addQueue',
        method: 'post',
        data: data
    })
}

export function deleteQueue(params) {
    return request({
        url: '/ares/config/deleteQueue',
        method: 'delete',
        data: params
    })
}

export function purgeQueue(params) {
    return request({
        url: '/ares/config/purgeQueue',
        method: 'post',
        data: params
    })
}

export function addBinding(data) {
    return request({
        url: '/ares/config/addBinding',
        method: 'post',
        data: data
    })
}

export function removeBinding(params) {
    return request({
        url: '/ares/config/removeBinding',
        method: 'post',
        data: params
    })
}
