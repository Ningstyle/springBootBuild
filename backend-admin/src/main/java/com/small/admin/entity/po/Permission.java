package com.small.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.small.admin.base.BaseEntity;
import com.small.admin.common.constant.CommonConstant;
import com.small.admin.enums.DelFlagEnum;
import com.small.admin.enums.HttpMethodEnum;
import com.small.admin.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
@TableName("small_permission")
@ApiModel(value = "权限表")
public class Permission extends BaseEntity {

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

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "子菜单/权限")
    private List<Permission> children;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "节点展开 前端所需")
    private Boolean expand = true;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "是否勾选 前端所需")
    private Boolean checked = false;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "是否选中 前端所需")
    private Boolean selected = false;

    @ApiModelProperty(value = "删除标志 默认0")
    private DelFlagEnum delFlag = DelFlagEnum.NORMAL;
}