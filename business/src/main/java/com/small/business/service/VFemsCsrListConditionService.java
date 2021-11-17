package com.small.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.small.business.entity.po.VFemsCsrDetailList;
import com.small.business.entity.po.VFemsCsrListCondition;
import com.small.business.entity.po.VFemsCsrProjectext;
import com.small.business.entity.vo.VFemsCsrListVo;

import java.util.List;
import java.util.Map;

public interface VFemsCsrListConditionService extends IService<VFemsCsrListCondition> {

    IPage<VFemsCsrListCondition> getTorqueListCondition(IPage<VFemsCsrListCondition> page, QueryWrapper<VFemsCsrListCondition> queryWrapper);

    VFemsCsrListVo getAllByListCondition(IPage<VFemsCsrDetailList> page, VFemsCsrListCondition vFemsCsrListCondition);

}
