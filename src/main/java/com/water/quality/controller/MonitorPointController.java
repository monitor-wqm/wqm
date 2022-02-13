package com.water.quality.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.MonitorPointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public R update(@RequestBody MonitorPointEntity monitorPoint){
        //todo 对更新数据进行判断 判重、判改变数据的影响、判新数据时候符合要求
        boolean update = monitorPointService.updateById(monitorPoint);
        Assert.isTrue(update, ResponseEnum.UPDATE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 监测点删除
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        //todo 对删除数据进行判断 是否影响原有数据
        boolean delete = monitorPointService.removeById(id);
        Assert.isTrue(delete , ResponseEnum.DELETE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 监测点id查询
     */
    @GetMapping("/search/monitorPoint/{id}")
    public R getById(@PathVariable Integer id){
        MonitorPointEntity getById = monitorPointService.getById(id);
        Assert.notNull(getById, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("monitorPointId", getById);
    }

    /**
     * 监测点名查询
     */
    @GetMapping("/search/monitorPoint/{name}")
    public R searchMonitorPoint(@PathVariable("name") @ApiParam(value = "监测点名", required = true) String name) {
        MonitorPointEntity monitorPointEntity = monitorPointService.getOne(new QueryWrapper<MonitorPointEntity>()
                .eq("name",name));
        Assert.notNull(monitorPointEntity, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("monitorPointName",monitorPointEntity );
    }
}

