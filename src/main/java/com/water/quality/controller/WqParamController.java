package com.water.quality.controller;


import com.water.quality.asserts.Assert;
import com.water.quality.pojo.vo.WqParamVo;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.WqParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 水质参数表 前端控制器
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Api(tags = "水质参数管理")
@CrossOrigin
@RestController
@RequestMapping("/api/wqParam")
public class WqParamController {

    @Autowired
    private WqParamService wqParamService;

    @ApiOperation("查询水质参数列表")
    @GetMapping("/list")
    public R list() {
        List<WqParamVo> list = wqParamService.listWithName();
        Assert.isTrue(list.size() > 0, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("list", list);
    }
}

