package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
public class VFemsCsrExamine  extends Model<VFemsCsrExamine> {
    private Long id;

    private String connId;

    private String upId;

    private String examineUserId;

    private String examineUserName;

    private Date examineTime;

    private Integer status;

    private String remarks;

    private Integer type;

}