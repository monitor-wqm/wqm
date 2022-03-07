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
        Assert.notNull(monitorPointDataEntity.getMonitorPointId(), ResponseEnum.MONITORPOINT_ID_NULL);
        Assert.notNull(monitorPointDataEntity.getId(), ResponseEnum.MONITORPOINT_NODE_NULL);
        //经纬度待定 和地点选填必填或生成待确定
        //暂定 ph 溶氧量 氨氮 高猛酸盐必填
        Assert.notNull(monitorPointDataEntity.getPh(), ResponseEnum.PH_IS_NULL);
        Assert.notNull(monitorPointDataEntity.getDissolvedOxygen(), ResponseEnum.DISSOLVEDOXYGEN_IS_NULL);
        Assert.notNull(monitorPointDataEntity.getNh3n(), ResponseEnum.NH3N_IS_NULL);
        Assert.notNull(monitorPointDataEntity.getMno4(), ResponseEnum.MNO4_IS_NULL);
        Assert.notNull(monitorPointDataEntity.getCity(), ResponseEnum.CITY_DISTRICT_NULL);
        Assert.notNull(monitorPointDataEntity.getDistrict(), ResponseEnum.CITY_DISTRICT_NULL);

        //超标有范围 Ⅳ+类水质污染

        //水质类别名称 和 单参数是否污染
        doCountWqType(monitorPointDataEntity);

        //计算水质参数超标信息
        doCountWqParamTypeOut(monitorPointDataEntity);

        //补充额外信息 创建时间等
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
        monitorPointDataEntity.setHandled(false);
        monitorPointDataEntity.setChecked(false);
    }

    /**
     * 计算水质参数超标信息
     * @param monitorPointDataEntity 检测数据实体
     */
    private void doCountWqParamTypeOut(MonitorPointDataEntity monitorPointDataEntity) {
        Map<String, Pair<Double, Double>> wqParamOutMap = wqParamService.getWqParamOutMap();
        monitorPointDataEntity.setPhOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_PH, monitorPointDataEntity.getPh(), wqParamOutMap));
        monitorPointDataEntity.setDissolvedOxygenOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_DISSOLVEDOXYGEN, monitorPointDataEntity.getDissolvedOxygen(), wqParamOutMap));
        monitorPointDataEntity.setNh3nOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_NH3N, monitorPointDataEntity.getNh3n(), wqParamOutMap));
        monitorPointDataEntity.setMno4Out(countWqParamTypeOut(WqParamTypeConstant.TYPE_MNO4, monitorPointDataEntity.getMno4(), wqParamOutMap));
        monitorPointDataEntity.setTurbidityOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_TURBIDITY, monitorPointDataEntity.getTurbidity(), wqParamOutMap));
        monitorPointDataEntity.setTemperatureOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_TEMPERATURE, monitorPointDataEntity.getTemperature(), wqParamOutMap));
        monitorPointDataEntity.setConductivityOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_CONDUCTIVITY, monitorPointDataEntity.getConductivity(), wqParamOutMap));
        monitorPointDataEntity.setRedoxPotentialOut(countWqParamTypeOut(WqParamTypeConstant.TYPE_REDOXPOTENTIAL, monitorPointDataEntity.getRedoxPotential(), wqParamOutMap));
    }

    /**
     * 根据数值判断是否超标
     * @param key 水质参数名
     * @param value 数值
     * @param wqParamOutMap
     * @return 是否超标
     */
    private Boolean countWqParamTypeOut(String key, Double value, Map<String, Pair<Double, Double>> wqParamOutMap) {
        if (value == null) {
            return null;
        }
        Pair<Double, Double> doubleDoublePair = wqParamOutMap.get(key);
        return value < doubleDoublePair.getKey() || value > doubleDoublePair.getValue();
    }

    /**
     * 计算水质类型 单参数判定水质
     * @return
     * @param monitorPointDataEntity 检测数据实体
     */
    private void doCountWqType(MonitorPointDataEntity monitorPointDataEntity) {
        //水质类别和对应参数阈值map
        Map<String, Map<String, Pair<Double, Double>>> wqParamMap = wqParamService.getWqParamMap();
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

        if (wqType.equals(WqTypeEnum.FIRST) || wqType.equals(WqTypeEnum.SECOND) || wqType.equals(WqTypeEnum.THIRD) ) {
            monitorPointDataEntity.setSingleIsPollution(false);
        } else {
            monitorPointDataEntity.setSingleIsPollution(true);
        }
    }
}
