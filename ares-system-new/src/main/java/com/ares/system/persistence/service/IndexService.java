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

package com.ares.system.persistence.service;

import com.ares.core.persistence.model.base.Constants;
import com.ares.core.utils.DateUtils;
import com.ares.redis.utils.RedisUtil;
import com.ares.system.persistence.dao.IIndexDao;
import com.ares.system.persistence.model.PanelGroup;
import com.ares.system.persistence.model.line.Legend;
import com.ares.system.persistence.model.line.LineChart;
import com.ares.system.persistence.model.line.Series;
import com.ares.system.persistence.model.line.XAxis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: Young
 * @date: 2020/09/14
 * @see: com.ares.system.persistence.service IndexService.java
 **/
@Slf4j
@Service
public class IndexService {

    @Resource
    private IIndexDao indexDao;

    public List<PanelGroup> getPanelGroup() {
        List<PanelGroup> panelGroups = new ArrayList<>();
        PanelGroup panelGroup = PanelGroup.newInstance()
                .setName("在线人数")
                .setType("newVisitis")
                .setIcon("peoples")
                .setStartVal(0)
                .setEndVal(getOnlinePeople())
                .setDuration(2000).build();
        panelGroups.add(panelGroup);
        panelGroup = PanelGroup.newInstance()
                .setName("消息")
                .setType("messages")
                .setIcon("message")
                .setStartVal(0)
                .setEndVal(81212)
                .setDuration(3000).build();
        panelGroups.add(panelGroup);
        panelGroup = PanelGroup.newInstance()
                .setName("定时任务")
                .setType("jobs")
                .setIcon("job")
                .setStartVal(0)
                .setEndVal(indexDao.getJobNumber())
                .setDuration(2000).build();
        panelGroups.add(panelGroup);
        panelGroup = PanelGroup.newInstance()
                .setName("日志")
                .setType("logs")
                .setIcon("log")
                .setStartVal(0)
                .setEndVal(indexDao.getLogNumber())
                .setDuration(2000).build();
        panelGroups.add(panelGroup);
        return panelGroups;
    }


    public Map<String, Object> getLineChartData() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("expectedData", Arrays.asList(100, 120, 161, 134, 105, 160, 165));
        data.put("actualData", Arrays.asList(120, 82, 91, 154, 162, 140, 145));
        map.put("newVisitis", data);

        data = new HashMap<>();
        data.put("expectedData", Arrays.asList(200, 192, 120, 144, 160, 130, 140));
        data.put("actualData", Arrays.asList(180, 160, 151, 106, 145, 150, 130));
        map.put("messages", data);

        data = new HashMap<>();
        data.put("expectedData", Arrays.asList(80, 100, 121, 104, 105, 90, 100));
        data.put("actualData", Arrays.asList(120, 90, 100, 138, 142, 130, 130));
        map.put("jobs", data);

        data = new HashMap<>();
        data.put("expectedData", Arrays.asList(130, 140, 141, 142, 145, 150, 160));
        data.put("actualData", Arrays.asList(120, 82, 91, 154, 162, 140, 130));
        map.put("shoppings", data);

        return map;
    }

    private int getOnlinePeople() {
        String pattern = Constants.LOGIN_INFO + "*";
        Set<String> keys = RedisUtil.getKeysByPattern(pattern);
        return keys.size();
    }

    public LineChart getLineChart() {
        LineChart lineChart = new LineChart();
        XAxis xAxis = new XAxis();
        Legend legend = new Legend();
        List<Series> series = new ArrayList<>();

        xAxis.setData(getXLine());
        xAxis.setBoundaryGap(false);
        xAxis.isShow(false);
        lineChart.setXAxis(xAxis);
        legend.setData(new String[]{"随机数1", "随机数2"});
        lineChart.setLegend(legend);

        Series ser = new Series();
        ser.setName("随机数1");
        ser.buildItemStyle("#FF005A", 2, "#FF005A", null);
//        ser.setItemStyle(new HashMap<String, Object>() {{
//            put("normal", new HashMap<String, Object>() {{
//                put("color", "#FF005A");
//                put("lineStyle", new HashMap<String, Object>() {{
//                    put("color", "#FF005A");
//                    put("width", "2");
//                }});
//            }});
//        }});
        ser.setSmooth(true);
        ser.setType("line");
        ser.setData(buildData());
        ser.setAnimationDuration(2800);
        ser.setAnimationEasing("cubicInOut");
        series.add(ser);

        ser = new Series();
        ser.setName("随机数2");
        ser.buildItemStyle("#3888fa", 2, "#3888fa", "#f3f8ff");
//        ser.setItemStyle(new HashMap<String, Object>() {{
//            put("normal", new HashMap<String, Object>() {{
//                put("color", "#3888fa");
//                put("lineStyle", new HashMap<String, Object>() {{
//                    put("color", "#3888fa");
//                    put("width", "2");
//                }});
//                put("areaStyle", new HashMap<String, Object>() {{
//                    put("color", "#f3f8ff");
//                }});
//            }});
//        }});
        ser.setSmooth(true);
        ser.setType("line");
        ser.setData(buildData());
        ser.setAnimationDuration(2800);
        ser.setAnimationEasing("quadraticOut");
        series.add(ser);
        lineChart.setSeries(series);
        return lineChart;
    }

    private String[] getXLine() {
        Calendar calendar = Calendar.getInstance();
        List<String> times = new ArrayList<>(7);
        times.add(DateUtils.format(calendar.getTime(), DateUtils.DATE_TIME_MIN_PATTERN));
        for (int i = 0; i < 6; i++) {
            String time = getTime(calendar);
            times.add(time);
        }
        times.sort(Comparator.naturalOrder());
        String[] xLine = new String[7];
        times.toArray(xLine);
        return xLine;
    }

    private String getTime(Calendar current) {
        current.add(Calendar.MINUTE, -10);
        Date before = current.getTime();
        String beforeTime = DateUtils.format(before, DateUtils.DATE_TIME_MIN_PATTERN);
        return beforeTime;
    }

    private Integer[] buildData() {
        Integer[] data = new Integer[7];
        for (int i = 0; i < 7; i++) {
            data[i] = (int) Math.ceil(Math.random() * 100000);
        }
        return data;
    }
}
