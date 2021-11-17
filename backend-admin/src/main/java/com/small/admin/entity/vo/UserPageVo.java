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
public class UserPageVo extends PageVo {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别: 男 or 女")
    private String sex;

    @ApiModelProperty(value = "描述/详情/备注")
    private String description;

    @ApiModelProperty(value = "用户类型 0普通用户 1管理员")
//    private UserTypeEnum userType = UserTypeEnum.GENERAL;
    private Integer userType = 0;

    @ApiModelProperty(value = "状态 默认0正常 -1拉黑")
//    private StatusEnum userStatus = StatusEnum.NORMAL;
    private Integer userStatus = 0;

    @ApiModelProperty(value = "是否分页 true 分页 false 不分页获取所有数据")
    private boolean page = true;

}
