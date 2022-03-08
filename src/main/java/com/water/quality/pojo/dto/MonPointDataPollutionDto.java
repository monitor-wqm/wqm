package com.water.quality.pojo.dto;

import lombok.Data;

@Data
public class MonPointDataPollutionDto {

    /**
     * 监测点数据id
     */
    private Long id;

    /**
     * 是否单参数污染
     */
    private Boolean pollutioned;
}
