package com.small.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.small.business.common.annotation.Login;
import com.small.business.common.enums.BusinessCodeEnum;
import com.small.business.dao.*;
import com.small.business.entity.po.*;
import com.small.business.entity.vo.*;
import com.small.business.service.*;
import com.small.business.utils.*;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.result.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 类详细描述：
 *
 * @author Eddy Zhang
 * @version 1.0
 * 创建时间：2020/11/4 15:00
 */
@Slf4j
@RestController
@Api(description = "系统接口")
@RequestMapping("/common")
public class CommonController {

    private static Logger logger = LogManager.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;
    @Autowired
    private VFemsCsrProjectextService vFemsCsrProjectextService;
    @Autowired
    private VFemsCsrPjmemberService vFemsCsrPjmemberService;
    @Autowired
    private VFemsCsrUnitprofile2Service vFemsCsrUnitprofile2Service;
    @Autowired
    private VFemsCsrListConditionService vFemsCsrListConditionService;
    @Autowired
    private VFemsCsrListConditionMapper vFemsCsrListConditionMapper;
    @Autowired
    private VFemsCsrDetailListMapper vFemsCsrDetailListMapper;
    @Autowired
    private VFemsCsrTypeMapper vFemsCsrTypeMapper;
    @Autowired
    private VFemsCsrListConditionBakMapper vFemsCsrListConditionBakMapper;
    @Autowired
    private VFemsCsrDetailListBakMapper vFemsCsrDetailListBakMapper;
    @Autowired
    private VFemsCsrTypeBakMapper vFemsCsrTypeBakMapper;
    @Autowired
    private VFemsCsrNodeMapper vFemsCsrNodeMapper;
    @Autowired
    private VFemsCsrOverhaulOrderMapper vFemsCsrOverhaulOrderMapper;
    @Autowired
    private VFemsCsrConstructionOrderMapper vFemsCsrConstructionOrderMapper;
    @Autowired
    private VFemsCsrApplyMapper vFemsCsrApplyMapper;
    @Autowired
    private VFemsCsrExamineMapper vFemsCsrExamineMapper;
    @Autowired
    private VFemsCsrBoltConfigurationMapper vFemsCsrBoltConfigurationMapper;
    @Autowired
    private VFemsCsrBoltConfigurationLogMapper vFemsCsrBoltConfigurationLogMapper;
    @Autowired
    private VFemsCsrPjmemberMapper vFemsCsrPjmemberMapper;
    @Autowired
    private VFemsCsrSendMapper vFemsCsrSendMapper;
    @Autowired
    private VFemsCsrUnitprofile2Mapper vFemsCsrUnitprofile2Mapper;
    @Autowired
    private VFemsCsrBoltMapper vFemsCsrBoltMapper;
    @Autowired
    private VFemsCsrBoltLogMapper vFemsCsrBoltLogMapper;
    @Autowired
    private VFemsCsrBoltDataMapper vFemsCsrBoltDataMapper;
    @Autowired
    private BaseService baseService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${upload}")
    private String uploadresource;

    private static final String domian="http://localhost:9092/business/";
//    private static String domian="http://10.12.9.219:9092/business/";


    //系统相关接口 start========================================================================================================================================

    @Login
    @PostMapping("/saveSystem")
    @ApiOperation(value = "增加/更新系统")
    public ResultInfo saveSystem(VFemsCsrSystem vFemsCsrSystem,@RequestParam(required = false,value = "iconFile") MultipartFile file, Employee employee){
        if (BlankUtil.isBlank(vFemsCsrSystem)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrSystem.getSysName())|| BlankUtil.isBlank(vFemsCsrSystem.getSysUrl()) || BlankUtil.isBlank(vFemsCsrSystem.getSysPhoneUrl())
                || BlankUtil.isBlank(vFemsCsrSystem.getMainColor())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrSystem == "+vFemsCsrSystem.toString());
        int i = commonService.saveSystem(vFemsCsrSystem,file,  employee);
        return ResultInfo.ok(i);
    }

    @GetMapping("/getSystemList")
    @ApiOperation(value = "获取系统列表")
    public ResultInfo getSystemList(VFemsCsrSystem vFemsCsrSystem, Query query){
        IPage<Map<String, Object>> page = new Page<Map<String, Object>>(query.getCurrent(),query.getPageSize());
        QueryWrapper<VFemsCsrSystem> queryWrapper = new QueryWrapper<>();
        IPage<Map<String, Object>> mapIPage = commonService.getSystemList(page, queryWrapper);
        return ResultInfo.ok(mapIPage);
    }

