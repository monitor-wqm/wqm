package com.water.quality.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 监测点数据表
 * </p>
 *
 * @author
 * @since 2022-02-10
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MonitorPointData对象", description="监测点数据表")
@TableName("monitor_point_data")
public class MonitorPointDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "监测点当前的水质类别名称")
    private String wqTypeName;

    @NotNull
    @ApiModelProperty(value = "监测点id")
    private Long monitorPointId;

    @NotNull
    @ApiModelProperty(value = "监测点节点序号")
    private Long montiorPointNodeId;

    @ApiModelProperty(value = "污染记录id")
    private Long pollutionRecordId;

    @ApiModelProperty(value = "纬度")
    private Double lat;

    @ApiModelProperty(value = "经度")
    private Double lng;

    @NotNull
    @ApiModelProperty(value = "市")
    private String city;

    @NotNull
    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "深度值")
    private Double depth;

    @ApiModelProperty(value = "浑浊度")
    private Double turbidity;

    @ApiModelProperty(value = "温度")
    private Double temperature;

    @ApiModelProperty(value = "PH值")
    private Double ph;

    @ApiModelProperty(value = "溶氧量")
    private Double dissolvedOxygen;

    @ApiModelProperty(value = "导电率")
    private Double conductivity;

    @ApiModelProperty(value = "氧化还原电位")
    private Double redoxPotential;

    @ApiModelProperty(value = "氨氮")
    private Double nh3n;

    @ApiModelProperty(value = "高猛酸盐植数")
    private Double mno4;

    @ApiModelProperty(value = "单参数判定方式是否污染")
    private Boolean singleIsPollution;

    @ApiModelProperty(value = "综合判定是否污染")
    @TableField("is_pollution")
    private Boolean pollution;

    @NotNull
    @ApiModelProperty(value = "地点")
    private String site;

    @ApiModelProperty(value = "是否已经处理")
    @TableField("is_handled")
    private Boolean handled;

    @ApiModelProperty(value = "PH值是否超标")
    @TableField("is_ph_out")
    private Boolean phOut;

    @ApiModelProperty(value = "溶氧量是否超标")
    @TableField("is_dissolved_oxygen_out")
    private Boolean dissolvedOxygenOut;

    @ApiModelProperty(value = "导电率是否超标")
    @TableField("is_conductivity_out")
    private Boolean conductivityOut;

    @ApiModelProperty(value = "温度是否超标")
    @TableField("is_temperature_out")
    private Boolean temperatureOut;

    @ApiModelProperty(value = "氧化还原电位是否超标")
    @TableField("is_redox_potential_out")
    private Boolean redoxPotentialOut;

    @ApiModelProperty(value = "浑浊度是否超标")
    @TableField("is_turbidity_out")
    private Boolean turbidityOut;

    @ApiModelProperty(value = "氨氮是否超标")
    @TableField("is_nh3n_out")
    private Boolean nh3nOut;

    @ApiModelProperty(value = "高猛酸盐是否超标")
    @TableField("is_mno4_out")
    private Boolean mno4Out;

    @ApiModelProperty(value = "数据是否被查看")
    @TableField("is_checked")
    private Boolean checked;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人id")
    private Long creatorId;

    @ApiModelProperty(value = "更新人id")
    private Long editorId;


}
