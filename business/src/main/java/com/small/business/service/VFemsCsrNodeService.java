package com.small.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.small.business.entity.po.VFemsCsrDetailList;
import com.small.business.entity.po.VFemsCsrNode;
import com.small.business.entity.po.VFemsCsrProjectext;

import java.util.List;
import java.util.Map;

public interface VFemsCsrNodeService extends IService<VFemsCsrNode> {

    IPage<Map<String, Object>> getProjectTestByUser(IPage<Map<String, Object>> page, QueryWrapper<VFemsCsrProjectext> queryWrapper);

    IPage<VFemsCsrProjectext> getProjectTest(VFemsCsrProjectext vFemsCsrProjectext, IPage<VFemsCsrProjectext> page, List<String> pjIds);

}
