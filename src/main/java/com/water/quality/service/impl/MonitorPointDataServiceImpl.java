package com.water.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.water.quality.asserts.Assert;
import com.water.quality.constants.WqParamTypeConstant;
import com.water.quality.enums.WqTypeEnum;
import com.water.quality.context.UserContext;
import com.water.quality.mapper.MonitorPointDataMapper;
import com.water.quality.pojo.entity.MonitorPointDataEntity;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.MonitorPointDataService;
import com.water.quality.service.WqParamService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 监测点数据表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Service
public class MonitorPointDataServiceImpl extends ServiceImpl<MonitorPointDataMapper, MonitorPointDataEntity> implements MonitorPointDataService {


    @Autowired
    private WqParamService wqParamService;

    @Override
    public List<MonitorPointDataEntity> selectMonitorPointDataByMonitorPointId(Long id) {
        return baseMapper.selectList(
                new QueryWrapper<MonitorPointDataEntity>().eq("monitor_point_id", id));
    }

    @Override
    public MonitorPointDataEntity selectLatestMonitorPointDataByMonitorPointId(Long id) {

        return baseMapper.selectOne(new QueryWrapper<MonitorPointDataEntity>()
                .eq("monitor_point_id", id)
                .orderByDesc("create_time")
                .last("Limit 1"));
    }

    @Override
    public int insertMonitorPointData(MonitorPointDataEntity monitorPointDataEntity) {
        Assert.isNull(monitorPointDataEntity.getId(), ResponseEnum.ADD_ID_NOT);

        //水质类别和对应参数阈值map
        Map<String, Map<String, Pair<Double, Double>>> wqParamMap = wqParamService.getWqParamMap();

        //暂定Ⅲ类水质超标 Ⅳ类水质污染
        doCountWqType(monitorPointDataEntity, wqParamMap);

        doCountWqParamTypeOut(monitorPointDataEntity, wqParamMap);

        doCountExInfo(monitorPointDataEntity);

        return baseMapper.insert(monitorPointDataEntity);
    }

    /**
     * 封装创建附加信息 如：创建人创建时间等
     * @param monitorPointDataEntity 监测数据实体
     */
    private void doCountExInfo(MonitorPointDataEntity monitorPointDataEntity) {
        monitorPointDataEntity.setCreateTime(LocalDateTime.now());
        monitorPointDataEntity.setUpdateTime(LocalDateTime.now());
        monitorPointDataEntity.setCreatorId(UserContext.getUserId());
        monitorPointDataEntity.setEditorId(UserContext.getUserId());
    }

    /**
     * 计算水质参数超标信息
     * @param monitorPointDataEntity 检测数据实体
     * @param wqParamMap
     */
    private void doCountWqParamTypeOut(MonitorPointDataEntity monitorPointDataEntity, Map<String, Map<String, Pair<Double, Double>>> wqParamMap) {
//        monitorPointDataEntity.setPhOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_PH, monitorPointDataEntity.getPh(), wqParamMap));
//        monitorPointDataEntity.setDissolvedOxygenOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_DISSOLVEDOXYGEN, monitorPointDataEntity.getDissolvedOxygen(), wqParamMap));
//        monitorPointDataEntity.setNh3nOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_NH3N, monitorPointDataEntity.getNh3n(), wqParamMap));
//        monitorPointDataEntity.setMno4Out(countWqParamTypeOut(WqParamTypeConstant.TYPE_MNO4, monitorPointDataEntity.getMno4(), wqParamMap));
        Map<String, Pair<Double, Double>> wqParamOutMap = wqParamService.getWqParamOutMap();
        monitorPointDataEntity.setPhOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_PH, monitorPointDataEntity.getPh(), wqParamOutMap));
        monitorPointDataEntity.setDissolvedOxygenOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_DISSOLVEDOXYGEN, monitorPointDataEntity.getDissolvedOxygen(), wqParamOutMap));
        monitorPointDataEntity.setNh3nOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_NH3N, monitorPointDataEntity.getNh3n(), wqParamOutMap));
        monitorPointDataEntity.setMno4Out(countWqParamTypeOut(WqParamTypeConstant.TYPE_MNO4, monitorPointDataEntity.getMno4(), wqParamOutMap));
        monitorPointDataEntity.setMno4Out(countWqParamTypeOut(WqParamTypeConstant.TYPE_TURBIDITY, monitorPointDataEntity.getMno4(), wqParamOutMap));
        monitorPointDataEntity.setMno4Out(countWqParamTypeOut(WqParamTypeConstant.TYPE_TEMPERATURE, monitorPointDataEntity.getMno4(), wqParamOutMap));
        monitorPointDataEntity.setMno4Out(countWqParamTypeOut(WqParamTypeConstant.TYPE_CONDUCTIVITY, monitorPointDataEntity.getMno4(), wqParamOutMap));
        monitorPointDataEntity.setMno4Out(countWqParamTypeOut(WqParamTypeConstant.TYPE_REDOXPOTENTIAL, monitorPointDataEntity.getMno4(), wqParamOutMap));


    }

    /**
     * 根据数值判断是否超标
     * @param key 水质参数名
     * @param value 数值
     * @param wqParamOutMap
     * @return 是否超标
     */
    private Boolean countWqParamTypeOut(String key, Double value, Map<String, Pair<Double, Double>> wqParamOutMap) {
        //Ⅳ+类水质污染
        //水质三类下界
//        Double minValue = wqParamMap.get(WqTypeConstant.THIRD).get(key).getKey();
//        return Double.compare(value, minValue) == 1;
        Pair<Double, Double> doubleDoublePair = wqParamOutMap.get(key);
        return value < doubleDoublePair.getKey() || value > doubleDoublePair.getValue();
    }

    /**
     * 计算水质类型
     * @return
     * @param monitorPointDataEntity 检测数据实体
     * @param wqParamMap
     */
    private void doCountWqType(MonitorPointDataEntity monitorPointDataEntity, Map<String, Map<String, Pair<Double, Double>>> wqParamMap) {
        Double ph = monitorPointDataEntity.getPh();
        Double dissolvedOxygen = monitorPointDataEntity.getDissolvedOxygen();
        Double nh3n = monitorPointDataEntity.getNh3n();
        Double mno4 = monitorPointDataEntity.getMno4();
        WqTypeEnum wqType = WqTypeEnum.FIRST;
        // 计算水质类型
        for (Map.Entry<String, Map<String, Pair<Double, Double>>> entry : wqParamMap.entrySet()) {
            String k = entry.getKey();
            Map<String, Pair<Double, Double>> v = entry.getValue();
            Boolean b1 = !countWqParamTypeOut(WqParamTypeConstant.TYPE_PH, ph, v);
            Boolean b2 = !countWqParamTypeOut(WqParamTypeConstant.TYPE_DISSOLVEDOXYGEN, dissolvedOxygen, v);
            Boolean b3 = !countWqParamTypeOut(WqParamTypeConstant.TYPE_NH3N, nh3n, v);
            Boolean b4 = !countWqParamTypeOut(WqParamTypeConstant.TYPE_MNO4, mno4, v);
            if (b1 || b2 || b3 || b4) {
                wqType = WqTypeEnum.getWqTypeByName(k);
            }
        }
        monitorPointDataEntity.setWqTypeName(wqType.getWqTypeName());
    }
}
