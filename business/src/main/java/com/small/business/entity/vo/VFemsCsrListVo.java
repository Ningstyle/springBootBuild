package com.small.business.entity.vo;

import com.small.business.entity.po.VFemsCsrBoltConfiguration;
import com.small.business.entity.po.VFemsCsrDetailList;
import com.small.business.entity.po.VFemsCsrType;
import lombok.Data;

import java.util.List;

@Data
public class VFemsCsrListVo {
    private String id;
    private List<VFemsCsrType> vFemsCsrTypeList;
    private List<VFemsCsrDetailList> vFemsCsrDetailList;

    private List<VFemsCsrBoltConfiguration> vFemsCsrBoltConfigurationList;
}
