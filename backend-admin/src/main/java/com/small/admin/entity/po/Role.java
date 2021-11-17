package com.small.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.small.admin.base.BaseEntity;
import com.small.admin.common.constant.CommonConstant;
import com.small.admin.enums.DelFlagEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("small_role")
@ApiModel(value = "角色表")
public class Role extends BaseEntity {

    @ApiModelProperty(value = "角色名称,建议以 ROLE_ 开头，配合security")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String roleDescription;

    @ApiModelProperty(value = "是否默认角色")
    private Boolean defaultRole = CommonConstant.ROLE_DEFAULT_TRUE;

    @ApiModelProperty(value = "删除标志 默认0")
    private DelFlagEnum delFlag = DelFlagEnum.NORMAL;
}