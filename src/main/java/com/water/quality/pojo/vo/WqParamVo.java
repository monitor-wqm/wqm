package com.water.quality.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 水质参数表
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Data
@ApiModel(value="WqParamVo对象", description="水质参数Vo")
public class WqParamVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "水质参数主键id")
    private Long id;

    @ApiModelProperty(value = "水质类别id")
    private Long wqTypeId;

    @ApiModelProperty(value = "水质类别名")
    private String wqTypeName;

    @ApiModelProperty(value = "水质参数类别id")
    private Long wqParamTypeId;

    @ApiModelProperty(value = "水质参数类别名")
    private String wqParamTypeName;

    @ApiModelProperty(value = "最大浓度阈值")
    private Double maxConcentration;

    @ApiModelProperty(value = "最小浓度阈值")
    private Double minConcentration;

    @ApiModelProperty(value = "水质参数单位")
    private String unit;

}
