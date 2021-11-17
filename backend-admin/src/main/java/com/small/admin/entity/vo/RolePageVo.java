package com.small.admin.entity.vo;

import com.small.common.vo.PageVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuwei
 * @date 2019/10/12 8:28
 */

@Data
public class RolePageVo extends PageVo {

    @ApiModelProperty(value = "角色编号")
    private String roleNo;

    @ApiModelProperty(value = "角色名称,建议以 ROLE_ 开头，配合security")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String roleDescription;

    @ApiModelProperty(value = "是否分页 true 分页 false 不分页获取所有数据")
    private boolean page = false;

}
