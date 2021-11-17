package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrPjmember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VFemsCsrPjmemberMapper  extends BaseMapper<VFemsCsrPjmember> {

    int insert(VFemsCsrPjmember record);

    int insertSelective(VFemsCsrPjmember record);

    List<VFemsCsrPjmember> selectByUserId(@Param("userId") String userId);

    List<String> selectByUpId(@Param("upId") String upId);
}