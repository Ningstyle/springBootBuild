package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
public class VFemsCsrSystem extends Model<VFemsCsrSystem> {
    private Integer sysId;

    private String sysName;

    private String picture;

    private String icon;

    private String mainColor;

    private String sysUrl;

    private String sysPhoneUrl;

    private Date createTime;

    private String createUserId;

    private String createUserName;

    private Date updateTime;

    private String updateUserId;

    private String updateUserName;

    private String role;

}