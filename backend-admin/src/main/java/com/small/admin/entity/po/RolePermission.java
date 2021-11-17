package com.small.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.small.admin.base.BaseEntity;
import com.small.admin.enums.DelFlagEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("small_role_permission")
@ApiModel(value = "角色权限关系表")
public class RolePermission extends BaseEntity {

    public RolePermission(String roleId, String permissionId){
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "权限ID")
    private String permissionId;

    @ApiModelProperty(value = "删除标志 默认0")
    private DelFlagEnum delFlag = DelFlagEnum.NORMAL;
}