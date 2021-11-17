package com.small.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.small.admin.base.BaseEntity;
import com.small.admin.enums.DelFlagEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("small_dict")
@ApiModel(value = "字典表")
public class Dict extends BaseEntity {

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "字典描述")
    private String description;

    @ApiModelProperty(value = "字典排序值")
    private Integer sortOrder;

    @ApiModelProperty(value = "删除标志 默认0")
    private DelFlagEnum delFlag = DelFlagEnum.NORMAL;
}