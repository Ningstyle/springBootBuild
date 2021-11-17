package com.small.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-09-12 16:11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum  UserTypeEnum {

    GENERAL(0, "普通用户"), ADMIN(1, "管理员");

    @EnumValue
    private final Integer value;
    private final String desc;

    UserTypeEnum(final Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonValue
    public Integer getValue() {
        if(null==value) return 0;
        return this.value;
    }

//    @JsonValue
    public String getDesc() {
        return desc;
    }
}

