package com.water.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.water.quality.asserts.Assert;
import com.water.quality.context.UserContext;
import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.pojo.entity.WqParamTypeEntity;
import com.water.quality.mapper.WqParamTypeMapper;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.WqParamTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 水质参数种类 服务实现类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Service
public class WqParamTypeServiceImpl extends ServiceImpl<WqParamTypeMapper, WqParamTypeEntity> implements WqParamTypeService {

    @Override
    public int updateWqParamTypeById(WqParamTypeEntity wqParamType) {
        //id不能为空
        Assert.notNull(wqParamType.getId(), ResponseEnum.ID_NULL_ERROR);

        WqParamTypeEntity wqParamTypeEntity = baseMapper.selectOne(new LambdaQueryWrapper<WqParamTypeEntity>()
                .eq(WqParamTypeEntity::getId, wqParamType.getId()));
        //数据库必须存在当前id
        Assert.notNull(wqParamTypeEntity, ResponseEnum.DATABASE_NULL_ERROR);

        //更新监测点时 name不能为空
        Assert.notEmpty(wqParamType.getName(), ResponseEnum.COMMIT_NULL_ERROR);
        Integer count = baseMapper.selectCount(new LambdaQueryWrapper<WqParamTypeEntity>()
                .eq(WqParamTypeEntity::getName, wqParamType.getName()));
        if (!wqParamTypeEntity.getName().equals(wqParamType.getName())){
            Assert.isTrue(count < 0, ResponseEnum.NAME_REPEAT_ERROR);
        }
        return baseMapper.update(wqParamType, new LambdaUpdateWrapper<WqParamTypeEntity>()
                .eq(WqParamTypeEntity::getId, wqParamType.getId())
                .set(WqParamTypeEntity::getUpdateTime, new Date())
                .set(WqParamTypeEntity::getEditorId, UserContext.getUserId()));
    }
}

