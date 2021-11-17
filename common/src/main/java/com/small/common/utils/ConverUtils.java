package com.small.common.utils;

import cn.hutool.core.bean.BeanUtil;


/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-09-30 15:13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ConverUtils {

    /**
     * 将 obj 的属性copy 到 cls对象中
     * @param obj
     * @return
     */
    public static <T> T copyToCls(Object obj, Class<T> cls) {
        try {
            T instance = cls.newInstance();
            BeanUtil.copyProperties(obj, instance);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
