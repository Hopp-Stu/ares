package com.ares.core.model;

import com.ares.core.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SysNoticeRead对象",description = "")
public class SysNoticeRead extends BaseModel{
    @ApiModelProperty("")
    private String noticeId;
    @ApiModelProperty("")
    private String userId;
}
