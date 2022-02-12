package com.water.quality.service;

import com.water.quality.pojo.entity.MonitorPointDataEntity;
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
public interface MonitorPointDataService extends IService<MonitorPointDataEntity> {

    /**
     * 通过监测点id 查询监测点的监测数据 todo:后期封装query
     * @param id 监测点id
     * @return 监测点数据列表
     */
    List<MonitorPointDataEntity> selectMonitorPointDataByMonitorPointId(Long id);

    /**
     * 通过监测点id 查询监测点的最新监测数据
     * @param id
     * @return
     */
    MonitorPointDataEntity selectLatestMonitorPointDataByMonitorPointId(Long id);
}
