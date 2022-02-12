package com.water.quality.mapper;

import com.water.quality.pojo.entity.MonitorPointEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 监测点表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Mapper
public interface MonitorPointMapper extends BaseMapper<MonitorPointEntity> {

}
