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
 * 水质参数表
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="WqParam对象", description="水质参数表")
public class WqParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "水质类别id")
    private Long wqTypeId;

    @ApiModelProperty(value = "水质参数类别id")
    private Long wqParamTypeId;

    @ApiModelProperty(value = "最大浓度阈值")
    private Double maxConcentration;

    @ApiModelProperty(value = "最小浓度阈值")
    private Double minConcentration;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人id")
    private Long creatorId;

    @ApiModelProperty(value = "更新人id")
    private Long editorId;


}
