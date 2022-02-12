package com.water.quality.controller;


import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.MonitorPointService;
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
 * 监测点表 前端控制器
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Api(tags = "监测点管理")
@CrossOrigin
@RestController
@RequestMapping("/api/monitorPoint")
public class MonitorPointController {

    @Autowired
    private MonitorPointService monitorPointService;

    /**
     * 获取所有监测点 todo: 加上分页
     * @return
     */
    @ApiOperation("查询监测点列表")
    @GetMapping("/list")
    public R list() {
        List<MonitorPointEntity> list = monitorPointService.list();
        Assert.isTrue(list.size() > 0, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("list", list);
    }

    /**
     * 监测点查询
     */
}

