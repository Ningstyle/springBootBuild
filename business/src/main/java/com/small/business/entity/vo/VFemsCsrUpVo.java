package com.small.business.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.small.business.entity.po.VFemsCsrBoltConfiguration;
import com.small.business.entity.po.VFemsCsrBoltConfigurationLog;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class VFemsCsrUpVo extends Model<VFemsCsrUpVo> {
    private Long id;
    private String upId;
    private String upSeatno;
    private String upRunningPositionNo;

    private List<VFemsCsrBoltConfiguration> VFemsCsrBoltConfiguration;
    private List<VFemsCsrBoltConfigurationLog> oldVFemsCsrBoltConfiguration;

}