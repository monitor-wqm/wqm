package com.water.quality.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 水质参数种类
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="WqParamType对象", description="水质参数种类")
public class WqParamType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "水质参数名称 (浑浊度，ph，溶氧量，深度值，导电率，氧化还原电位等)")
    private String name;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "水质参数意义")
    private String sense;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人id")
    private Long editorId;


}
