package com.ares.activiti.model;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: yy
 * @date: 2020/08/28
 * @see: com.ares.activiti.model PersistentState.java
 **/
@Data
public class PersistentState {
    private String category;
    private Date createTime;
    private String deploymentId;
    private String editorSourceExtraValueId;
    private String editorSourceValueId;
    private String key;
    private Date lastUpdateTime;
    private String metaInfo;
    private String name;
    private int version;
}
