package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
public class VFemsCsrSend extends Model<VFemsCsrSend> {
    private Long id;

    private String userId;

    private String project;

    private String method;

    private String operateUserName;
    private String operateUserId;

    private String connid;

    private Date time;

    private String detail;

    private Integer status;

    private String remarks;

    private Date createTime;
    private Date updateTime;

    private Integer ifRead;

    private Integer type;



}