package com.water.quality.service;

import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.pojo.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.water.quality.pojo.vo.UserVo;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @Return 用户对象
     */
    UserVo login(String username, String password);

    /**
     * 更新监测点信息
     * @param user
     * @return 受影响行数
     */
    int updateUserById(UserEntity user);


}
