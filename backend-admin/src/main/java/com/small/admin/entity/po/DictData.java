package com.small.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.small.admin.base.BaseEntity;
import com.small.admin.enums.DelFlagEnum;
import com.small.admin.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("small_dict_data")
@ApiModel(value = "字典数据表")
public class DictData extends BaseEntity {

    @ApiModelProperty(value = "字典ID")
    private String dictId;

    @ApiModelProperty(value = "字典数据名")
    private String name;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "字典状态")
    private StatusEnum status = StatusEnum.NORMAL;

    @ApiModelProperty(value = "字典排序值")
    private Integer sortOrder;

    @ApiModelProperty(value = "删除标志 默认0")
    private DelFlagEnum delFlag = DelFlagEnum.NORMAL;
}