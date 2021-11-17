package com.small.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.small.business.entity.po.VFemsCsrPjmember;
import com.small.business.entity.po.VFemsCsrUnitprofile2;

import java.util.List;
import java.util.Map;

public interface VFemsCsrUnitprofile2Service extends IService<VFemsCsrUnitprofile2> {

    IPage<Map<String, Object>> getUpByPjId(IPage<Map<String, Object>> page, QueryWrapper<VFemsCsrUnitprofile2> queryWrapper);
}
