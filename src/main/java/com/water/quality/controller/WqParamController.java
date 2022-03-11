package com.water.quality.controller;


import com.water.quality.asserts.Assert;
import com.water.quality.pojo.entity.WqParamEntity;
import com.water.quality.pojo.entity.WqParamTypeEntity;
import com.water.quality.pojo.vo.WqParamVo;
import com.water.quality.r.R;
import com.water.quality.r.enums.ResponseEnum;
import com.water.quality.service.WqParamService;
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
    /**
     * 水质参数id查询
     */
    @GetMapping("/search/{id}")
    public R getById(@PathVariable Integer id){
        WqParamEntity getById = wqParamService.getById(id);
        Assert.notNull(getById, ResponseEnum.DATABASE_NULL_ERROR);
        return R.ok().data("wqParamId", getById);
    }

    /**
     * 水质参数添加
     */
    @ApiOperation("添加水质参数")
    @PostMapping("/add")
    public R save(@RequestBody WqParamEntity wqParam){
        //返回结果用 R 对象封装
        boolean save = wqParamService.save(wqParam);
        //使用assert断言判断查询结果
        Assert.isTrue(save, ResponseEnum.UPDATE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 水质参数更新
     */
    @PutMapping("/update")
    public R update(@RequestBody WqParamEntity wqParam){
        int update = wqParamService.updateWqParamById(wqParam);
        Assert.isTrue(update > 0, ResponseEnum.UPDATE_DATA_ERROR);
        return R.ok();
    }

    /**
     * 水质参数删除
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        //todo 对删除数据进行判断 是否影响原有数据
        boolean delete = wqParamService.removeById(id);
        Assert.isTrue(delete , ResponseEnum.DELETE_DATA_ERROR);
        return R.ok();
    }
}

