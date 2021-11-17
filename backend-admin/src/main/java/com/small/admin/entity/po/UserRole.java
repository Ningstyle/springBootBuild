package com.small.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.small.admin.base.BaseEntity;
import com.small.admin.enums.DelFlagEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("small_user_role")
@ApiModel(value = "用户角色关系表")
public class UserRole extends BaseEntity {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "删除标志 默认0")
    private DelFlagEnum delFlag = DelFlagEnum.NORMAL;
}