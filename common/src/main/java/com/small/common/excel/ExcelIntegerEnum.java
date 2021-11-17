package com.small.common.excel;

/**
 * Excel 整型数据枚举转换
 */
public enum ExcelIntegerEnum {

    NORMAL("启用",0),
    DISABLE("禁用",-1);


    private String name;
    private Integer value;


    ExcelIntegerEnum(String name, Integer value) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return name;
    }
}