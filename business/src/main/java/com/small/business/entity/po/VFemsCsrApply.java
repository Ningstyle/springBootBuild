package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
public class VFemsCsrApply extends Model<VFemsCsrApply> {
    private Long id;

    private String connId;

    private String upId;

    private String applyUserId;

    private String applyUserName;

    private Date applyTime;

    private Integer status;

    private Integer type;


}