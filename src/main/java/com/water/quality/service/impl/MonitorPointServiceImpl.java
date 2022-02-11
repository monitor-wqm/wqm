package com.water.quality.service.impl;

import com.water.quality.pojo.entity.MonitorPoint;
import com.water.quality.mapper.MonitorPointMapper;
import com.water.quality.service.MonitorPointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 监测点表 服务实现类
 * </p>
 *
 * @author 
 * @since 2022-02-10
 */
@Service
public class MonitorPointServiceImpl extends ServiceImpl<MonitorPointMapper, MonitorPoint> implements MonitorPointService {

}
