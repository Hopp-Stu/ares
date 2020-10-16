package com.ares.core.model;

import com.ares.core.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SysPost对象",description = "")
public class SysPost extends BaseModel{
    @ApiModelProperty("")
    private String postCode;
    @ApiModelProperty("")
    private String postName;
}
