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

// 登录方法
export function login(username, password, code, uuid, rememberMe) {
  const data = {
    username,
    password,
    code,
    uuid,
    rememberMe
  }
  return request({
    url: '/ares/login',
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/ares/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/ares/loginOut',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/ares/kaptcha',
    method: 'get'
  })
}