    @GetMapping("/deleteSystem")
    @ApiOperation(value = "删除系统列表")
    public ResultInfo deleteSystem(VFemsCsrSystem vFemsCsrSystem){
        if (BlankUtil.isBlank(vFemsCsrSystem.getSysId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        return ResultInfo.ok(commonService.deleteSystem(vFemsCsrSystem));
    }

    //系统相关接口 end========================================================================================================================================


    //风场相关接口 start========================================================================================================================================

    @GetMapping("/getProjectTestByUser")
    @ApiOperation(value = "根据用户获取风场列表")
    public ResultInfo getProjectTestByUser(VFemsCsrProjectext vFemsCsrProjectext, Query query,String userId){
        if (BlankUtil.isBlank(userId)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        //查询用户信息
        List<VFemsCsrPjmember> vFemsCsrPjmemberList = vFemsCsrPjmemberService.selectUserByid(userId);
        if (BlankUtil.isBlank(vFemsCsrPjmemberList)){
            return ResultInfo.ok(null);
        }
        List<String> pjIds = new ArrayList<>();
        for (VFemsCsrPjmember vFemsCsrPjmember: vFemsCsrPjmemberList){
            pjIds.add(vFemsCsrPjmember.getPjmemPjId());
        }
        logger.info("vFemsCsrPjmemberList==="+vFemsCsrPjmemberList.toString());
        /*IPage<Map<String, Object>> page = new Page<Map<String, Object>>(query.getCurrent(),query.getPageSize());
        QueryWrapper<VFemsCsrProjectext> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("pj_id",pjIds);
        IPage<Map<String, Object>> mapIPage = vFemsCsrProjectextService.getProjectTestByUser(page, queryWrapper);*/
        IPage<VFemsCsrProjectext> page = new Page<VFemsCsrProjectext>(query.getCurrent(),query.getPageSize());
        IPage<VFemsCsrProjectext> mapIPage = vFemsCsrProjectextService.getProjectTest(vFemsCsrProjectext,page,pjIds);

        return ResultInfo.ok(mapIPage);
    }

    //风场相关接口 end========================================================================================================================================



    //风机相关接口 start========================================================================================================================================
    @GetMapping("/getUpByPjId")
    @ApiOperation(value = "根据用户获取风场列表")
    public ResultInfo getUpByPjId(VFemsCsrProjectext vFemsCsrProjectext, Query query,String userId){
        if (BlankUtil.isBlank(vFemsCsrProjectext.getPjId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrProjectext==="+vFemsCsrProjectext.toString());
        IPage<Map<String, Object>> page = new Page<Map<String, Object>>(query.getCurrent(),query.getPageSize());
        QueryWrapper<VFemsCsrUnitprofile2> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("up_pj_id",vFemsCsrProjectext.getPjId());
        if (!BlankUtil.isBlank(vFemsCsrProjectext.getUpSeatno())){
            queryWrapper.like("up_seatno",vFemsCsrProjectext.getUpSeatno());
            queryWrapper.like("up_running_position_no",vFemsCsrProjectext.getUpSeatno());
        }
        IPage<Map<String, Object>> mapIPage = vFemsCsrUnitprofile2Service.getUpByPjId(page, queryWrapper);


        return ResultInfo.ok(mapIPage);
    }

    @GetMapping("/getordersByUpId")
    @ApiOperation(value = "根据分机表的机组id和这两表关联获取数据")
    public ResultInfo getordersByUpId(VFemsCsrUnitprofile2 vFemsCsrUnitprofile2, Query query){
        if (BlankUtil.isBlank(vFemsCsrUnitprofile2) || BlankUtil.isBlank(vFemsCsrUnitprofile2.getUpId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        //查询用户信息
        Map map = new HashMap();
        map.put("task_up_id",vFemsCsrUnitprofile2.getUpId());
        IPage<VFemsCsrOverhaulOrderVo> page = new Page<VFemsCsrOverhaulOrderVo>(query.getCurrent(),query.getPageSize());
        List<VFemsCsrOverhaulOrderVo> vFemsCsrOverhaulOrderVoList = vFemsCsrOverhaulOrderMapper.selectByUpId(vFemsCsrUnitprofile2.getUpId(),page);
        return ResultInfo.ok(page.setRecords(vFemsCsrOverhaulOrderVoList));
    }

    //风场相关接口 end========================================================================================================================================



    //螺栓配置数据相关接口 start========================================================================================================================================
    @GetMapping("/getConfigurationByUpId")
    @ApiOperation(value = "根据分机表的机组id获取螺栓配置数据")
    public ResultInfo getConfigurationByUpId(VFemsCsrUnitprofile2 vFemsCsrUnitprofile2, Query query){
        if (BlankUtil.isBlank(vFemsCsrUnitprofile2) || BlankUtil.isBlank(vFemsCsrUnitprofile2.getUpId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
//        IPage<VFemsCsrBoltConfiguration> page = new Page<VFemsCsrBoltConfiguration>(query.getCurrent(),query.getPageSize());
//        QueryWrapper<VFemsCsrBoltConfiguration> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("up_id",vFemsCsrUnitprofile2.getUpId());
        VFemsCsrApplyVo vFemsCsrApplyVo = new VFemsCsrApplyVo();
        IPage<VFemsCsrBoltConfiguration> vFemsCsrBoltConfigurationList = baseService.getConfigurationByUpId(vFemsCsrUnitprofile2,query);
        Long total = vFemsCsrBoltConfigurationList.getTotal();
        List<VFemsCsrBoltConfiguration> list = vFemsCsrBoltConfigurationList.getRecords();
        if (!BlankUtil.isBlank(list)){
            String connId = list.get(0).getConnId();
            vFemsCsrApplyVo = vFemsCsrApplyMapper.selectApplyDetail(connId);
            vFemsCsrApplyVo.setTotal(BlankUtil.isBlank(total)?0:total);
            vFemsCsrApplyVo.setSize(query.getPageSize());
            vFemsCsrApplyVo.setCurrent(query.getCurrent());
            vFemsCsrApplyVo.setVFemsCsrBoltConfigurationList(list);
            if (!BlankUtil.isBlank(vFemsCsrApplyVo)&&vFemsCsrApplyVo.getStatus()!=0){
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("conn_id",connId);
                VFemsCsrExamine vFemsCsrExamine = vFemsCsrExamineMapper.selectOne(queryWrapper);
                if (!BlankUtil.isBlank(vFemsCsrExamine)){
                    vFemsCsrApplyVo.setExamineUserId(vFemsCsrExamine.getExamineUserId());
                    vFemsCsrApplyVo.setExamineUserName(vFemsCsrExamine.getExamineUserName());
                }
            }
            if (BlankUtil.isBlank(vFemsCsrApplyVo.getExamineUserName())){
                String name= vFemsCsrUnitprofile2Mapper.selectExaminUser(vFemsCsrUnitprofile2.getUpId());
                vFemsCsrApplyVo.setExamineUserName(BlankUtil.isBlank(name)?"":name);
            }

        }else {
            Map map = new HashMap();
            map.put("up_id",vFemsCsrUnitprofile2.getUpId());
            VFemsCsrUnitprofile2 vFemsCsrUnitprofile = (VFemsCsrUnitprofile2)vFemsCsrUnitprofile2Mapper.selectByMap(map).get(0);
            vFemsCsrApplyVo.setUpPjId(vFemsCsrUnitprofile.getUpPjId());
            vFemsCsrApplyVo.setUpPjName(vFemsCsrUnitprofile.getUpPjName());
            vFemsCsrApplyVo.setUpRunningPositionNo(vFemsCsrUnitprofile.getUpRunningPositionNo());
            vFemsCsrApplyVo.setUpSeatno(vFemsCsrUnitprofile.getUpSeatno());
            vFemsCsrApplyVo.setUpId(vFemsCsrUnitprofile.getUpPjId());
            String name= vFemsCsrUnitprofile2Mapper.selectExaminUser(vFemsCsrUnitprofile.getUpId());
            vFemsCsrApplyVo.setExamineUserName(BlankUtil.isBlank(name)?"":name);
            vFemsCsrApplyVo.setVFemsCsrBoltConfigurationList(null);
        }
        return ResultInfo.ok(vFemsCsrApplyVo);
    }

    @GetMapping("/getAllConfigurations")
    @ApiOperation(value = "查询所有配置了螺栓配置数据的机组")
    public ResultInfo getAllConfigurations(VFemsCsrUnitprofile2 vFemsCsrUnitprofile2, Query query){
        List<VFemsCsrUpVo> vFemsCsrBoltConfigurationList = vFemsCsrBoltConfigurationMapper.selectUpIds();
        return ResultInfo.ok(vFemsCsrBoltConfigurationList);
    }

    @Login
    @PostMapping("/saveOrUpdateConfigurationByConId")
    @ApiOperation(value = "增加/更新螺栓配置")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 3600, rollbackFor = Exception.class)
    public ResultInfo saveOrUpdateConfigurationByConId(@RequestBody VFemsCsrListVo vFemsCsrListVo, Employee employee){
        if (BlankUtil.isBlank(vFemsCsrListVo)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrListVo.getId()) || BlankUtil.isBlank(vFemsCsrListVo.getVFemsCsrBoltConfigurationList())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrListVo == "+vFemsCsrListVo.toString());
        Map map = new HashMap();
        map.put("up_id",vFemsCsrListVo.getId());
        List<VFemsCsrBoltConfiguration> vFemsCsrBoltConfigurationList = vFemsCsrBoltConfigurationMapper.selectByMap(map);
        if(!BlankUtil.isBlank(vFemsCsrBoltConfigurationList)){
            Map map1 = new HashMap();
            map1.put("conn_id",vFemsCsrBoltConfigurationList.get(0).getConnId());
            List<VFemsCsrApply> vFemsCsrApplies = vFemsCsrApplyMapper.selectByMap(map1);
            if (vFemsCsrApplies.get(0).getStatus()==0){
                return new ResultInfo(BusinessCodeEnum.APPLYING);
            }
        }
        /*for (VFemsCsrBoltConfiguration femsCsrBoltConfiguration: vFemsCsrBoltConfigurationList){
            femsCsrBoltConfiguration.setUpdateTime(new Date());
            femsCsrBoltConfiguration.setId(null);
            vFemsCsrBoltConfigurationLogMapper.insertSelective(femsCsrBoltConfiguration);
        }*/
        vFemsCsrBoltConfigurationMapper.deleteByMap(map);
        int i = 0;
        String connid = String.valueOf(SnowflakeIdWorker.nextId());
        for (VFemsCsrBoltConfiguration vFemsCsrBoltConfiguration : vFemsCsrListVo.getVFemsCsrBoltConfigurationList()) {
            vFemsCsrBoltConfiguration.setId(null);
            if (BlankUtil.isBlank(vFemsCsrBoltConfiguration.getId())) {
                vFemsCsrBoltConfiguration.setUpId(vFemsCsrListVo.getId());
                vFemsCsrBoltConfiguration.setConnId(connid);
                vFemsCsrBoltConfiguration.setCreateTime(new Date());
                vFemsCsrBoltConfiguration.setCreateUserId(employee.getUserId());
                vFemsCsrBoltConfiguration.setCreateUserName(employee.getUserName());
                vFemsCsrBoltConfiguration.setUpdateTime(new Date());
                vFemsCsrBoltConfiguration.setUpdateUserId(employee.getUserId());
                vFemsCsrBoltConfiguration.setUpdateUserName(employee.getUserName());
            } else {
                vFemsCsrBoltConfiguration.setUpId(vFemsCsrListVo.getId());
                vFemsCsrBoltConfiguration.setConnId(connid);
                vFemsCsrBoltConfiguration.setUpdateTime(new Date());
                vFemsCsrBoltConfiguration.setUpdateUserId(employee.getUserId());
                vFemsCsrBoltConfiguration.setUpdateUserName(employee.getUserName());
            }
           i += vFemsCsrBoltConfigurationMapper.insertSelective(vFemsCsrBoltConfiguration);
            vFemsCsrBoltConfigurationLogMapper.insertSelective(vFemsCsrBoltConfiguration);
        }
        //提交审核
        VFemsCsrApply vFemsCsrApply = new VFemsCsrApply();
        vFemsCsrApply.setConnId(connid);
        vFemsCsrApply.setUpId(vFemsCsrListVo.getId());
        vFemsCsrApply.setApplyUserId(employee.getUserId());
        vFemsCsrApply.setApplyUserName(employee.getUserName());
        vFemsCsrApply.setApplyTime(new Date());
        vFemsCsrApply.setConnId(connid);
        vFemsCsrApply.setStatus(0);//wait apply
        vFemsCsrApply.setType(0);
        vFemsCsrApplyMapper.insertSelective(vFemsCsrApply);

        //通知
        List<String> toList = vFemsCsrPjmemberMapper.selectByUpId(vFemsCsrListVo.getId());
        //管理人员，推送，谁在什么时间修改了那块东西，提交了审核，重新提交或者提交，第二次提交就是重新提交


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("project","螺栓管理");
        jsonObject.put("method","提交审核");
        jsonObject.put("userName",employee.getUserName());
        jsonObject.put("time",new Date());
        jsonObject.put("connId",connid);
        jsonObject.put("type",0);
        String tijiao = null;
        if (!BlankUtil.isBlank(vFemsCsrBoltConfigurationList)){
            //修改
            tijiao = "重新提交";
            jsonObject.put("detail","重新提交");
        }else {
            //新增
            tijiao = "提交";
            jsonObject.put("detail","提交");
        }
        System.out.println(toList.toString());
        for (String str:toList){
            VFemsCsrSend vFemsCsrSend = new VFemsCsrSend();
            vFemsCsrSend.setProject("螺栓管理");
            vFemsCsrSend.setMethod("提交审核");
            vFemsCsrSend.setDetail(tijiao);
            vFemsCsrSend.setOperateUserId(employee.getUserId());
            vFemsCsrSend.setOperateUserName(employee.getUserName());
            vFemsCsrSend.setConnid(connid);
            vFemsCsrSend.setTime(new Date());
            vFemsCsrSend.setCreateTime(new Date());
            vFemsCsrSend.setIfRead(0);
            vFemsCsrSend.setUserId(str);
            vFemsCsrSend.setType(0);
            vFemsCsrSendMapper.insertSelective(vFemsCsrSend);
            jsonObject.put("id",vFemsCsrSend.getId());
            Map map1 = new HashMap();
            map1.put("userId",str);
            map1.put("message",jsonObject.toString());
            restTemplate.getForObject(domian+"socket?userId={userId}&message={message}" , String.class,map1);
        }
        return ResultInfo.ok(i);
    }

//    @Login
    @GetMapping("/getApplyList")
    @ApiOperation(value = "待审批螺栓配置数据列表")
    public ResultInfo getApplyList(VFemsCsrApply vFemsCsrApply, Query query,Employee employee){
        /*IPage<VFemsCsrApply> page = new Page<VFemsCsrApply>(query.getCurrent(),query.getPageSize());
        List<VFemsCsrApplyVo> vFemsCsrApplyList = vFemsCsrApplyMapper.selectApplyList(page,vFemsCsrApply.getStatus());
        IPage<VFemsCsrApplyVo> page1 = new Page<VFemsCsrApplyVo>(query.getCurrent(),query.getPageSize());
        List<VFemsCsrApplyVo> vFemsCsrApplyListAll = vFemsCsrApplyMapper.selectApplyList(new Page<>(),vFemsCsrApply.getStatus());
        page1.setTotal(vFemsCsrApplyListAll.size());
        page1.setRecords(vFemsCsrApplyList);*/
        IPage<VFemsCsrApplyVo> page = new Page<VFemsCsrApplyVo>(query.getCurrent(),query.getPageSize());
//        System.out.println("employee.getUserId()=="+employee.getUserId());
        employee.setUserId("51787");//TODO ===========================================================================
        List<VFemsCsrApplyVo> vFemsCsrApplyList = vFemsCsrApplyMapper.selectListByUserId(page,employee);
        IPage<VFemsCsrApplyVo> page1 = new Page<VFemsCsrApplyVo>(query.getCurrent(),query.getPageSize());
        List<VFemsCsrApplyVo> vFemsCsrApplyListAll = vFemsCsrApplyMapper.selectListByUserId(new Page<>(),employee);
        page1.setTotal(BlankUtil.isBlank(vFemsCsrApplyListAll)?0:vFemsCsrApplyListAll.size());
        page1.setRecords(vFemsCsrApplyList);
        return ResultInfo.ok(vFemsCsrApplyListAll);
    }

    @Login
    @PostMapping("/examine")
    @ApiOperation(value = "审核螺栓配置")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 3600, rollbackFor = Exception.class)
    public ResultInfo examine(@RequestBody VFemsCsrExamine vFemsCsrExamine, Employee employee){
        if (BlankUtil.isBlank(vFemsCsrExamine)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrExamine.getId()) || BlankUtil.isBlank(vFemsCsrExamine.getStatus())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (vFemsCsrExamine.getStatus()==2 && BlankUtil.isBlank(vFemsCsrExamine.getRemarks())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrListVo == "+vFemsCsrExamine.toString());

        VFemsCsrApply vFemsCsrApply = vFemsCsrApplyMapper.selectByPrimaryKey(vFemsCsrExamine.getId());
        if (vFemsCsrApply.getStatus()==1){
            return new ResultInfo(BusinessCodeEnum.EXAMINED);
        }

        vFemsCsrApply.setStatus(vFemsCsrExamine.getStatus());//0待审核 1审核通过 2审核失败
        vFemsCsrApplyMapper.updateByPrimaryKey(vFemsCsrApply);

        if (BlankUtil.isBlank(vFemsCsrExamine.getType())){
            vFemsCsrExamine.setType(0);
        }

        //提交审核
        VFemsCsrExamine vFemsCsrExamine1 = new VFemsCsrExamine();
        vFemsCsrExamine1.setConnId(vFemsCsrApply.getConnId());
        vFemsCsrExamine1.setUpId(vFemsCsrApply.getUpId());
        vFemsCsrExamine1.setStatus(vFemsCsrExamine.getStatus());
        vFemsCsrExamine1.setRemarks(vFemsCsrExamine.getRemarks());
        vFemsCsrExamine1.setExamineTime(new Date());
        vFemsCsrExamine1.setExamineUserId(employee.getUserId());
        vFemsCsrExamine1.setExamineUserName(employee.getUserName());//wait apply
        vFemsCsrExamine1.setType(vFemsCsrExamine.getType());
        int i = vFemsCsrExamineMapper.insertSelective(vFemsCsrExamine1);

        //通知  一般人员，推送，谁在什么时间对那条数据做了审核，审核意见是什么
        VFemsCsrSend vFemsCsrSend = new VFemsCsrSend();
        if (vFemsCsrExamine.getType()==0){
            vFemsCsrSend.setProject("螺栓管理");
        }else {
            vFemsCsrSend.setProject("文件上传");
        }
        vFemsCsrSend.setProject("螺栓管理");
        vFemsCsrSend.setMethod("审核");
        vFemsCsrSend.setOperateUserId(employee.getUserId());
        vFemsCsrSend.setOperateUserName(employee.getUserName());
        vFemsCsrSend.setConnid(vFemsCsrApply.getConnId());
        vFemsCsrSend.setTime(new Date());
        vFemsCsrSend.setCreateTime(new Date());
        vFemsCsrSend.setIfRead(0);
        vFemsCsrSend.setUserId(vFemsCsrApply.getApplyUserId());
        vFemsCsrSend.setType(vFemsCsrExamine.getType());

        JSONObject jsonObject = new JSONObject();
        if (vFemsCsrExamine.getType()==0){
            jsonObject.put("project","螺栓管理");
        }else {
            jsonObject.put("project","文件上传");
        }
        jsonObject.put("method","审核");
        jsonObject.put("operateUserName",employee.getUserName());
        jsonObject.put("connId",vFemsCsrApply.getConnId());
        jsonObject.put("time",new Date());
        jsonObject.put("type",vFemsCsrExamine.getType());
        if (vFemsCsrExamine.getStatus()==1){
            //审核通过
            vFemsCsrSend.setDetail("审核通过");
            vFemsCsrSend.setStatus(1);
            jsonObject.put("detail","审核通过");
            jsonObject.put("status","1");
        }else {
            //审核失败
            vFemsCsrSend.setDetail("审核失败");
            vFemsCsrSend.setStatus(0);
            vFemsCsrSend.setRemarks(vFemsCsrExamine.getRemarks());
            jsonObject.put("detail","审核失败");
            jsonObject.put("status","0");
            jsonObject.put("remarks",vFemsCsrExamine.getRemarks());
        }
        vFemsCsrSendMapper.insertSelective(vFemsCsrSend);
        jsonObject.put("id",vFemsCsrSend.getId());
        Map map = new HashMap();
        map.put("userId",vFemsCsrApply.getApplyUserId());
        map.put("message",jsonObject.toString());
        restTemplate.getForObject(domian+"socket?userId={userId}&message={message}" , String.class,map);
        return ResultInfo.ok(i);
    }

    @Login
    @PostMapping("/examineFile")
    @ApiOperation(value = "审核螺栓配置")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 3600, rollbackFor = Exception.class)
    public ResultInfo examineFile(@RequestBody VFemsCsrExamine vFemsCsrExamine, Employee employee){
        if (BlankUtil.isBlank(vFemsCsrExamine)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrExamine.getId()) || BlankUtil.isBlank(vFemsCsrExamine.getStatus())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (vFemsCsrExamine.getStatus()==2 && BlankUtil.isBlank(vFemsCsrExamine.getRemarks())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrListVo == "+vFemsCsrExamine.toString());

        VFemsCsrApply vFemsCsrApply = vFemsCsrApplyMapper.selectByPrimaryKey(vFemsCsrExamine.getId());
        if (vFemsCsrApply.getStatus()==1){
            return new ResultInfo(BusinessCodeEnum.EXAMINED);
        }

        vFemsCsrApply.setStatus(vFemsCsrExamine.getStatus());//0待审核 1审核通过 2审核失败
        vFemsCsrApplyMapper.updateByPrimaryKey(vFemsCsrApply);

        //提交审核
        VFemsCsrExamine vFemsCsrExamine1 = new VFemsCsrExamine();
        vFemsCsrExamine1.setConnId(vFemsCsrApply.getConnId());
        vFemsCsrExamine1.setUpId(vFemsCsrApply.getUpId());
        vFemsCsrExamine1.setStatus(vFemsCsrExamine.getStatus());
        vFemsCsrExamine1.setRemarks(vFemsCsrExamine.getRemarks());
        vFemsCsrExamine1.setExamineTime(new Date());
        vFemsCsrExamine1.setExamineUserId(employee.getUserId());
        vFemsCsrExamine1.setExamineUserName(employee.getUserName());//wait apply
        vFemsCsrExamine1.setType(1);
        int i = vFemsCsrExamineMapper.insertSelective(vFemsCsrExamine1);

        //通知  一般人员，推送，谁在什么时间对那条数据做了审核，审核意见是什么
        VFemsCsrSend vFemsCsrSend = new VFemsCsrSend();
        vFemsCsrSend.setProject("文件上传");
        vFemsCsrSend.setMethod("审核");
        vFemsCsrSend.setOperateUserId(employee.getUserId());
        vFemsCsrSend.setOperateUserName(employee.getUserName());
        vFemsCsrSend.setConnid(vFemsCsrApply.getConnId());
        vFemsCsrSend.setTime(new Date());
        vFemsCsrSend.setCreateTime(new Date());
        vFemsCsrSend.setIfRead(0);
        vFemsCsrSend.setUserId(vFemsCsrApply.getApplyUserId());
        vFemsCsrSend.setType(1);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("project","文件上传");
        jsonObject.put("method","审核");
        jsonObject.put("operateUserName",employee.getUserName());
        jsonObject.put("connId",vFemsCsrApply.getConnId());
        jsonObject.put("time",new Date());
        jsonObject.put("type",1);
        if (vFemsCsrExamine.getStatus()==1){
            //审核通过
            vFemsCsrSend.setDetail("审核通过");
            vFemsCsrSend.setStatus(1);
            jsonObject.put("detail","审核通过");
            jsonObject.put("status","1");
        }else {
            //审核失败
            vFemsCsrSend.setDetail("审核失败");
            vFemsCsrSend.setStatus(0);
            vFemsCsrSend.setRemarks(vFemsCsrExamine.getRemarks());
            jsonObject.put("detail","审核失败");
            jsonObject.put("status","0");
            jsonObject.put("remarks",vFemsCsrExamine.getRemarks());
        }
        vFemsCsrSendMapper.insertSelective(vFemsCsrSend);
        jsonObject.put("id",vFemsCsrSend.getId());
        Map map = new HashMap();
        map.put("userId",vFemsCsrApply.getApplyUserId());
        map.put("message",jsonObject.toString());
        restTemplate.getForObject(domian+"socket?userId={userId}&message={message}" , String.class,map);
        return ResultInfo.ok(i);
    }

//    @Login
    @GetMapping("/operateLog")
    @ApiOperation(value = "操作历史")//申请人修改的操作历史，审核人的操作历史
    public ResultInfo operateLog(VFemsCsrApplyVo vFemsCsrApply, Query query) {
        if (BlankUtil.isBlank(vFemsCsrApply)) {
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
       /* if (vFemsCsrApply.getType() == 0){//申请人修改的操作历史
            IPage<VFemsCsrApply> page = new Page<VFemsCsrApply>(query.getCurrent(),query.getPageSize());
            QueryWrapper queryWrapper = new QueryWrapper();
            if (!BlankUtil.isBlank(vFemsCsrApply.getUpId())){
                queryWrapper.eq("up_id",vFemsCsrApply.getUpId());
            }
            queryWrapper.orderByDesc("apply_time");
            IPage<VFemsCsrApply> vFemsCsrApplyList = vFemsCsrApplyMapper.selectPage(page,queryWrapper);
            return ResultInfo.ok(vFemsCsrApplyList);
        }
        IPage<VFemsCsrExamine> page = new Page<VFemsCsrExamine>(query.getCurrent(),query.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!BlankUtil.isBlank(vFemsCsrApply.getUpId())){
            queryWrapper.eq("up_id",vFemsCsrApply.getUpId());
        }
        queryWrapper.orderByDesc("examine_time");
        IPage<VFemsCsrExamine> vFemsCsrExamineList = vFemsCsrExamineMapper.selectPage(page,queryWrapper);*/
        IPage<VFemsCsrApplyVo> page = new Page<VFemsCsrApplyVo>(query.getCurrent(),query.getPageSize());
        if (vFemsCsrApply.getType()==0){
            List<VFemsCsrApplyVo> vFemsCsrApplyListAll  = vFemsCsrApplyMapper.selectApplyLog(new Page<>(),vFemsCsrApply.getUpId());
            List<VFemsCsrApplyVo> vFemsCsrApplyList  = vFemsCsrApplyMapper.selectApplyLog(page,vFemsCsrApply.getUpId());
            page.setRecords(vFemsCsrApplyList);
            page.setTotal(BlankUtil.isBlank(vFemsCsrApplyListAll)?0:vFemsCsrApplyListAll.size());
            return ResultInfo.ok(page);
        }
        List<VFemsCsrApplyVo> vFemsCsrApplyListAll  = vFemsCsrApplyMapper.selectExLog(new Page<>(),vFemsCsrApply.getUpId());
        List<VFemsCsrApplyVo> vFemsCsrApplyList  = vFemsCsrApplyMapper.selectExLog(page,vFemsCsrApply.getUpId());
        page.setRecords(vFemsCsrApplyList);
        page.setTotal(BlankUtil.isBlank(vFemsCsrApplyListAll)?0:vFemsCsrApplyListAll.size());
        return ResultInfo.ok(page);
    }

    @GetMapping("/getCompareList")
    @ApiOperation(value = "操作历史")//申请人修改的操作历史，审核人的操作历史
    public ResultInfo getCompareList(VFemsCsrApplyVo vFemsCsrApply, Query query) {
        if (BlankUtil.isBlank(vFemsCsrApply) || BlankUtil.isBlank(vFemsCsrApply.getUpId())) {
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        VFemsCsrUpVo vFemsCsrUpVo = new VFemsCsrUpVo();
        Map map = new HashMap();
        map.put("up_id",vFemsCsrApply.getUpId());
        List<VFemsCsrBoltConfiguration> newOne = vFemsCsrBoltConfigurationMapper.selectByMap(map);
        String coonId = null;
        if (!BlankUtil.isBlank(newOne)){
            vFemsCsrUpVo.setVFemsCsrBoltConfiguration(newOne);
            coonId = vFemsCsrBoltConfigurationLogMapper.selectBefore(newOne.get(0));
            logger.info("connid=====" +coonId);
            /*Map map1 = new HashMap();
            map1.put("conn_id",coonId);*/

            List<VFemsCsrBoltConfigurationLog> old = vFemsCsrBoltConfigurationLogMapper.selectBeforeList(coonId);
            vFemsCsrUpVo.setOldVFemsCsrBoltConfiguration(old);
            return ResultInfo.ok(vFemsCsrUpVo);
        }else {
            return ResultInfo.ok(vFemsCsrUpVo);
        }
    }

//    @Login
    @GetMapping("/getDetailByUpId")
    @ApiOperation(value = "根据机组ID查出查出相关信息")
    public ResultInfo getDetailByUpId(VFemsCsrApplyVo vFemsCsrApply, Query query){
        IPage<VFemsCsrApplyVo> page = new Page<VFemsCsrApplyVo>(query.getCurrent(),query.getPageSize());
        /*
        List<VFemsCsrApplyVo> vFemsCsrApplyList = vFemsCsrApplyMapper.selectApplyList(page,vFemsCsrApply.getStatus());
        return ResultInfo.ok(vFemsCsrApplyList);*/

        if (vFemsCsrApply.getType()==0){
            List<VFemsCsrApplyVo> vFemsCsrApplyList  = vFemsCsrApplyMapper.selectApplyLog(page,vFemsCsrApply.getUpId());
            return ResultInfo.ok(vFemsCsrApplyList);
        }
        List<VFemsCsrApplyVo> vFemsCsrApplyList  = vFemsCsrApplyMapper.selectExLog(page,vFemsCsrApply.getUpId());
        return ResultInfo.ok(vFemsCsrApplyList);
    }

    @Login
    @GetMapping("/getSendList")
    @ApiOperation(value = "获取推送列表")
    public ResultInfo getSendList(VFemsCsrSend vFemsCsrSend,int type, Query query,Employee employee){
        IPage<VFemsCsrSend> page = new Page<VFemsCsrSend>(query.getCurrent(),query.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",employee.getUserId());
        if (!BlankUtil.isBlank(vFemsCsrSend) && !BlankUtil.isBlank(vFemsCsrSend.getIfRead())){
            queryWrapper.eq("if_read",vFemsCsrSend.getIfRead());
        }
        queryWrapper.orderByDesc("create_time");
        return ResultInfo.ok(vFemsCsrSendMapper.selectPage(page,queryWrapper));
    }

    @Login
    @GetMapping("/read")
    @ApiOperation(value = "已读")
    public ResultInfo read(VFemsCsrSend vFemsCsrSend, Query query,Employee employee){
        if (BlankUtil.isBlank(vFemsCsrSend) || BlankUtil.isBlank(vFemsCsrSend.getId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        VFemsCsrSend vFemsCsrSendOld = vFemsCsrSendMapper.selectByPrimaryKey(vFemsCsrSend.getId());
        vFemsCsrSendOld.setIfRead(1);
        vFemsCsrSendOld.setUpdateTime(new Date());
        return ResultInfo.ok(vFemsCsrSendMapper.updateByPrimaryKey(vFemsCsrSendOld));
    }
    //螺栓配置数据相关接口 end========================================================================================================================================



    //螺栓文件上传相关接口 start========================================================================================================================================
    @Login
    @GetMapping("/getBoltList")
    @ApiOperation(value = "获取螺栓文件列表")
    public ResultInfo getBoltList(VFemsCsrApplyVo vFemsCsrApplyVo, Query query,Employee employee){
        IPage<VFemsCsrBolt> page = new Page<VFemsCsrBolt>(query.getCurrent(),query.getPageSize());
        vFemsCsrApplyVo.setApplyUserId(employee.getUserId());
        return ResultInfo.ok(vFemsCsrBoltMapper.selectBoltExaminList(page,vFemsCsrApplyVo));
    }

    @Login
    @GetMapping("/getBoltData")
    @ApiOperation(value = "根据唯一值查看这个文件的信息")//根据唯一值查看这个文件的信息，可以用个节点，螺栓编号筛选、
    public ResultInfo getBoltData(VFemsCsrApplyVo vFemsCsrApplyVo, Query query,Employee employee){
        IPage<VFemsCsrBolt> page = new Page<VFemsCsrBolt>(query.getCurrent(),query.getPageSize());
        vFemsCsrApplyVo.setApplyUserId(employee.getUserId());
        return ResultInfo.ok(vFemsCsrBoltMapper.selectBoltExaminList(page,vFemsCsrApplyVo));
    }

    @Login
    @PostMapping("/saveOrUpdateBolt")
    @ApiOperation(value = "增加/更新螺栓文件")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 3600, rollbackFor = Exception.class)
    public ResultInfo saveOrUpdateBolt(VFemsCsrBolt vFemsCsrBolt,@RequestParam("file") MultipartFile multipartFile ,Employee employee){
        if (BlankUtil.isBlank(vFemsCsrBolt)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(multipartFile) || BlankUtil.isBlank(multipartFile.getOriginalFilename())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("multipartFile.getOriginalFilename() == "+multipartFile.getOriginalFilename());
        VFemsCsrBolt vFemsCsrBoltOld = vFemsCsrBoltMapper.selectByPrimaryKey(vFemsCsrBolt.getId());
        if(!BlankUtil.isBlank(vFemsCsrBoltOld)){
            Map map1 = new HashMap();
            map1.put("conn_id",vFemsCsrBoltOld.getId());
            List<VFemsCsrApply> vFemsCsrApplies = vFemsCsrApplyMapper.selectByMap(map1);
            if (vFemsCsrApplies.get(0).getStatus()==0){
                return new ResultInfo(BusinessCodeEnum.APPLYING);
            }
        }
//        vFemsCsrBoltMapper.deleteByPrimaryKey(vFemsCsrBolt.getId());
        int i = 0;
        if (BlankUtil.isBlank(vFemsCsrBoltOld)) {
            //文件上传
            if (!BlankUtil.isBlank(multipartFile)){
                //上传附件
                String fileName = null;
                fileName = multipartFile.getOriginalFilename();
                //文件上传 路径
                String realPath = uploadresource;
//                String realPath = "E:/Users";
                File newfile = new File(realPath, "/bolt-file/");
                if (!newfile.exists() && !newfile.isDirectory()) {
                    System.out.println(realPath + " 目录不存在，创建该目录");
                    newfile.mkdirs();
                }
                FileUtil.toFile(multipartFile, new File(realPath + "/bolt-file/" + fileName));
                vFemsCsrBolt.setFilePath("/bolt-file/" +fileName);
            }
            vFemsCsrBolt.setUploadTime(new Date());
            vFemsCsrBolt.setCreateUserId(employee.getUserId());
            vFemsCsrBolt.setCreateUserName(employee.getUserName());
            i += vFemsCsrBoltMapper.insertSelective(vFemsCsrBolt);


            //读取文件
            try {
                readFile(vFemsCsrBolt);
            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            vFemsCsrBolt.setUploadTime(vFemsCsrBolt.getUploadTime());
            vFemsCsrBolt.setCreateUserName(vFemsCsrBolt.getUpdateUserName());
            vFemsCsrBolt.setCreateUserId(vFemsCsrBolt.getCreateUserId());
            vFemsCsrBolt.setUpdateTime(new Date());
            vFemsCsrBolt.setUpdateUserId(employee.getUserId());
            vFemsCsrBolt.setUpdateUserName(employee.getUserName());
            VFemsCsrBoltLog vFemsCsrBoltLog = new VFemsCsrBoltLog();
            CopyUtils.copyProperties(vFemsCsrBolt,vFemsCsrBoltLog);
            vFemsCsrBoltLogMapper.insertSelective(vFemsCsrBoltLog);

            i +=vFemsCsrBoltMapper.updateByPrimaryKey(vFemsCsrBolt);
        }

        //提交审核
        VFemsCsrApply vFemsCsrApply = new VFemsCsrApply();
        vFemsCsrApply.setUpId(vFemsCsrBolt.getUpId());
        vFemsCsrApply.setConnId(vFemsCsrBolt.getId().toString());
        vFemsCsrApply.setApplyUserId(employee.getUserId());
        vFemsCsrApply.setApplyUserName(employee.getUserName());
        vFemsCsrApply.setApplyTime(new Date());
        vFemsCsrApply.setStatus(0);//wait apply
        vFemsCsrApply.setType(1);
        vFemsCsrApplyMapper.insertSelective(vFemsCsrApply);

        //通知
        List<String> toList = vFemsCsrPjmemberMapper.selectByUpId(vFemsCsrBolt.getUpId());
        //管理人员，推送，谁在什么时间修改了那块东西，提交了审核，重新提交或者提交，第二次提交就是重新提交
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("project","文件与风场关联");
        jsonObject.put("method","提交审核");
        jsonObject.put("userName",employee.getUserName());
        jsonObject.put("time",new Date());
        jsonObject.put("connId",vFemsCsrBolt.getId().toString());
        jsonObject.put("type",1);
        String tijiao = null;
        if (!BlankUtil.isBlank(vFemsCsrBoltOld)){
            //修改
            tijiao = "重新提交";
            jsonObject.put("detail","重新提交");
        }else {
            //新增
            tijiao = "提交";
            jsonObject.put("detail","提交");
        }
        System.out.println(toList.toString());
        for (String str:toList){
            VFemsCsrSend vFemsCsrSend = new VFemsCsrSend();
            vFemsCsrSend.setProject("文件与风场关联");
            vFemsCsrSend.setMethod("提交审核");
            vFemsCsrSend.setDetail(tijiao);
            vFemsCsrSend.setOperateUserId(employee.getUserId());
            vFemsCsrSend.setOperateUserName(employee.getUserName());
            vFemsCsrSend.setConnid(vFemsCsrBolt.getId().toString());
            vFemsCsrSend.setTime(new Date());
            vFemsCsrSend.setCreateTime(new Date());
            vFemsCsrSend.setIfRead(0);
            vFemsCsrSend.setUserId(str);
            vFemsCsrSend.setType(1);
            vFemsCsrSendMapper.insertSelective(vFemsCsrSend);
            jsonObject.put("id",vFemsCsrSend.getId());
            Map map1 = new HashMap();
            map1.put("userId",str);
            map1.put("message",jsonObject.toString());
            restTemplate.getForObject(domian+"socket?userId={userId}&message={message}" , String.class,map1);
        }
        return ResultInfo.ok(i);
    }

    public  String readFile(VFemsCsrBolt vFemsCsrBolt) throws IOException{
        StringBuffer msgs = new StringBuffer("");

        Map<String,Object> SheetMaps=new HashMap<>();

        Iterator<Sheet> sheets = ExcelUtils.getAllSheets("E:/Users"+vFemsCsrBolt.getFilePath());
        Map<String,List<VFemsCsrBolt>> sheetsMap = new HashMap<>();//存储sheet名称为key，内容为values

        while (sheets.hasNext()) {

            Sheet sheet = sheets.next();
            String name = sheet.getSheetName();
            int rowIndex = sheet.getLastRowNum();
            if (rowIndex == 0) {
               return "您导入的excel未填值!";
            }
            int total1 = 0;
            if ("文件信息".equals(name)){
                Row row = ExcelUtils.getRow(sheet, 1);
                if (!BlankUtil.isBlank(ExcelUtils.formattingCell(row.getCell(1)))) {
                    vFemsCsrBolt.setFileName(ExcelUtils.formattingCell(row.getCell(0)));
                    vFemsCsrBolt.setEquipmentInfo(ExcelUtils.formattingCell(row.getCell(1)));
//                    vFemsCsrBolt.setBoltNo(ExcelUtils.formattingCell(row.getCell(2)));
                    vFemsCsrBolt.setOperator(ExcelUtils.formattingCell(row.getCell(2)));
                    vFemsCsrBolt.setUpName(ExcelUtils.formattingCell(row.getCell(3)));
                    total1 += vFemsCsrBoltMapper.updateByPrimaryKey(vFemsCsrBolt);
                    VFemsCsrBoltLog vFemsCsrBoltLog = new VFemsCsrBoltLog();
                    CopyUtils.copyProperties(vFemsCsrBolt,vFemsCsrBoltLog);
                    vFemsCsrBoltLog.setId(null);
                    vFemsCsrBoltLog.setBoltId(vFemsCsrBolt.getId());
                    vFemsCsrBoltLogMapper.insertSelective(vFemsCsrBoltLog);
                }
            }
            int total2 = 0;
            if ("数据".equals(name)){
                List<VFemsCsrBoltData> vFemsCsrBoltDataList = new ArrayList<VFemsCsrBoltData>();
                for (int i = 1; i <= rowIndex; i++) {
                    Row row = ExcelUtils.getRow(sheet, i);
                    if (row != null && !ExcelUtils.isRowEmpty(row)) {
                        VFemsCsrBoltData vFemsCsrBoltData = new VFemsCsrBoltData();
                        if (!BlankUtil.isBlank(ExcelUtils.formattingCell(row.getCell(1)))) {
                            vFemsCsrBoltData.setBoltId(vFemsCsrBolt.getId());
                            vFemsCsrBoltData.setNodeNo(ExcelUtils.formattingCell(row.getCell(1)));
                            vFemsCsrBoltData.setBoltNo(ExcelUtils.formattingCell(row.getCell(2)));
                            vFemsCsrBoltData.setNum(ExcelUtils.formattingCell(row.getCell(3)));
                            vFemsCsrBoltData.setTime(ExcelUtils.formattingCell(row.getCell(4)));
                            vFemsCsrBoltData.setQuantity(ExcelUtils.formattingCell(row.getCell(5)));
//                            vFemsCsrBoltDataList.add(vFemsCsrBoltData);
                            total2 += vFemsCsrBoltDataMapper.insertSelective(vFemsCsrBoltData);
                        }
                    }
                }
//                vFemsCsrBoltDataMapper.insertList(vFemsCsrBoltDataList);
            }
        }

        return null;

    }



    //螺栓文件上传相关接口 end========================================================================================================================================




    //力矩清单相关接口 start======================================================================================================================================
    @GetMapping("/getTorqueListCondition")
    @ApiOperation(value = "获取力矩清单条件列表")
    public ResultInfo getTorqueListCondition(VFemsCsrListCondition vFemsCsrListCondition, Query query, String userId){
        IPage<VFemsCsrListCondition> page = new Page<VFemsCsrListCondition>(query.getCurrent(),query.getPageSize());
        QueryWrapper<VFemsCsrListCondition> queryWrapper = new QueryWrapper<>();
        IPage<VFemsCsrListCondition> mapIPage = vFemsCsrListConditionService.getTorqueListCondition(page, queryWrapper);
        return ResultInfo.ok(mapIPage);
    }

    @GetMapping("/getTorqueListConditionBak")
    @ApiOperation(value = "获取力矩清单条件列表Bak")
    public ResultInfo getTorqueListConditionBak(VFemsCsrListCondition vFemsCsrListCondition, Query query, String userId){
        IPage<VFemsCsrListConditionBak> page = new Page<VFemsCsrListConditionBak>(query.getCurrent(),query.getPageSize());
        QueryWrapper<VFemsCsrListConditionBak> queryWrapper = new QueryWrapper<>();
        return ResultInfo.ok( vFemsCsrListConditionBakMapper.selectPage(page,queryWrapper));
    }

    @Login
    @PostMapping("/saveOrUpdateCondition")
    @ApiOperation(value = "增加/更新力矩清单条件")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 3600, rollbackFor = Exception.class)
    public ResultInfo saveOrUpdateCondition(@RequestBody VFemsCsrListCondition vFemsCsrListCondition, Employee employee){
        if (BlankUtil.isBlank(vFemsCsrListCondition)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrListCondition.getName())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrListCondition == "+vFemsCsrListCondition.toString());
        if (BlankUtil.isBlank(vFemsCsrListCondition.getId())){
            vFemsCsrListCondition.setCreateTime(new Date());
            vFemsCsrListCondition.setCreateUserId(employee.getUserId());
            vFemsCsrListCondition.setCreateUserName(employee.getUserName());
            vFemsCsrListCondition.setUpdateTime(new Date());
            vFemsCsrListCondition.setUpdateUserId(employee.getUserId());
            vFemsCsrListCondition.setUpdateUserName(employee.getUserName());
            int i = vFemsCsrListConditionMapper.insertSelective(vFemsCsrListCondition);
            log.info("vFemsCsrListCondition="+vFemsCsrListCondition.toString());
            vFemsCsrListConditionBakMapper.insertSelective(vFemsCsrListConditionMapper.selectByPrimaryKey(vFemsCsrListCondition.getId()));

            return ResultInfo.ok(i);
        }else {
            vFemsCsrListCondition.setUpdateTime(new Date());
            vFemsCsrListCondition.setUpdateUserId(employee.getUserId());
            vFemsCsrListCondition.setUpdateUserName(employee.getUserName());
            vFemsCsrListConditionBakMapper.updateByPrimaryKeySelective(vFemsCsrListConditionMapper.selectByPrimaryKey(vFemsCsrListCondition.getId()));
            int i = vFemsCsrListConditionMapper.updateByPrimaryKeySelective(vFemsCsrListCondition);
            return ResultInfo.ok(i);
        }
    }

    @GetMapping("/deleteCondition")
    @ApiOperation(value = "删除力矩清单条件")
    public ResultInfo deleteCondition(VFemsCsrListCondition vFemsCsrListCondition){
        if (BlankUtil.isBlank(vFemsCsrListCondition)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrListCondition.getId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrListCondition == "+vFemsCsrListCondition.toString());
        int i = vFemsCsrListConditionMapper.deleteByPrimaryKey(vFemsCsrListCondition.getId());
        if (i>0){
            vFemsCsrListConditionBakMapper.deleteByPrimaryKey(vFemsCsrListCondition.getId());
            Map map = new HashMap();
            map.put("con_id",vFemsCsrListCondition.getId());
            vFemsCsrDetailListMapper.deleteByMap(map);
            vFemsCsrTypeMapper.deleteByMap(map);
        }
        return ResultInfo.ok(i);
    }

    @GetMapping("/deleteType")
    @ApiOperation(value = "删除适用机型")
    public ResultInfo deleteType(VFemsCsrListCondition vFemsCsrListCondition){
        if (BlankUtil.isBlank(vFemsCsrListCondition)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrListCondition.getId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrListCondition == "+vFemsCsrListCondition.toString());
        int i = vFemsCsrTypeMapper.deleteByPrimaryKey(vFemsCsrListCondition.getId());
        vFemsCsrTypeBakMapper.deleteByPrimaryKey(vFemsCsrListCondition.getId());
        return ResultInfo.ok(i);
    }

    @GetMapping("/deleteDetailList")
    @ApiOperation(value = "删除力矩清单")
    public ResultInfo deleteDetailList(VFemsCsrListCondition vFemsCsrListCondition){
        if (BlankUtil.isBlank(vFemsCsrListCondition)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrListCondition.getId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrListCondition == "+vFemsCsrListCondition.toString());
        int i = vFemsCsrDetailListMapper.deleteByPrimaryKey(vFemsCsrListCondition.getId());
        vFemsCsrDetailListBakMapper.deleteByPrimaryKey(vFemsCsrListCondition.getId());
        return ResultInfo.ok(i);
    }


    @GetMapping("/getAllByListCondition")
    @ApiOperation(value = "根据力矩清单条件获取详细列表")
    public ResultInfo getAllByListCondition(VFemsCsrListCondition vFemsCsrListCondition, Query query, String userId){
        if (BlankUtil.isBlank(vFemsCsrListCondition) || BlankUtil.isBlank(vFemsCsrListCondition.getId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        IPage<VFemsCsrDetailList> page = new Page<VFemsCsrDetailList>(query.getCurrent(),query.getPageSize());
        VFemsCsrListVo vFemsCsrListVo = vFemsCsrListConditionService.getAllByListCondition(page, vFemsCsrListCondition);
        return ResultInfo.ok(vFemsCsrListVo);
    }

    @GetMapping("/getAllByListConditionBak")
    @ApiOperation(value = "根据力矩清单条件获取详细列表Bak")
    public ResultInfo getAllByListConditionBak(VFemsCsrListCondition vFemsCsrListCondition, Query query, String userId){
        if (BlankUtil.isBlank(vFemsCsrListCondition) || BlankUtil.isBlank(vFemsCsrListCondition.getId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        IPage<VFemsCsrDetailList> page = new Page<VFemsCsrDetailList>(query.getCurrent(),query.getPageSize());
        VFemsCsrListVo vFemsCsrListVo = new VFemsCsrListVo();
        vFemsCsrListVo.setId(vFemsCsrListCondition.getId().toString());
        List<VFemsCsrType> vFemsCsrTypeList = new ArrayList<>();
        vFemsCsrTypeList = vFemsCsrTypeBakMapper.selectByConId(vFemsCsrListCondition.getId().toString());
        vFemsCsrListVo.setVFemsCsrTypeList(vFemsCsrTypeList);
        IPage<VFemsCsrDetailList> vFemsCsrDetailList = new Page<>() ;
        if (vFemsCsrDetailListBakMapper.selectByConId(vFemsCsrListCondition.getId().toString(),page).size()>0)
            vFemsCsrDetailList = page.setRecords(vFemsCsrDetailListBakMapper.selectByConId(vFemsCsrListCondition.getId().toString(),page));
        if (!BlankUtil.isBlank(vFemsCsrDetailList)){
            vFemsCsrListVo.setVFemsCsrDetailList(vFemsCsrDetailList.getRecords());
        }else {
            vFemsCsrListVo.setVFemsCsrDetailList(new ArrayList<>());
        }
        return ResultInfo.ok(vFemsCsrListVo);
    }

    @Login
    @PostMapping("/saveOrUpdateAllByConId")
    @ApiOperation(value = "增加/更新力矩清单、适用机型类型")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 3600, rollbackFor = Exception.class)
    public ResultInfo saveOrUpdateAllByConId(@RequestBody VFemsCsrListVo vFemsCsrListVo, Employee employee){
        if (BlankUtil.isBlank(vFemsCsrListVo)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrListVo.getId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrListVo == "+vFemsCsrListVo.toString());
        Map map = new HashMap();
        map.put("con_id",vFemsCsrListVo.getId());
        vFemsCsrTypeBakMapper.deleteByMap(map);
        vFemsCsrDetailListBakMapper.deleteByMap(map);
        List<VFemsCsrType> vFemsCsrTypeList = vFemsCsrTypeMapper.selectByConId(vFemsCsrListVo.getId());
        for (VFemsCsrType vFemsCsrType: vFemsCsrTypeList){
            vFemsCsrTypeBakMapper.insertSelective(vFemsCsrType);
        }
        List<VFemsCsrDetailList> vFemsCsrDetailListList = vFemsCsrDetailListMapper.selectByConId(vFemsCsrListVo.getId(),new Page());
        for (VFemsCsrDetailList vFemsCsrDetailList: vFemsCsrDetailListList){
            vFemsCsrDetailListBakMapper.insertSelective(vFemsCsrDetailList);
        }
        vFemsCsrTypeMapper.deleteByMap(map);
        vFemsCsrDetailListMapper.deleteByMap(map);
        int i = 0;
        if (!BlankUtil.isBlank(vFemsCsrListVo.getVFemsCsrTypeList())) {
            for (VFemsCsrType vFemsCsrType : vFemsCsrListVo.getVFemsCsrTypeList()) {
                if (BlankUtil.isBlank(vFemsCsrType.getId())) {
                    vFemsCsrType.setCreateTime(new Date());
                    vFemsCsrType.setCreateUserId(employee.getUserId());
                    vFemsCsrType.setCreateUserName(employee.getUserName());
                    vFemsCsrType.setUpdateTime(new Date());
                    vFemsCsrType.setUpdateUserId(employee.getUserId());
                    vFemsCsrType.setUpdateUserName(employee.getUserName());
                } else {
                    vFemsCsrType.setUpdateTime(new Date());
                    vFemsCsrType.setUpdateUserId(employee.getUserId());
                    vFemsCsrType.setUpdateUserName(employee.getUserName());
                }
                vFemsCsrTypeMapper.insertSelective(vFemsCsrType);
            }
        }
        if (!BlankUtil.isBlank(vFemsCsrListVo.getVFemsCsrDetailList())) {
            for (VFemsCsrDetailList vFemsCsrDetailList : vFemsCsrListVo.getVFemsCsrDetailList()) {
                if (BlankUtil.isBlank(vFemsCsrDetailList.getId())) {
                    vFemsCsrDetailList.setCreateTime(new Date());
                    vFemsCsrDetailList.setCreateUserId(employee.getUserId());
                    vFemsCsrDetailList.setCreateUserName(employee.getUserName());
                    vFemsCsrDetailList.setUpdateTime(new Date());
                    vFemsCsrDetailList.setUpdateUserId(employee.getUserId());
                    vFemsCsrDetailList.setUpdateUserName(employee.getUserName());
                } else {
                    vFemsCsrDetailList.setUpdateTime(new Date());
                    vFemsCsrDetailList.setUpdateUserId(employee.getUserId());
                    vFemsCsrDetailList.setUpdateUserName(employee.getUserName());
                }
                i = vFemsCsrDetailListMapper.insertSelective(vFemsCsrDetailList);
            }
        }
        return ResultInfo.ok(i);
    }


    @GetMapping("/getNodeList")
    @ApiOperation(value = "获取节点配置列表")
    public ResultInfo getNodeList(VFemsCsrNode vFemsCsrNode, Query query, String userId, Employee employee){
        IPage<VFemsCsrNode> page = new Page<VFemsCsrNode>(query.getCurrent(),query.getPageSize());
        QueryWrapper<VFemsCsrNode> queryWrapper = new QueryWrapper<>();
        if (!BlankUtil.isBlank(vFemsCsrNode.getNodeName())){
            queryWrapper.like("node_name",vFemsCsrNode.getNodeName());
        }
        if (!BlankUtil.isBlank(vFemsCsrNode.getPlace())) {
            queryWrapper.like("place",vFemsCsrNode.getPlace());
        }
        IPage<VFemsCsrNode> mapIPage = vFemsCsrNodeMapper.selectPage(page, queryWrapper);
        return ResultInfo.ok(mapIPage);
    }

    @Login
    @PostMapping("/saveOrUpdateNode")
    @ApiOperation(value = "增加/更新节点配置")
    public ResultInfo saveOrUpdateNode(@RequestBody List<VFemsCsrNode> vFemsCsrNodeList, Employee employee){
        if (BlankUtil.isBlank(vFemsCsrNodeList)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        int i =0;
        logger.info("vFemsCsrNodeList == "+vFemsCsrNodeList.toString());
        for (VFemsCsrNode vFemsCsrNode : vFemsCsrNodeList){
            if (BlankUtil.isBlank(vFemsCsrNode.getNodeName()) || BlankUtil.isBlank(vFemsCsrNode.getNodeCode())){
                return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
            }
            if (BlankUtil.isBlank(vFemsCsrNode.getId())){
                vFemsCsrNode.setCreateTime(new Date());
                vFemsCsrNode.setCreateUserId(employee.getUserId());
                vFemsCsrNode.setCreateUserName(employee.getUserName());
                vFemsCsrNode.setUpdateTime(new Date());
                vFemsCsrNode.setUpdateUserId(employee.getUserId());
                vFemsCsrNode.setUpdateUserName(employee.getUserName());
                i += vFemsCsrNodeMapper.insertSelective(vFemsCsrNode);
            }else {
                vFemsCsrNode.setUpdateTime(new Date());
                vFemsCsrNode.setUpdateUserId(employee.getUserId());
                vFemsCsrNode.setUpdateUserName(employee.getUserName());
                i += vFemsCsrNodeMapper.updateByPrimaryKeySelective(vFemsCsrNode);
            }
        }
        return ResultInfo.ok(i);

    }

    @GetMapping("/deleteNode")
    @ApiOperation(value = "删除节点配置")
    public ResultInfo deleteNode(VFemsCsrNode vFemsCsrNode){
        if (BlankUtil.isBlank(vFemsCsrNode)){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        if (BlankUtil.isBlank(vFemsCsrNode.getId())){
            return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        logger.info("vFemsCsrListCondition == "+vFemsCsrNode.toString());
        int i = vFemsCsrNodeMapper.deleteByPrimaryKey(vFemsCsrNode.getId());
        return ResultInfo.ok(i);
    }
    //力矩清单相关接口 end========================================================================================================================================

    public static void main(String[] args) {

    }

}
