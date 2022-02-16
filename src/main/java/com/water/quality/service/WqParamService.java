package com.water.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.water.quality.pojo.entity.WqParamEntity;
import com.water.quality.pojo.vo.WqParamVo;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 水质参数表 服务类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
public interface WqParamService extends IService<WqParamEntity> {

    /**
     * 获取水质参数列表
     * @return
     */
    List<WqParamVo> listWithName();

    /**
     * 封装水质参数阈值map 为其他方法提供接口
     */
    Map<String, Map<String, Pair<Double, Double>>> getWqParamMap();

    /**
     * 封装水质超标阈值
     */
    Map<String, Pair<Double, Double>> getWqParamOutMap();
}
