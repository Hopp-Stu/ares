package com.ares.core.model;

import com.ares.core.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SysDept对象",description = "")
public class SysDept extends BaseModel{
    @ApiModelProperty("")
    private String code;
    @ApiModelProperty("")
    private String deptName;
    @ApiModelProperty("")
    private String parentDeptId;

    private String parentDeptName;

    private int childCount;
}
