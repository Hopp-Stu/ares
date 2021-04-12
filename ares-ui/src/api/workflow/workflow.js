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
import { praseStrEmpty } from "@/utils/ares";

// 查询用户列表
export function list(query) {
  return request({
    url: '/ares/model/list',
    method: 'get',
    params: query
  })
}

// 查询用户详细
export function getWorkflow(modelId) {
  return request({
    url: '/ares/model/' + praseStrEmpty(modelId),
    method: 'get'
  })
}

// 新增用户
export function addWorkflow(data) {
  return request({
    url: '/ares/model/edit',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateWorkflow(data) {
  return request({
    url: '/ares/model/edit',
    method: 'post',
    data: data
  })
}

// 删除用户
export function delWorkflow(modelId) {
  return request({
    url: '/ares/model/' + modelId,
    method: 'delete'
  })
}

export function deployWorkflow(modelId) {
  return request({
    url: '/ares/model/' + modelId + '/deployment',
    method: 'get'
  })
}



