package com.small.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-09-12 15:47]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum DelFlagEnum {

    NORMAL(0, "正常"), DELETE(-1, "删除");

    @EnumValue
    private final Integer value;

    private final String desc;

    DelFlagEnum(final Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonValue
    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return desc;
    }
}
