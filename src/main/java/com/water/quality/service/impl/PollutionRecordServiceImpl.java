package com.water.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.water.quality.mapper.PollutionRecordMapper;
import com.water.quality.pojo.entity.PollutionRecordEntity;
import com.water.quality.service.PollutionRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 污染记录表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Service
public class PollutionRecordServiceImpl extends ServiceImpl<PollutionRecordMapper, PollutionRecordEntity> implements PollutionRecordService {

    @Override
    public List<PollutionRecordEntity> selectPollutionRecordByMonitorPointId(Long id) {
        return baseMapper.selectList(
                new QueryWrapper<PollutionRecordEntity>().eq("monitor_point_id", id));
    }
}
