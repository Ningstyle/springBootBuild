package com.small.business.common.annotation;

import java.lang.annotation.*;

/**
 * 类详细描述：接口需要登录注解
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2020/2/18 4:46 PM
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {

}
