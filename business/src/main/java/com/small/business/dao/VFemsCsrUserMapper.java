package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrUser;

public interface VFemsCsrUserMapper extends BaseMapper<VFemsCsrUser> {
    int insert(VFemsCsrUser record);

    int insertSelective(VFemsCsrUser record);
}