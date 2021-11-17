package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrConstructionOrder;

public interface VFemsCsrConstructionOrderMapper extends BaseMapper<VFemsCsrConstructionOrder> {
    int insert(VFemsCsrConstructionOrder record);

    int insertSelective(VFemsCsrConstructionOrder record);
}