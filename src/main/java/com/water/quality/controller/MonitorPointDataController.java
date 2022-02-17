package com.water.quality.controller;


import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.MonitorPointDataEntity;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.MonitorPointDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 监测点数据表 前端控制器
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Api(tags = "监测点数据管理")
@CrossOrigin
@RestController
@RequestMapping("/api/monitorPointData")
public class MonitorPointDataController {

    @Autowired
    private MonitorPointDataService monitorPointDataService;

    /**
     * 这里为了简单实现先使用id查  todo: 后期所有查询封装query
     * @return
     */
    @ApiOperation("查询某个监测点的监测数据")
    @GetMapping("/select/{id}")
    public R select(@ApiParam(value = "监测点id", required = true) @PathVariable("id") Long id) {
        List<MonitorPointDataEntity> list = monitorPointDataService.selectMonitorPointDataByMonitorPointId(id);
        Assert.isTrue(list.size() > 0, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("list", list);
    }

    /**
     * 监测点最新数据查询
     */
    @ApiOperation("查询某个监测点的最新监测数据")
    @GetMapping("/select/latest/{id}")
    public R selectLatest(@ApiParam(value = "监测点id", required = true) @PathVariable("id") Long id) {
        MonitorPointDataEntity latestData = monitorPointDataService.selectLatestMonitorPointDataByMonitorPointId(id);
        Assert.notNull(latestData, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("latestData", latestData);
    }

    /**
     * 新增监测点数据
     */
    @ApiOperation("新增监测点数据")
    @PostMapping("/insert/{id}")
    public R insert(@ApiParam(value = "监测点id", required = true) @PathVariable("id") Long id,
                    @RequestBody MonitorPointDataEntity monitorPointDataEntity) {

        int status = monitorPointDataService.insertMonitorPointData(monitorPointDataEntity);
        Assert.isTrue(status > 0, ResponseEnum.INSERT_DATA_ERROR);
        return R.ok();
    }

}

