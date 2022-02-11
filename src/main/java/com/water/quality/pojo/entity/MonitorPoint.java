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
 * 监测点表
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MonitorPoint对象", description="监测点表")
public class MonitorPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "纬度")
    private Double lat;

    @ApiModelProperty(value = "经度")
    private Double lng;

    @ApiModelProperty(value = "监测点当前的水质类别名称")
    private String wqTypeName;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "地点")
    private String site;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "唯一节点序号")
    private Long nodeId;

    @ApiModelProperty(value = "最新的深度")
    private Double latestDepth;

    @ApiModelProperty(value = "0正常 1污染")
    private Integer pollutionStatus;

    @ApiModelProperty(value = "周围环境状况")
    private String conditionInfo;

    @ApiModelProperty(value = "水域信息")
    private String waterInfo;

    @ApiModelProperty(value = "深度列表")
    private String depthList;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人id")
    private Long creatorId;

    @ApiModelProperty(value = "更新人id")
    private Long editorId;


}
