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

// 查询模版列表
export function listTemplate(query) {
  return request({
    url: '/ares/sysTemplate/list',
    method: 'get',
    params: query
  })
}

// 查询模版详细
export function getTemplate(templateId) {
  return request({
    url: '/ares/sysTemplate/' + templateId,
    method: 'get'
  })
}


// 新增模版
export function addTemplate(data) {
  return request({
    url: '/ares/sysTemplate/edit',
    method: 'post',
    data: data
  })
}

// 修改模版
export function updateTemplate(data) {
  return request({
    url: '/ares/sysTemplate/edit',
    method: 'post',
    data: data
  })
}

// 删除模版
export function delTemplate(configId) {
  return request({
    url: '/ares/sysTemplate/' + configId,
    method: 'delete'
  })
}

// 导出模版
export function exportTemplate(query) {
  return request({
    url: '/ares/sysTemplate/export',
    method: 'get',
    params: query
  })
}
