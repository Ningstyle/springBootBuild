package com.small.common.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-08-28 08:48]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 失败消息
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 结果对象
     */
    private T result;
}
