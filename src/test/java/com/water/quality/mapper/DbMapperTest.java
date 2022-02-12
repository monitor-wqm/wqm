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
        MonitorPointDataEntity monitorPointDataEntity = new MonitorPointDataEntity();
        monitorPointDataEntity.setWqTypeName("水质类别1");
        monitorPointDataEntity.setMonitorPointId(1l);
        monitorPointDataEntity.setMontiorPointNodeId(1l);
        monitorPointDataEntity.setPollutionRecordId(1l);
        monitorPointDataEntity.setLat(120.101156);
        monitorPointDataEntity.setLng(30.293628);
        monitorPointDataEntity.setCity("杭州");
        monitorPointDataEntity.setDistrict("余杭");
        monitorPointDataEntity.setDepth(20.05);
        monitorPointDataEntity.setTurbidity(0.15);
        monitorPointDataEntity.setTemperature(20.0);
        monitorPointDataEntity.setPh(7.5);
        monitorPointDataEntity.setDissolvedOxygen(0.15);
        monitorPointDataEntity.setConductivity(0.15);
        monitorPointDataEntity.setRedoxPotential(1.0);
        monitorPointDataEntity.setNh3n(0.15);
        monitorPointDataEntity.setMno4(0.15);
        monitorPointDataEntity.setSingleIsPollution(false);
        monitorPointDataEntity.setPollution(false);
        monitorPointDataEntity.setSite("场地1");
        monitorPointDataEntity.setHandled(false);
        monitorPointDataEntity.setPhOut(false);
        monitorPointDataEntity.setDissolvedOxygenOut(false);
        monitorPointDataEntity.setTemperatureOut(false);
        monitorPointDataEntity.setRedoxPotentialOut(false);
        monitorPointDataEntity.setTurbidityOut(false);
        monitorPointDataEntity.setNh3nOut(false);
        monitorPointDataEntity.setMno4Out(false);
        monitorPointDataEntity.setChecked(false);
        monitorPointDataEntity.setCreateTime(LocalDateTime.now());
        monitorPointDataEntity.setUpdateTime(LocalDateTime.now());
        monitorPointDataEntity.setCreatorId(1l);
        monitorPointDataEntity.setEditorId(1l);
        monitorPointDataMapper.insert(monitorPointDataEntity);
    }

    @Test
    void monitorPointMapper() {
        MonitorPointEntity monitorPointEntity = new MonitorPointEntity();
        monitorPointEntity.setLat(120.101156);
        monitorPointEntity.setLng(30.293628);
        monitorPointEntity.setWqTypeName("水质类别1");
        monitorPointEntity.setCity("杭州");
        monitorPointEntity.setDistrict("余杭");
        monitorPointEntity.setSite("场地1");
        monitorPointEntity.setName("监测点1");
        monitorPointEntity.setSite("场地1");
        monitorPointEntity.setNodeId(1l);
        monitorPointEntity.setLatestDepth(20.0);
        monitorPointEntity.setPollutionStatus(0);
        monitorPointEntity.setConditionInfo("环境良好");
        monitorPointEntity.setWaterInfo("水域信息良好");
        monitorPointEntity.setDepthList("20,30,40");
        monitorPointEntity.setCreateTime(LocalDateTime.now());
        monitorPointEntity.setUpdateTime(LocalDateTime.now());
        monitorPointEntity.setCreatorId(1l);
        monitorPointEntity.setEditorId(1l);
        monitorPointMapper.insert(monitorPointEntity);
    }
    @Test
    void noticeMapper() {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeType(1);
        noticeEntity.setSiteId(1l);
        noticeEntity.setTitle("通知标题1");
        noticeEntity.setContent("这是一条通知");
        noticeEntity.setCreateTime(LocalDateTime.now());
        noticeEntity.setUpdateTime(LocalDateTime.now());
        noticeEntity.setCreatorId(1l);
        noticeEntity.setEditorId(1l);
        noticeMapper.insert(noticeEntity);
    }
    @Test
    void pollutionRecordMapper() {
        PollutionRecordEntity pollutionRecordEntity = new PollutionRecordEntity();
        pollutionRecordEntity.setTitle("ph值");
        pollutionRecordEntity.setStartTime(LocalDateTime.now());
        pollutionRecordEntity.setEndTime(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
        pollutionRecordEntity.setLevel(1);
        pollutionRecordEntity.setPollutionAnalyse("生活垃圾");
        pollutionRecordEntity.setMeasure("清理垃圾");
        pollutionRecordEntity.setMeasureEffect("非常好");
        pollutionRecordEntity.setMonitorPointNodeId(1l);
        pollutionRecordEntity.setMonitorPointId(1l);
        pollutionRecordEntity.setDepth(20.0);
        pollutionRecordEntity.setCreateTime(LocalDateTime.now());
        pollutionRecordEntity.setUpdateTime(LocalDateTime.now());
        pollutionRecordEntity.setCreatorId(1l);
        pollutionRecordEntity.setEditorId(1l);
        pollutionRecordMapper.insert(pollutionRecordEntity);
    }
    @Test
    void userMapper() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("user1");
        userEntity.setPassword(MD5Util.encrypt("123456"));
        userEntity.setRole(0);
        userEntity.setPermission("1,2,3");
        userMapper.insert(userEntity);
    }
    @Test
    void wqParamMapper() {
        WqParamEntity wqParamEntity = new WqParamEntity();
        wqParamEntity.setWqTypeId(3l);
        wqParamEntity.setWqParamTypeId(1l);
        wqParamEntity.setMaxConcentration(1.0);
        wqParamEntity.setMinConcentration(0.0);
        wqParamEntity.setCreateTime(LocalDateTime.now());
        wqParamEntity.setUpdateTime(LocalDateTime.now());
        wqParamEntity.setCreatorId(1l);
        wqParamEntity.setEditorId(1l);
        wqParamMapper.insert(wqParamEntity);
    }
    @Test
    void wqParamTypeMapper() {
        WqParamTypeEntity wqParamTypeEntity = new WqParamTypeEntity();
        wqParamTypeEntity.setName("ph");
        wqParamTypeEntity.setUnit("ph");
        wqParamTypeEntity.setSense("酸碱度");
        wqParamTypeEntity.setUpdateTime(LocalDateTime.now());
        wqParamTypeEntity.setEditorId(1l);
        wqParamTypeMapper.insert(wqParamTypeEntity);
    }
    @Test
    void wqTypeMapper() {
        WqTypeEntity wqTypeEntity = new WqTypeEntity();
        wqTypeEntity.setName("水质类别1");
        wqTypeEntity.setCreateTime(LocalDateTime.now());
        wqTypeEntity.setUpdateTime(LocalDateTime.now());
        wqTypeEntity.setCreatorId(1l);
        wqTypeEntity.setEditorId(1l);
        wqTypeMapper.insert(wqTypeEntity);
    }
}
