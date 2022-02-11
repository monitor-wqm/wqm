package com.water.quality.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.User;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询用户")
    @GetMapping("/search/user/{username}")
    public R searchUser(@PathVariable("username") @ApiParam(value = "用户名", required = true) String username) {
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username", username));
        Assert.notNull(user, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("user", user);
    }
}

