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

// 查询公告列表
export function listNotice(query) {
  return request({
    url: '/ares/system/notice/list',
    method: 'get',
    params: query
  })
}

// 查询公告详细
export function getNotice(noticeId) {
  return request({
    url: '/ares/system/notice/' + noticeId,
    method: 'get'
  })
}

// 新增公告
export function editNotice(data) {
  return request({
    url: '/ares/system/notice/edit',
    method: 'post',
    data: data
  })
}


// 删除公告
export function delNotice(noticeId) {
  return request({
    url: '/ares/system/notice/' + noticeId,
    method: 'delete'
  })
}
