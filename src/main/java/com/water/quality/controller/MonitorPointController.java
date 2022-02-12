package com.water.quality.controller;


import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.MonitorPointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 监测点添加
     */
    @PostMapping("/add")
    public R save(@RequestBody MonitorPointEntity monitorPoint){
        //返回结果用 R 对象封装
        boolean save = monitorPointService.save(monitorPoint);
        //使用assert断言判断查询结果
        Assert.isTrue(save, ResponseEnum.UPDATE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 监测点更新
     */
    @PutMapping("/update")
    public Boolean update(@RequestBody MonitorPointEntity monitorPoint){
        //todo 对更新数据进行判断 判重、判改变数据的影响、判新数据时候符合要求
        return monitorPointService.updateById(monitorPoint);
    }

    /**
     * 监测点删除
     */
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Integer id){
        //todo 对删除数据进行判断 是否影响原有数据
        return monitorPointService.removeById(id);
    }

    /**
     * 监测点查询
     */
    @GetMapping("/get/{id}")
    public MonitorPointEntity getById(@PathVariable Integer id){
        return monitorPointService.getById(id);
    }

    /**
     * 监测点名查询 等等
     */
}

