package com.water.quality.service;

import com.water.quality.pojo.entity.MonitorPointEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 监测点表 服务类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
public interface MonitorPointService extends IService<MonitorPointEntity> {

    /**
     * 更新监测点信息
     * @param monitorPoint
     * @return 受影响行数
     */
    int updateMonitorPointById(MonitorPointEntity monitorPoint);
}
