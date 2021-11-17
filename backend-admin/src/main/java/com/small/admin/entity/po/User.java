package com.small.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.small.admin.base.BaseEntity;
import com.small.admin.common.constant.CommonConstant;
import com.small.admin.common.constant.Regex;
import com.small.admin.enums.DelFlagEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-08-28 08:48]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@TableName("small_user")
@ApiModel(value = "用户")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Pattern(regexp = Regex.USERNAME,message = "用户名格式错误")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @Pattern(regexp = Regex.PASSWORD,message = "密码格式错误")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别: 男 or 女")
    private String sex;

    @ApiModelProperty(value = "头像地址")
    private String avatar = CommonConstant.USER_DEFAULT_AVATAR;

    @ApiModelProperty(value = "描述/详情/备注")
    private String description;

    @ApiModelProperty(value = "用户类型 0普通用户 1管理员")
//    private UserTypeEnum userType = UserTypeEnum.GENERAL;
    private Integer userType = 0;

    @ApiModelProperty(value = "状态 默认0正常 -1拉黑")
//    private StatusEnum userStatus = StatusEnum.NORMAL;
    private Integer userStatus = 0;

    @ApiModelProperty(value = "最后登录时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    @ApiModelProperty(value = "删除标志 默认0")
    private DelFlagEnum delFlag = DelFlagEnum.NORMAL;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = null!=userType?userType:0;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = null!=userStatus?userStatus:0;
    }
}
