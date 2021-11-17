package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class VFemsCsrPjmember extends Model<VFemsCsrPjmember> {
    private String userId;

    private String userName;

    private String pjmemPjId;

    private String pjNo;

    private String pjName;

}