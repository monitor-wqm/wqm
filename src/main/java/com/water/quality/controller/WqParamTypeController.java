package com.water.quality.controller;


import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.WqParamType;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.WqParamTypeService;
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
 * 水质参数种类 前端控制器
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Api(tags = "水质参数类别管理")
@CrossOrigin
@RestController
@RequestMapping("/api/wqParamType")
public class WqParamTypeController {

    @Autowired
    private WqParamTypeService wqParamTypeService;

    /**
     * 获取所有水质参数类别 todo:分页
     * @return
     */
    @ApiOperation("查询水质参数类别列表")
    @GetMapping("/list")
    public R list() {
        List<WqParamType> list = wqParamTypeService.list();
        Assert.isTrue(list.size() > 0, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("list", list);
    }

}

