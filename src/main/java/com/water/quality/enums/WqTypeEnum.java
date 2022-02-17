package com.water.quality.enums;

import com.water.quality.r.exception.RRException;

/**
 * 水质类别常量
 * @author zby
 * @date 2022-02-12 21:51
 */
public enum  WqTypeEnum {
    FIRST("Ⅰ类"),
    SECOND("Ⅱ类"),
    THIRD("Ⅲ类"),
    FOURTH("Ⅳ类"),
    FIVE("Ⅴ类"),
    DE_FIVE("劣Ⅴ类");

    private String wqTypeName;

    WqTypeEnum(String wqTypeName) {
        this.wqTypeName = wqTypeName;
    }

    public static WqTypeEnum getWqTypeByName(String wqTypeName){
        for(WqTypeEnum configTypeEnum : WqTypeEnum.values()){
            if(configTypeEnum.wqTypeName.equals(wqTypeName)){
                return configTypeEnum;
            }
        }
        throw new RRException("解析的枚举值错误: " + wqTypeName);
    }

    public String getWqTypeName() {
        return wqTypeName;
    }

}
