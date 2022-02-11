package com.water.quality.mapper;

import com.water.quality.pojo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
