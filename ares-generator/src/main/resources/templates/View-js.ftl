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

// 查询列表
export function list${entityName}(query) {
    return request({
    url: '/ares//list',
    method: 'get',
    params: query
    })
}

// 查询
export function get${entityName}(id) {
    return request({
    url: '/ares//' + id,
    method: 'get'
    })
}

// 新增
export function add${entityName}(data) {
    return request({
    url: '/ares//edit',
    method: 'post',
    data: data
    })
}

// 修改
export function update${entityName}(data) {
    return request({
    url: '/ares//edit',
    method: 'post',
    data: data
    })
}

// 删除
export function del${entityName}(postId) {
    return request({
    url: '/ares//' + postId,
    method: 'delete'
    })
}

// 导出
export function export${entityName}(query) {
    return request({
    url: '/ares//export',
    method: 'get',
    params: query
    })
}