package com.water.quality.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.water.quality.pojo.entity.WqParamEntity;
import com.water.quality.pojo.vo.WqParamVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 水质参数表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Mapper
public interface WqParamMapper extends BaseMapper<WqParamEntity> {

    List<WqParamVo> listWithName();
}
