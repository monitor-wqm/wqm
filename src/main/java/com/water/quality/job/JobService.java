package com.water.quality.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.water.quality.mapper.MonitorPointDataMapper;
import com.water.quality.mapper.PollutionRecordMapper;
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
import java.util.Map;


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
    private PollutionRecordMapper pollutionRecordMapper;

    @Transactional
    public void judgingMonitorDataIsPollution() {
        List<Object> monPointIds = monitorPointDataMapper.selectObjs(new LambdaQueryWrapper<MonitorPointDataEntity>()
                .select(MonitorPointDataEntity::getMonitorPointId)
                .groupBy(MonitorPointDataEntity::getMonitorPointId));
        monPointIds.forEach(monPointId -> {
            //k:id, v:ispolllution
            Map<Long, Boolean> monPonitDataIdPollutionMap = monitorPointDataMapper.selectPollutionWithTimesDesc(new Long(monPointId.toString()), count);
            List<Long> pollutionList = new ArrayList<>();
            monPonitDataIdPollutionMap.forEach((id, pollution) -> {
                if (pollution) {
                    pollutionList.add(id);
                }
            });
            MonitorPointEntity monitorPoint = monitorPointService.getById(monPointId.toString());
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
                new LambdaUpdateChainWrapper<>(monitorPointDataMapper)
                        .set(MonitorPointDataEntity::getChecked, 1)
                        .set(MonitorPointDataEntity::getPollution, 0)
                        .eq(MonitorPointDataEntity::getMonitorPointId, monitorPoint.getId())
                        .and(wrapper -> wrapper.set(MonitorPointDataEntity::getPollutionRecordId, pollutionRecord.getId())
                            .set(MonitorPointDataEntity::getPollution, 1)
                            .in(MonitorPointDataEntity::getId, pollutionList)
                        );
            } else {
                new LambdaUpdateChainWrapper<>(monitorPointDataMapper)
                        .set(MonitorPointDataEntity::getChecked, 1)
                        .set(MonitorPointDataEntity::getPollution, 0)
                        .eq(MonitorPointDataEntity::getMonitorPointId, monitorPoint.getId());
            }
            //todo 测试
        });
    }
}
