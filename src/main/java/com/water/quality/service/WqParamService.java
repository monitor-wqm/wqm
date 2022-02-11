package com.water.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.water.quality.pojo.entity.WqParam;
import com.water.quality.pojo.vo.WqParamVo;

import java.util.List;

/**
 * <p>
 * 水质参数表 服务类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
public interface WqParamService extends IService<WqParam> {

    /**
     * 获取水质参数列表
     * @return
     */
    List<WqParamVo> listWithName();
}
