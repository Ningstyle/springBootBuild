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
public class PageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //@ApiModelProperty(value = "页号")
    private int pageNumber = 0;

   // @ApiModelProperty(value = "页面大小")
    private int pageSize = 20;

    //@ApiModelProperty(value = "排序字段")
    private String sort;

    //@ApiModelProperty(value = "排序方式 asc/desc")
    private String order;
}
