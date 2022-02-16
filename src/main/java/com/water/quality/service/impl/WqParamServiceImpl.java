package com.water.quality.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.water.quality.mapper.WqParamMapper;
import com.water.quality.pojo.entity.WqParamEntity;
import com.water.quality.pojo.vo.WqParamVo;
import com.water.quality.service.WqParamService;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 水质参数表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Service
public class WqParamServiceImpl extends ServiceImpl<WqParamMapper, WqParamEntity> implements WqParamService {

    @Override
    public List<WqParamVo> listWithName() {
        return baseMapper.listWithName();
    }

    @Override
    public Map<String, Map<String, Pair<Double, Double>>> getWqParamMap() {
        //<水质类别名, <水质参数名, <水质参数min,水质参数max>>>
        Map<String, Map<String, Pair<Double, Double>>> map = new LinkedHashMap<>();
        List<WqParamVo> wqParamVos = baseMapper.listWithName();
        //排序之后水质按低到高 Ⅰ类最先放入map
        Collections.sort(wqParamVos, (o1, o2) -> (int) (o1.getId() - o2.getId()));
        wqParamVos.forEach(wqParamVo -> {
            String firstKey = wqParamVo.getWqTypeName();
            String secondKey = wqParamVo.getWqParamTypeName();
            Pair<Double, Double> pair = new Pair<>(wqParamVo.getMinConcentration(), wqParamVo.getMaxConcentration());
            map.computeIfAbsent(firstKey, key -> new HashMap<>());
            map.get(firstKey).put(secondKey, pair);
        });
        return map;
    }

    @Override
    public Map<String, Pair<Double, Double>> getWqParamOutMap() {
        //<水质参数名, <水质参数min,水质参数max>>
        Map<String, Pair<Double, Double>> map = new LinkedHashMap<>();
        List<WqParamVo> wqParamVos = baseMapper.listOutWithName();
        wqParamVos.forEach(wqParamVo -> {
            String firstKey = wqParamVo.getWqParamTypeName();
            Pair<Double, Double> pair = new Pair<>(wqParamVo.getMinConcentration(), wqParamVo.getMaxConcentration());
            map.put(firstKey, pair);
        });
        return map;

    }
}
