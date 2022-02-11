package com.water.quality.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.water.quality.mapper.WqParamMapper;
import com.water.quality.pojo.entity.WqParam;
import com.water.quality.pojo.vo.WqParamVo;
import com.water.quality.service.WqParamService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 水质参数表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Service
public class WqParamServiceImpl extends ServiceImpl<WqParamMapper, WqParam> implements WqParamService {

    @Override
    public List<WqParamVo> listWithName() {
        return baseMapper.listWithName();
    }
}
