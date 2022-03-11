package com.water.quality.job;

import com.water.quality.mapper.MonitorPointDataMapper;
import com.water.quality.mapper.MonitorPointMapper;
import com.water.quality.mapper.PollutionRecordMapper;
import com.water.quality.service.MonitorPointDataService;
import com.water.quality.service.MonitorPointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
class JobServiceTest {

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

    @Autowired
    private JobService jobService;

    @Test
    void judgingMonitorDataIsPollution() {
        jobService.judgingMonitorDataIsPollution();
    }

    @Test
    void addMonitorPointData() {
        for (int i = 0; i < 100; i++) {
            jobService.addMonitorPointData();
        }
    }
}
