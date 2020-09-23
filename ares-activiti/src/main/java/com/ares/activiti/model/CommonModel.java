package com.ares.activiti.model;

import lombok.Data;
import org.activiti.engine.impl.persistence.entity.ModelEntityImpl;

/**
 * @description:
 * @author: yy
 * @date: 2020/08/27
 * @see: com.ares.activiti.model NewModel.java
 **/
@Data
public class CommonModel extends ModelEntityImpl {
    private String desc;
    private int revisionNext;
    private PersistentState persistentState;

}
