package com.small.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.small.business.entity.po.Test;
import com.small.business.entity.po.VFemsCsrSystem;
import com.small.business.entity.vo.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CommonService extends IService<VFemsCsrSystem> {

    int saveSystem(VFemsCsrSystem vFemsCsrSystem, MultipartFile file, Employee employee);

    IPage<Map<String, Object>> getSystemList(IPage<Map<String, Object>> page , QueryWrapper<VFemsCsrSystem> queryWrapper);

    int deleteSystem(VFemsCsrSystem vFemsCsrSystem);
}
