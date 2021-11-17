package com.small.admin.entity.vo;


import com.small.admin.common.constant.CommonConstant;
import com.small.admin.enums.HttpMethodEnum;
import com.small.admin.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MenuVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限描述")
    private String description;

    @ApiModelProperty(value = "类型 0页面 1具体操作")
    private Integer type;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "按钮，供前端使用")
    private String icon;

    @ApiModelProperty(value = "页面路径/资源url")
    private String path;

    @ApiModelProperty(value = "request 请求方式：")
    private HttpMethodEnum actionMethod = HttpMethodEnum.GET;

    @ApiModelProperty(value = "父资源id")
    private String parentId;

    @ApiModelProperty(value = "排序")
    private Integer sortOrder;

    @ApiModelProperty(value = "状态")
    private StatusEnum status = StatusEnum.NORMAL;

    @ApiModelProperty(value = "始终显示 默认是")
    private Boolean showAlways = CommonConstant.SHOW_ALWAYS_TRUE;

    @ApiModelProperty(value = "子菜单/权限")
    private List<MenuVo> children;
}