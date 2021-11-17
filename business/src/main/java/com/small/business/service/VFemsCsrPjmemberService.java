package com.small.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.small.business.entity.po.VFemsCsrPjmember;

import java.util.List;
import java.util.Map;

public interface VFemsCsrPjmemberService extends IService<VFemsCsrPjmember> {

    List<VFemsCsrPjmember> selectUserByid(String userId);

}
