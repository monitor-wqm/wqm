package com.water.quality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.water.quality.asserts.Assert;
import com.water.quality.mapper.UserMapper;
import com.water.quality.pojo.entity.UserEntity;
import com.water.quality.pojo.vo.UserVo;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.UserService;
import com.water.quality.utils.JwtUtil;
import com.water.quality.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public UserVo login(String username, String password) {
        Assert.notEmpty(username, ResponseEnum.COMMIT_NULL_ERROR);
        Assert.notEmpty(password, ResponseEnum.COMMIT_NULL_ERROR);
        UserEntity user = baseMapper.selectOne(new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getUsername, username));
        Assert.notNull(user, ResponseEnum.LOGIN_USER_ERROR);
        if (user.getPassword().equals(MD5Util.encrypt(password))) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVo.setToken(JwtUtil.createToken(user.getId(), username));
            return userVo;
        } else {
            return null;
        }
    }
}
