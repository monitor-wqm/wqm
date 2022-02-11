package com.water.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.water.quality.pojo.entity.MonitorPointData;
import com.water.quality.mapper.MonitorPointDataMapper;
import com.water.quality.service.MonitorPointDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 监测点数据表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Service
public class MonitorPointDataServiceImpl extends ServiceImpl<MonitorPointDataMapper, MonitorPointData> implements MonitorPointDataService {

    @Override
    public List<MonitorPointData> selectMonitorPointDataByMonitorPointId(Long id) {
        return baseMapper.selectList(
                new QueryWrapper<MonitorPointData>().eq("monitor_point_id", id));
    }
}
