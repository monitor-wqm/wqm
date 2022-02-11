package com.water.quality.service.impl;

import com.water.quality.pojo.entity.User;
import com.water.quality.mapper.UserMapper;
import com.water.quality.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
