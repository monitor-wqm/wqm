package com.water.quality.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.water.quality.mapper.MonitorPointDataMapper;
import com.water.quality.mapper.MonitorPointMapper;
import com.water.quality.mapper.PollutionRecordMapper;
import com.water.quality.pojo.dto.MonPointDataPollutionDto;
import com.water.quality.pojo.entity.MonitorPointDataEntity;
import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.pojo.entity.PollutionRecordEntity;
import com.water.quality.service.MonitorPointDataService;
import com.water.quality.service.MonitorPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: zby
 * @Date: 2021/8/17
 */
@Slf4j
@Service
public class JobService {

    /**
     * 每次定时任务检查 同一监测点的count条记录
     */
    @Value("${pollution.count}")
    private int count;

    /**
     * 检查的记录中有times条 则判断为污染记录
     */
    @Value("${pollution.times}")
    private int times;

    @Autowired
    private MonitorPointDataService monitorPointDataService;

    @Resource
    private MonitorPointDataMapper monitorPointDataMapper;

    @Autowired
    private MonitorPointService monitorPointService;

    @Resource
    private MonitorPointMapper monitorPointMapper;

    @Resource
    private PollutionRecordMapper pollutionRecordMapper;

    @Transactional
    public void judgingMonitorDataIsPollution() {
        log.info("JOB-runJudging, 开始执行任务");
        List<Object> monPointIds = monitorPointDataMapper.selectObjs(new LambdaQueryWrapper<MonitorPointDataEntity>()
                .select(MonitorPointDataEntity::getMonitorPointId)
                .groupBy(MonitorPointDataEntity::getMonitorPointId));
        if (monPointIds == null || monPointIds.size() < 1) {
            return;
        }
        monPointIds.forEach(monPointId -> {
            //k:id, v:ispolllution
            List<MonPointDataPollutionDto> dataPollutionList = monitorPointDataMapper.selectPollutionWithTimesDesc(new Long(monPointId.toString()), count);
            List<Long> currentCheckList = dataPollutionList.stream().map(MonPointDataPollutionDto::getId).collect(Collectors.toList());
            //判断是否数据不够 数据不够做处理就跳过等待下次处理
            if (dataPollutionList == null || dataPollutionList.size() < count) {
                return;
            }
            List<Long> pollutionList = new ArrayList<>();
            dataPollutionList.forEach(dataPollutionDto -> {
                if (dataPollutionDto.getPollutioned()) {
                    pollutionList.add(dataPollutionDto.getId());
                }
            });
            MonitorPointEntity monitorPoint = monitorPointService.getById(monPointId.toString());
            if (monitorPoint == null) {
                return;
            }
            //判断是否为污染
            if (pollutionList.size() >= times) {
                PollutionRecordEntity pollutionRecord = new PollutionRecordEntity();
                pollutionRecord.setMonitorPointNodeId(monitorPoint.getNodeId());
                pollutionRecord.setMonitorPointId(monitorPoint.getId());
                pollutionRecord.setCreateTime(LocalDateTime.now());
                pollutionRecord.setCreatorId(1l);
                pollutionRecord.setUpdateTime(LocalDateTime.now());
                pollutionRecord.setEditorId(1l);
                pollutionRecordMapper.insert(pollutionRecord);
                log.info("JOB-runJudging, 新增污染记录-监测点id: {}, 污染记录id: {}", monitorPoint.getId(), pollutionRecord.getId());
                monitorPointDataMapper.update(null, new LambdaUpdateWrapper<MonitorPointDataEntity>()
                        .set(MonitorPointDataEntity::getChecked, 1)
                        .set(MonitorPointDataEntity::getPollution, 0)
                        .in(MonitorPointDataEntity::getId, currentCheckList));
                monitorPointDataMapper.update(null, new LambdaUpdateWrapper<MonitorPointDataEntity>()
                        .set(MonitorPointDataEntity::getChecked, 1)
                        .set(MonitorPointDataEntity::getPollution, 1)
                        .set(MonitorPointDataEntity::getPollutionRecordId, pollutionRecord.getId())
                        .in(pollutionList.size() > 0, MonitorPointDataEntity::getId, pollutionList));
                monitorPointMapper.update(null, new LambdaUpdateWrapper<MonitorPointEntity>()
                        .set(MonitorPointEntity::getPollutionStatus, 1)
                        .eq(MonitorPointEntity::getId, monitorPoint.getId()));
            } else {
                monitorPointDataMapper.update(null, new LambdaUpdateWrapper<MonitorPointDataEntity>()
                        .set(MonitorPointDataEntity::getChecked, 1)
                        .set(MonitorPointDataEntity::getPollution, 0)
                        .in(MonitorPointDataEntity::getId, currentCheckList));
                log.info("JOB-runJudging, 监测点最近无污染-监测点id: {}", monitorPoint.getId());
            }
        });
        log.info("JOB-runJudging, 结束执行任务");
    }

    public void addMonitorPointData() {
        log.info("JOB-addData, 开始执行任务");
        //新增监测点数据 Ⅲ类 不是单参数污染数据
        List<MonitorPointEntity> list = monitorPointService.list();
        list.forEach(moitorPoint -> {
            MonitorPointDataEntity monitorPointData = new MonitorPointDataEntity();
            monitorPointData.setMonitorPointId(moitorPoint.getId());
            monitorPointData.setMontiorPointNodeId(moitorPoint.getNodeId());
            monitorPointData.setLat(120.101156D);
            monitorPointData.setLng(30.293628D);
            monitorPointData.setCity("杭州");
            monitorPointData.setDistrict("余杭");
            monitorPointData.setDepth(20.0);
            monitorPointData.setTurbidity(17.0);
            monitorPointData.setTemperature(25.0);
            monitorPointData.setPh(7.2);
            monitorPointData.setDissolvedOxygen(5.2);
            monitorPointData.setConductivity(11.5);
            monitorPointData.setRedoxPotential(61.5);
            monitorPointData.setNh3n(0.3);
            monitorPointData.setMno4(5.0);
            monitorPointData.setSite("模拟场地");
            monitorPointData.setCreatorId(1l);
            monitorPointData.setEditorId(1l);
            monitorPointDataService.insertMonitorPointData(monitorPointData);
            log.info("JOB-addData, 新增一条监测点数据-监测点id: {}, 监测点数据id: {}, 监测点数据: {}", moitorPoint.getId(), monitorPointData.getId(), JSON.toJSONString(monitorPointData));
        });
        log.info("JOB-addData, 结束执行任务");
    }
}
