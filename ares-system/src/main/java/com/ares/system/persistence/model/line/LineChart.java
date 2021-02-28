package com.ares.system.persistence.model.line;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/14
 * @see: com.ares.system.persistence.model LineChart.java
 **/
@Data
public class LineChart {
    private XAxis xAxis;
    private Legend legend;
    private List<Series> series;
}