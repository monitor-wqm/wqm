package com.water.quality.service;

import com.water.quality.pojo.entity.MonitorPointData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 监测点数据表 服务类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
public interface MonitorPointDataService extends IService<MonitorPointData> {

    /**
     * 通过监测点id 查询监测点的监测数据 todo:后期封装query
     * @param id 监测点id
     * @return 监测点数据列表
     */
    List<MonitorPointData> selectMonitorPointDataByMonitorPointId(Long id);
}
