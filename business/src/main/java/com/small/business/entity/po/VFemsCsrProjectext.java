package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class VFemsCsrProjectext  extends Model<VFemsCsrProjectext> {
    private String pjId;

    private String pjContrId;

    private String pjPgId;

    private String pgName;

    private String pjBgId;

    private String bgName;

    private String pjNo;

    private String pjName;

    private String pjDeliveryAddr;

    private String pjManagerOano;

    private String pjManager;

    private String pjLngType;

    private String pjBoosterStatLng;

    private String pjLatType;

    private String pjBoosterStatLat;

    private String pjTerrainType;
    private String pjStage;
    private String pjStageDesc;



    private String upNum;//风机数量
    private String upSeatno;
    private String upRunningPositionNo;

}