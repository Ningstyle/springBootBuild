package com.small.business.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class VFemsCsrOverhaulOrderVo extends Model<VFemsCsrOverhaulOrderVo> {
    private String pjId="";

    private String pjNo="";

    private String pjName="";

    private String bgName="";

    private String taskId="";

    private String taskNo="";

    private String taskType="";

    private String taskTypeName="";

    private String taskUpId="";

    private String upRunningPositionNo="";

    private String upSeatno="";

    private String taskOverhaulBatch="";

    private String taskOverhaulType="";

    private String overhaulTypeName="";

    private String soManageUserId="";

    private String soManageUserName="";

    private String taskStatusFlg="";

    private String statusName="";

    private String taskStartTime="";

    private String taskEndTime="";




    private String bgId="";
    private int type=0;//默认是检修工单 1在建工单
}