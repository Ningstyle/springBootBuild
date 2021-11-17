package com.small.admin.entity.dto;

import com.small.admin.entity.po.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-09-25 10:12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class RoleDto extends Role {

    @ApiModelProperty(value = "拥有权限")
    private List<String> permissions;

}
