package com.small.business.entity.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.small.business.entity.po.VFemsCsrBoltConfiguration;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class VFemsCsrApplyVo extends Model<VFemsCsrApplyVo> {
    private Long id;

    private String connId;

    private String upId;

    private String applyUserId;

    private String applyUserName;

    private Date applyTime;

    private Integer status;


    private String upSeatno;
    private String upRunningPositionNo;
    private String upPjName;
    private String upPjId;

    private List<VFemsCsrBoltConfiguration> VFemsCsrBoltConfigurationList = new ArrayList<>();
    private Long total;
    private int size;
    private int current;


    private String examineUserId;
    private String examineUserName;
    private Date examineTime;
    private String remarks;

    private Integer type;
}