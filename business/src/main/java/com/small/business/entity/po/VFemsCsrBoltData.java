package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class VFemsCsrBoltData extends Model<VFemsCsrBoltData> {
    private Long id;

    private Long boltId;

    private String time;

    private String num;

    private String nodeNo;

    private String boltNo;

    private String quantity;




}