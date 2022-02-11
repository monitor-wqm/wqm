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
 * 污染记录表
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PollutionRecord对象", description="污染记录表")
public class PollutionRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题 保存超标参数")
    private String title;

    @ApiModelProperty(value = "污染开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "污染结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "污染等级")
    private Integer level;

    @ApiModelProperty(value = "污染源分析")
    private String pollutionAnalyse;

    @ApiModelProperty(value = "治理措施")
    private String measure;

    @ApiModelProperty(value = "治理效果")
    private String measureEffect;

    @ApiModelProperty(value = "监测点节点id")
    private Long monitorPointNodeId;

    @ApiModelProperty(value = "监测点id")
    private Long monitorPointId;

    @ApiModelProperty(value = "深度值")
    private Double depth;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long creatorId;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人id")
    private Long editorId;


}
