package com.small.common.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ExcelProperty
 * @Description: Excel 自定义注解到实体类
 * @Author xaq
 * @Date 2019/10/14
 * @Version V1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public  @interface ExcelProperty {
    public int index(); // 指定 JavaBean 的属性对应 excel 的第几列
    public String format() default ""; // 指定 Date 的格式化模式
}
