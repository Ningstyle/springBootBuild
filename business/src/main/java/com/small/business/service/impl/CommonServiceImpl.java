package com.small.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.business.dao.TestMapper;
import com.small.business.dao.VFemsCsrSystemMapper;
import com.small.business.entity.po.Test;
import com.small.business.entity.po.VFemsCsrSystem;
import com.small.business.entity.vo.Employee;
import com.small.business.service.CommonService;
import com.small.business.service.TestService;
import com.small.business.utils.BlankUtil;
import com.small.business.utils.FileUtil;
import com.small.business.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.date.DateTime.now;

/**
 * 类详细描述：
 *
 * @author Eddy Zhang
 * @version 1.0
 * 创建时间：2020/11/4 15:00
 */
@Service
@Transactional
public class CommonServiceImpl extends ServiceImpl<VFemsCsrSystemMapper,VFemsCsrSystem> implements CommonService {

    private static Logger logger = LogManager.getLogger(CommonServiceImpl.class);

    @Autowired
    private VFemsCsrSystemMapper vFemsCsrSystemMapper;
    @Value("${upload}")
    private String uploadresource;

    @Override
    public int saveSystem(VFemsCsrSystem vFemsCsrSystem, MultipartFile file, Employee employee) {
        //上传附件
        String fileName = null;
        if (file != null) {
            fileName = file.getOriginalFilename();
            //文件上传 路径
            fileName = Utils.getUUID() + "_" + fileName;
            String realPath = uploadresource;
//            String realPath = "D:\\file\\business";
            File newfile = new File(realPath);
            if (!newfile.exists() && !newfile.isDirectory()) {
                System.out.println(realPath + " 目录不存在，创建该目录");
                newfile.mkdirs();
            }
//            fileName = newfile + "/" + fileName;
            FileUtil.toFile(file, new File(newfile + "/"+fileName));
        }

        if (!BlankUtil.isBlank(vFemsCsrSystem.getSysId())){
            VFemsCsrSystem oldVFemsCsrSystem = vFemsCsrSystemMapper.selectByPrimaryKey(vFemsCsrSystem.getSysId());
            if (!BlankUtil.isBlank(fileName)){
                oldVFemsCsrSystem.setIcon(fileName);
            }
            if (!BlankUtil.isBlank(vFemsCsrSystem.getMainColor())){
                oldVFemsCsrSystem.setMainColor(vFemsCsrSystem.getMainColor());
            }
            if (!BlankUtil.isBlank(vFemsCsrSystem.getSysPhoneUrl())){
                oldVFemsCsrSystem.setSysPhoneUrl(vFemsCsrSystem.getSysPhoneUrl());
            }
            if (!BlankUtil.isBlank(vFemsCsrSystem.getSysUrl())){
                oldVFemsCsrSystem.setSysUrl(vFemsCsrSystem.getSysUrl());
            }
            if (!BlankUtil.isBlank(vFemsCsrSystem.getSysName())){
                oldVFemsCsrSystem.setSysName(vFemsCsrSystem.getSysName());
            }
            oldVFemsCsrSystem.setUpdateTime(now());
            oldVFemsCsrSystem.setUpdateUserId(employee.getUserId());
            oldVFemsCsrSystem.setUpdateUserName(employee.getUserName());
            int i =  vFemsCsrSystemMapper.updateByPrimaryKey(oldVFemsCsrSystem);
            return i;
        }

        vFemsCsrSystem.setIcon(fileName);
        vFemsCsrSystem.setCreateTime(now());
        vFemsCsrSystem.setUpdateTime(now());
        vFemsCsrSystem.setUpdateUserId(employee.getUserId());
        vFemsCsrSystem.setCreateUserId(employee.getUserId());
        vFemsCsrSystem.setUpdateUserName(employee.getUserName());
        vFemsCsrSystem.setCreateUserName(employee.getUserName());
        int i = vFemsCsrSystemMapper.insert(vFemsCsrSystem);
        return i;
    }

    @Override
    public IPage<Map<String, Object>> getSystemList(IPage<Map<String, Object>> page , QueryWrapper<VFemsCsrSystem> queryWrapper) {
        return vFemsCsrSystemMapper.selectMapsPage(page,queryWrapper);
    }

    @Override
    public int deleteSystem(VFemsCsrSystem vFemsCsrSystem) {
        return vFemsCsrSystemMapper.deleteByPrimaryKey(vFemsCsrSystem.getSysId());
    }
}
