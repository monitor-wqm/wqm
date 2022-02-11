package com.water.quality.controller;


import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.PollutionRecord;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.PollutionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 污染记录表 前端控制器
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Api(tags = "污染记录管理")
@CrossOrigin
@RestController
@RequestMapping("/api/pollutionRecord")
public class PollutionRecordController {


    @Autowired
    private PollutionRecordService pollutionRecordService;

    /**
     * 查询监测点的污染记录  todo: 后期所有查询封装query
     *
     * @return
     */
    @ApiOperation("查询某个监测点的污染记录")
    @GetMapping("/select/{id}")
    public R select(@ApiParam(value = "监测点id", required = true) @PathVariable("id") Long id) {
        List<PollutionRecord> list = pollutionRecordService.selectPollutionRecordByMonitorPointId(id);
        Assert.isTrue(list.size() > 0, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("list", list);
    }
}
