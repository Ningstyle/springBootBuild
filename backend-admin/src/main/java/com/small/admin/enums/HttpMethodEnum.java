package com.small.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-09-29 14:42]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum  HttpMethodEnum {

    GET("GET"), POST("POST"), DELETE("DELETE"), PUT("PUT"), HEAD("HEAD");

    @EnumValue
    private final String value;

    HttpMethodEnum(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
