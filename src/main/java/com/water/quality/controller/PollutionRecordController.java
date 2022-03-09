package com.water.quality.controller;


import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.pojo.entity.PollutionRecordEntity;
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
        List<PollutionRecordEntity> list = pollutionRecordService.selectPollutionRecordByMonitorPointId(id);
        Assert.isTrue(list.size() > 0, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("list", list);
    }

    @ApiOperation("查询所有监测点的污染记录")
    @GetMapping("/list")
    public R list() {
        List<PollutionRecordEntity> list = pollutionRecordService.list();
        Assert.isTrue(list.size() > 0, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("list", list);
    }

    /**
     * 监测点污染记录添加
     */
    @PostMapping("/add")
    public R save(@RequestBody PollutionRecordEntity PollutionRecord){
        //返回结果用 R 对象封装
        boolean save = pollutionRecordService.save(PollutionRecord);
        //使用assert断言判断查询结果
        Assert.isTrue(save, ResponseEnum.UPDATE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 监测点污染记录删除
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        boolean delete = pollutionRecordService.removeById(id);
        Assert.isTrue(delete , ResponseEnum.DELETE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 监测点污染记录id查询
     */
    @GetMapping("/search/{id}")
    public R getById(@PathVariable Integer id){
        PollutionRecordEntity getById = pollutionRecordService.getById(id);
        Assert.notNull(getById, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("pollutionRecordId", getById);
    }

    /**
     * 污染记录逻辑 todo
     * 1:系统分析: 定时任务一次分析所有？或者点击系统分析再根据污染id找出未分析的污染记录 分析出污染title 起始时间 污染等级
     * 2:人工处理: 列出污染记录 选择一条进行人工处理 填上采取措施和措施效果、污染分析字段  完成后 把监测点污染状态置0 污染记录监测点数据标记已处理
     */

}
