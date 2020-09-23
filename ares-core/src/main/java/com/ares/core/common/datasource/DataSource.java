package com.ares.core.common.datasource;

import java.lang.annotation.*;

/**
 * @description:
 * @author: yy
 * @date: 2020/07/10
 * @see: com.system.springbootv1.common.datasource DataSource.java
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    DataSourceType value() default DataSourceType.MYSQL;
}
