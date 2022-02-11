package com.water.quality.service;

import com.water.quality.pojo.entity.PollutionRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 污染记录表 服务类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
public interface PollutionRecordService extends IService<PollutionRecord> {

    /**
     * 通过监测点id 查询监测点的污染记录 todo:后期封装query
     * @param id 监测点id
     * @return 监测点的污染记录列表
     */
    List<PollutionRecord> selectPollutionRecordByMonitorPointId(Long id);
}
