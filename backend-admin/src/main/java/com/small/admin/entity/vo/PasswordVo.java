package com.small.admin.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PasswordVo {

    @ApiModelProperty(value = "旧密码")
    private String oldpassword;

    @ApiModelProperty(value = "新密码")
    private String newpassword;

}
