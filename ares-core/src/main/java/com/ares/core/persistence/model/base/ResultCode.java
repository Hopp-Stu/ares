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

package com.ares.core.persistence.model.base;

import lombok.Getter;

/**
 * @description:
 * @author: Young 2020/03/30
 **/
@Getter
public enum ResultCode {

    SUCCESS(200, "成功"),
    FAILED(500, "错误"),
    VALIDATE_FAILED(10001, "参数校验失败"),
    UNKNOWN(10000, "未知错误"),
    NOAUTH(401, "用户没有权限"),
    NOLOGIN(1000, "用户没有登录!"),
    NOMODEL(2000,"模型不存在"),
    NOFLOW(2001,"请先设计流程定义并成功保存，再进行部署"),
    ERRORFLOWDEFINITION(2002,"流程定义不符要求，请至少设计一条主线流程");
    private int code;

    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
