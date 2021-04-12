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

// 查询部门列表
export function listDept(query) {
  return request({
    url: '/ares/sysDept/list',
    method: 'get',
    params: query
  })
}

// 查询部门详细
export function getDept(deptId) {
  return request({
    url: '/ares/sysDept/' + deptId,
    method: 'get'
  })
}

// 查询部门下拉树结构
export function treeselect() {
  return request({
    url: '/ares/sysDept/treeselect',
    method: 'get'
  })
}


// 新增部门
export function addDept(data) {
  return request({
    url: '/ares/sysDept/edit',
    method: 'post',
    data: data
  })
}

// 修改部门
export function updateDept(data) {
  return request({
    url: '/ares/sysDept/edit',
    method: 'post',
    data: data
  })
}

// 删除部门
export function delDept(deptId) {
  return request({
    url: '/ares/sysDept/' + deptId,
    method: 'delete'
  })
}
