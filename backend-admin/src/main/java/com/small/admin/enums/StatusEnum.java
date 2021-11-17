package com.small.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * luhanlin 2019-09-12
 */
public enum StatusEnum {

    NORMAL(0, "启用"), DISABLE(-1, "禁用");

    @EnumValue
    private final Integer value;
    private final String desc;

    StatusEnum(final Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonValue
    public Integer getValue() {
        return this.value;
    }

//    @JsonValue
    public String getDesc() {
        return desc;
    }
}