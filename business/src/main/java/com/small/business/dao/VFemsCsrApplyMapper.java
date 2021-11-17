package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.small.business.entity.po.VFemsCsrApply;
import com.small.business.entity.vo.Employee;
import com.small.business.entity.vo.VFemsCsrApplyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VFemsCsrApplyMapper extends BaseMapper<VFemsCsrApply> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrApply record);

    int insertSelective(VFemsCsrApply record);

    VFemsCsrApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrApply record);

    int updateByPrimaryKey(VFemsCsrApply record);

    List<VFemsCsrApplyVo> selectApplyList(IPage<VFemsCsrApply> page, @Param("satus") int status);

    VFemsCsrApplyVo selectApplyDetail(@Param("connId") String connId);

    List<VFemsCsrApplyVo> selectListByUserId(IPage<VFemsCsrApplyVo> page,@Param("employee") Employee employee);
    List<VFemsCsrApplyVo> selectApplyLog(IPage<VFemsCsrApplyVo> page,@Param("upId") String upId);
    List<VFemsCsrApplyVo> selectExLog(IPage<VFemsCsrApplyVo> page,@Param("upId") String upId);


}