package com.water.quality.service;

import com.water.quality.pojo.entity.UserEntity;
import com.water.quality.pojo.entity.WqParamTypeEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 水质参数种类 服务类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
public interface WqParamTypeService extends IService<WqParamTypeEntity> {
    /**
     * 更新监测点信息
     * @param wqParamType
     * @return 受影响行数
     */
    int updateWqParamTypeById(WqParamTypeEntity wqParamType);
}
