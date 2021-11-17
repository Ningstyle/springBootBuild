package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class VFemsCsrOverhaulOrder extends Model<VFemsCsrOverhaulOrder> {
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





}