package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrSend;

public interface VFemsCsrSendMapper extends BaseMapper<VFemsCsrSend> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrSend record);

    int insertSelective(VFemsCsrSend record);

    VFemsCsrSend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrSend record);

    int updateByPrimaryKey(VFemsCsrSend record);
}