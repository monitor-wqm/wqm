package com.water.quality.mapper;


import com.water.quality.pojo.entity.*;
import com.water.quality.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


/**
 * 造简单数据
 * @author zby
 * @date 2022-02-11 09:32
 */
@Slf4j
@SpringBootTest
class DbMapperTest {

    @Resource
    MonitorPointDataMapper monitorPointDataMapper;

    @Resource
    MonitorPointMapper monitorPointMapper;

    @Resource
    NoticeMapper noticeMapper;

    @Resource
    PollutionRecordMapper pollutionRecordMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    WqParamMapper wqParamMapper;

    @Resource
    WqParamTypeMapper wqParamTypeMapper;

    @Resource
    WqTypeMapper wqTypeMapper;



    @Test
    void monitorPointDataMapper() {
        MonitorPointData monitorPointData = new MonitorPointData();
        monitorPointData.setWqTypeName("水质类别1");
        monitorPointData.setMonitorPointId(1l);
        monitorPointData.setMontiorPointNodeId(1l);
        monitorPointData.setPollutionRecordId(1l);
        monitorPointData.setLat(120.101156);
        monitorPointData.setLng(30.293628);
        monitorPointData.setCity("杭州");
        monitorPointData.setDistrict("余杭");
        monitorPointData.setDepth(20.05);
        monitorPointData.setTurbidity(0.15);
        monitorPointData.setTemperature(20.0);
        monitorPointData.setPh(7.5);
        monitorPointData.setDissolvedOxygen(0.15);
        monitorPointData.setConductivity(0.15);
        monitorPointData.setRedoxPotential(1.0);
        monitorPointData.setNh3n(0.15);
        monitorPointData.setMno4(0.15);
        monitorPointData.setSingleIsPollution(false);
        monitorPointData.setPollution(false);
        monitorPointData.setSite("场地1");
        monitorPointData.setHandled(false);
        monitorPointData.setPhOut(false);
        monitorPointData.setDissolvedOxygenOut(false);
        monitorPointData.setTemperatureOut(false);
        monitorPointData.setRedoxPotentialOut(false);
        monitorPointData.setTurbidityOut(false);
        monitorPointData.setNh3nOut(false);
        monitorPointData.setMno4Out(false);
        monitorPointData.setChecked(false);
        monitorPointData.setCreateTime(LocalDateTime.now());
        monitorPointData.setUpdateTime(LocalDateTime.now());
        monitorPointData.setCreatorId(1l);
        monitorPointData.setEditorId(1l);
        monitorPointDataMapper.insert(monitorPointData);
    }

    @Test
    void monitorPointMapper() {
        MonitorPoint monitorPoint = new MonitorPoint();
        monitorPoint.setLat(120.101156);
        monitorPoint.setLng(30.293628);
        monitorPoint.setWqTypeName("水质类别1");
        monitorPoint.setCity("杭州");
        monitorPoint.setDistrict("余杭");
        monitorPoint.setSite("场地1");
        monitorPoint.setName("监测点1");
        monitorPoint.setSite("场地1");
        monitorPoint.setNodeId(1l);
        monitorPoint.setLatestDepth(20.0);
        monitorPoint.setPollutionStatus(0);
        monitorPoint.setConditionInfo("环境良好");
        monitorPoint.setWaterInfo("水域信息良好");
        monitorPoint.setDepthList("20,30,40");
        monitorPoint.setCreateTime(LocalDateTime.now());
        monitorPoint.setUpdateTime(LocalDateTime.now());
        monitorPoint.setCreatorId(1l);
        monitorPoint.setEditorId(1l);
        monitorPointMapper.insert(monitorPoint);
    }
    @Test
    void noticeMapper() {
        Notice notice = new Notice();
        notice.setNoticeType(1);
        notice.setSiteId(1l);
        notice.setTitle("通知标题1");
        notice.setContent("这是一条通知");
        notice.setCreateTime(LocalDateTime.now());
        notice.setUpdateTime(LocalDateTime.now());
        notice.setCreatorId(1l);
        notice.setEditorId(1l);
        noticeMapper.insert(notice);
    }
    @Test
    void pollutionRecordMapper() {
        PollutionRecord pollutionRecord = new PollutionRecord();
        pollutionRecord.setTitle("ph值");
        pollutionRecord.setStartTime(LocalDateTime.now());
        pollutionRecord.setEndTime(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
        pollutionRecord.setLevel(1);
        pollutionRecord.setPollutionAnalyse("生活垃圾");
        pollutionRecord.setMeasure("清理垃圾");
        pollutionRecord.setMeasureEffect("非常好");
        pollutionRecord.setMonitorPointNodeId(1l);
        pollutionRecord.setMonitorPointId(1l);
        pollutionRecord.setDepth(20.0);
        pollutionRecord.setCreateTime(LocalDateTime.now());
        pollutionRecord.setUpdateTime(LocalDateTime.now());
        pollutionRecord.setCreatorId(1l);
        pollutionRecord.setEditorId(1l);
        pollutionRecordMapper.insert(pollutionRecord);
    }
    @Test
    void userMapper() {
        User user = new User();
        user.setUsername("user1");
        user.setPassword(MD5Util.encrypt("123456"));
        user.setRole(0);
        user.setPermission("1,2,3");
        userMapper.insert(user);
    }
    @Test
    void wqParamMapper() {
        WqParam wqParam = new WqParam();
        wqParam.setWqTypeId(3l);
        wqParam.setWqParamTypeId(1l);
        wqParam.setMaxConcentration(1.0);
        wqParam.setMinConcentration(0.0);
        wqParam.setCreateTime(LocalDateTime.now());
        wqParam.setUpdateTime(LocalDateTime.now());
        wqParam.setCreatorId(1l);
        wqParam.setEditorId(1l);
        wqParamMapper.insert(wqParam);
    }
    @Test
    void wqParamTypeMapper() {
        WqParamType wqParamType = new WqParamType();
        wqParamType.setName("ph");
        wqParamType.setUnit("ph");
        wqParamType.setSense("酸碱度");
        wqParamType.setUpdateTime(LocalDateTime.now());
        wqParamType.setEditorId(1l);
        wqParamTypeMapper.insert(wqParamType);
    }
    @Test
    void wqTypeMapper() {
        WqType wqType = new WqType();
        wqType.setName("水质类别1");
        wqType.setCreateTime(LocalDateTime.now());
        wqType.setUpdateTime(LocalDateTime.now());
        wqType.setCreatorId(1l);
        wqType.setEditorId(1l);
        wqTypeMapper.insert(wqType);
    }
}
