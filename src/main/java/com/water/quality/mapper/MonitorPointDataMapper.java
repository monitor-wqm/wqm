package com.water.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.water.quality.pojo.dto.MonPointDataPollutionDto;
import com.water.quality.pojo.entity.MonitorPointDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 监测点数据表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Mapper
public interface MonitorPointDataMapper extends BaseMapper<MonitorPointDataEntity> {

    /**
     * 查出监测点id 对应的times条记录的污染状态
     * @param monPointId 监测点id
     * @param count 多少条记录
     * @return k:id, v:pollution
     */
    List<MonPointDataPollutionDto> selectPollutionWithTimesDesc(@Param("monPointId") Long monPointId ,
                                                                @Param("count") int count);
}
