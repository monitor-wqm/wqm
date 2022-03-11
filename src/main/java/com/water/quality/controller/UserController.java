package com.water.quality.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.pojo.entity.UserEntity;
import com.water.quality.pojo.vo.UserVo;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Api(tags = "用户管理")
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class  UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询用户")
    @GetMapping("/search/user/{username}")
    public R searchUser(@PathVariable("username") @ApiParam(value = "用户名", required = true) String username) {
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>()
                .eq("username", username));
        Assert.notNull(userEntity, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("user", userEntity);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R searchUser(@RequestParam("username") @ApiParam(value = "用户名", required = true) String username,
                        @RequestParam("password") @ApiParam(value = "密码", required = true) String password) {
        UserVo user = userService.login(username, password);
        Assert.notNull(user, ResponseEnum.LOGINACCT_PASSWORD_EXCEPTION);
        //todo session设置(暂时不做登录权限等,先有个密码校验)
        return R.ok().data("user", user);
    }

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public R list() {
        List<UserEntity> list = userService.list();
        Assert.isTrue(list.size() > 0, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("list", list);
    }

    /**
     * 用户添加
     */
    @ApiOperation("添加用户")
    @PostMapping("/add")
    public R save(@RequestBody UserEntity user){
        //返回结果用 R 对象封装
        boolean save = userService.save(user);
        //使用assert断言判断查询结果
        Assert.isTrue(save, ResponseEnum.UPDATE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 用户更新
     */
    @ApiOperation("更新用户")
    @PutMapping("/update")
    public R update(@RequestBody UserEntity user){
        int update = userService.updateUserById(user);
        Assert.isTrue(update > 0, ResponseEnum.UPDATE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 用户删除
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        //todo 对删除数据进行判断 是否影响原有数据
        boolean delete = userService.removeById(id);
        Assert.isTrue(delete , ResponseEnum.DELETE_DATA_ERROR);
        return R.ok();
    }
}

