package com.water.quality.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 *
 * @Author: zby
 * @Date: 2021/8/17 15:23
 */
@Slf4j
@Component
public class JudgingPollutionJob {

    @Autowired
    private JobService jobService;

    @Scheduled(cron = "${pollution.cron}")
    public void runJudging(){
        jobService.judgingMonitorDataIsPollution();
    }

    /**
     * 模拟新增监测点数据
     */
    @Scheduled(cron = "${data.cron}")
    public void addData(){
        jobService.addMonitorPointData();
    }
}
