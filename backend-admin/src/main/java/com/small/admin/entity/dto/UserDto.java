package com.small.admin.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.small.admin.common.constant.Regex;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.po.Role;
import com.small.admin.entity.po.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-09-25 10:11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class UserDto extends User {

    @ApiModelProperty(value = "用户拥有角色")
    private List<Role> roles;

    @ApiModelProperty(value = "用户拥有的权限")
    private List<Permission> permissions;

    @Override
    @JsonIgnore
    public @Pattern(regexp = Regex.PASSWORD, message = "密码格式错误") String getPassword() {
        return super.getPassword();
    }
}
