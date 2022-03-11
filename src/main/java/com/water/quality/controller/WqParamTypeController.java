package com.water.quality.controller;


import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.MonitorPointEntity;
import com.water.quality.pojo.entity.WqParamTypeEntity;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.WqParamTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
        List<WqParamTypeEntity> list = wqParamTypeService.list();
        Assert.isTrue(list.size() > 0, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("list", list);
    }

    /**
     * 水质参数类别id查询
     */
    @GetMapping("/search/{id}")
    public R getById(@PathVariable Integer id){
        WqParamTypeEntity getById = wqParamTypeService.getById(id);
        Assert.notNull(getById, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("wqParamTypeId", getById);
    }

    /**
     * 水质参数类别添加
     */
    @ApiOperation("添加水质参数类别")
    @PostMapping("/add")
    public R save(@RequestBody WqParamTypeEntity wqParamType){
        //返回结果用 R 对象封装
        boolean save = wqParamTypeService.save(wqParamType);
        //使用assert断言判断查询结果
        Assert.isTrue(save, ResponseEnum.UPDATE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 水质参数类别更新
     */
    @PutMapping("/update")
    public R update(@RequestBody WqParamTypeEntity wqParamType){
        int update = wqParamTypeService.updateWqParamTypeById(wqParamType);
        Assert.isTrue(update > 0, ResponseEnum.UPDATE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 水质参数类别删除
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        //todo 对删除数据进行判断 是否影响原有数据
        boolean delete = wqParamTypeService.removeById(id);
        Assert.isTrue(delete , ResponseEnum.DELETE_DATA_ERROR);
        return R.ok();
    }
}

