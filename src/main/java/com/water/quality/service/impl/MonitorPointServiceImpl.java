package com.water.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.water.quality.asserts.Assert;
import com.water.quality.context.UserContext;
import com.water.quality.mapper.MonitorPointMapper;
import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.MonitorPointService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 监测点表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Service
public class MonitorPointServiceImpl extends ServiceImpl<MonitorPointMapper, MonitorPointEntity> implements MonitorPointService {

    @Override
    public int updateMonitorPointById(MonitorPointEntity monitorPoint) {
        //更新监测点时 id不能为空
        Assert.notNull(monitorPoint.getId(), ResponseEnum.ID_NULL_ERROR);

        MonitorPointEntity monitorPointEntity = baseMapper.selectOne(new LambdaQueryWrapper<MonitorPointEntity>()
                .eq(MonitorPointEntity::getId, monitorPoint.getId()));
        //数据库必须存在当前id
        Assert.notNull(monitorPointEntity, ResponseEnum.DATABASE_NULL_ERROR);

        //更新监测点时 name不能为空
        Assert.notEmpty(monitorPoint.getName(), ResponseEnum.COMMIT_NULL_ERROR);
        Integer count = baseMapper.selectCount(new LambdaQueryWrapper<MonitorPointEntity>()
                .eq(MonitorPointEntity::getName, monitorPoint.getName()));
        //如果name修改了，那么监测点name不能重复
        if (!monitorPointEntity.getName().equals(monitorPoint.getName())) {
            Assert.isTrue(count < 0, ResponseEnum.NAME_REPEAT_ERROR);
        }
        return baseMapper.update(monitorPoint, new LambdaUpdateWrapper<MonitorPointEntity>()
                .eq(MonitorPointEntity::getId, monitorPoint.getId())
                .set(MonitorPointEntity::getUpdateTime, new Date())
                .set(MonitorPointEntity::getEditorId, UserContext.getUserId()));
    }
}
