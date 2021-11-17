package com.small.admin.entity.vo;

import com.small.common.vo.PageVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-10-10 13:45]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class PermissionPageVo extends PageVo {

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限描述")
    private String description;

    @ApiModelProperty(value = "类型 0页面 1具体操作")
    private Integer type;

    @ApiModelProperty(value = "是否分页 true 分页 false 不分页获取所有数据")
    private boolean page = false;

}
