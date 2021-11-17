package com.small.business.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.small.business.common.annotation.Login;
import com.small.business.common.enums.BusinessCodeEnum;
import com.small.business.dao.VFemsCsrMenuMapper;
import com.small.business.dao.VFemsCsrPjmemberMapper;
import com.small.business.dao.VFemsCsrUserMapper;
import com.small.business.entity.po.*;
import com.small.business.entity.vo.Employee;
import com.small.business.entity.vo.Query;
import com.small.business.entity.vo.VFemsCsrMenuVo;
import com.small.business.service.CommonService;
import com.small.business.service.VFemsCsrPjmemberService;
import com.small.business.service.VFemsCsrProjectextService;
import com.small.business.service.VFemsCsrUnitprofile2Service;
import com.small.business.utils.BlankUtil;
import com.small.business.utils.CopyUtils;
import com.small.business.utils.HttpUtil;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.result.ResultInfo;
import com.small.common.utils.JWTUtils;
import com.small.redis.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private VFemsCsrProjectextService vFemsCsrProjectextService;

    @Autowired
    private VFemsCsrPjmemberService vFemsCsrPjmemberService;

    @Autowired
    private VFemsCsrUnitprofile2Service vFemsCsrUnitprofile2Service;

    private static final String authUsername = "WLWJC";
    private static final String authPass = "WLWJC@CA20201106";

    @Value("${userPath}")
    private String userPath;
    @Autowired
    private VFemsCsrUserMapper vFemsCsrUserMapper;
    @Autowired
    private VFemsCsrMenuMapper vFemsCsrMenuMapper;

    //用户相关接口 start========================================================================================================================================
    @PostMapping("/authenticate")
    @ApiOperation(value = "2.1用户认证")///rest/authentication/authenticate
    public ResultInfo authenticate(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("userName",userBaseBean.getUserName());
        map.put("password",userBaseBean.getPassword());
        String json = HttpUtil.httpPostWithJSON(userPath+"/rest/authentication/authenticate",map,authUsername,authPass);
        JSONObject jsonObject = new JSONObject();
        if (!BlankUtil.isBlank(json)){
            jsonObject = JSON.parseObject(json);
            if ("000".equals(jsonObject.get("code"))){
                Employee employee = JSONObject.parseObject(jsonObject.get("employee").toString(), Employee.class);
                String token = JWTUtils.generateToken(employee.getUserId(),1440 , JSONUtil.toJsonStr(employee));
                logger.info("token==="+token);
                RedisUtil.setEx("authenticate_"+employee.getUserId(),token,1, TimeUnit.DAYS);
                RedisUtil.setEx(token,JSONUtil.toJsonStr(employee),1, TimeUnit.DAYS);
//                RedisUtil.setEx(employee.getUserId(),JSONUtil.toJsonStr(employee),1, TimeUnit.DAYS);
                logger.info(" 1111 "+RedisUtil.get(token));
                return ResultInfo.ok(jsonObject.get("employee"),token);
            }else {
                return new ResultInfo(BusinessCodeEnum.PASSWORD_ERROR);
            }
        }else {
            return null;
        }
    }

    @Login
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "根据当前人登录的id获取角色信息")///rest/authentication/authenticate
    public ResultInfo getUserInfo(Employee employee){
        Map map = new HashMap();
        map.put("user_id",employee.getUserId());
        List<VFemsCsrUser> vFemsCsrUser = vFemsCsrUserMapper.selectByMap(map);
        if (BlankUtil.isBlank(vFemsCsrUser)){
            return null;
        }else {
            return ResultInfo.ok(vFemsCsrUser.get(0));
        }
    }

    @Login
    @GetMapping("/getMenuList")
    @ApiOperation(value = "根据当前人角色获取菜单")///rest/authentication/authenticate
    public ResultInfo getMenuList(Employee employee){
        Map map = new HashMap();
        map.put("user_id",employee.getUserId());
        List<VFemsCsrUser> vFemsCsrUser = vFemsCsrUserMapper.selectByMap(map);
        if (BlankUtil.isBlank(vFemsCsrUser)){
            return null;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_name",vFemsCsrUser.get(0).getRoleName());
        queryWrapper.eq("grade",1);
        queryWrapper.orderByAsc("orders");
        List<VFemsCsrMenu> vFemsCsrMenusList =  vFemsCsrMenuMapper.selectList(queryWrapper);
        List<VFemsCsrMenuVo> fatherList =  new ArrayList<>();
        for (VFemsCsrMenu vFemsCsrMenu : vFemsCsrMenusList){
            VFemsCsrMenuVo vFemsCsrMenuVo = new VFemsCsrMenuVo();
            CopyUtils.copyProperties(vFemsCsrMenu, vFemsCsrMenuVo);
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("role_name",vFemsCsrUser.get(0).getRoleName());
            queryWrapper1.eq("father_id",vFemsCsrMenu.getId());
            queryWrapper1.eq("grade",2);
            queryWrapper1.orderByAsc("orders");
            List<VFemsCsrMenu> childList =  vFemsCsrMenuMapper.selectList(queryWrapper1);
            List<VFemsCsrMenuVo> childs =  new ArrayList<>();
            for (VFemsCsrMenu menu : childList){
                VFemsCsrMenuVo vFemsCsrMenuVo1 = new VFemsCsrMenuVo();
                CopyUtils.copyProperties(menu, vFemsCsrMenuVo1);
                childs.add(vFemsCsrMenuVo1);
            }
            vFemsCsrMenuVo.setChildren(childs);
            fatherList.add(vFemsCsrMenuVo);
        }
        return ResultInfo.ok(fatherList);
    }
    //用户相关接口 start========================================================================================================================================












    @PostMapping("/changePassword")
    @ApiOperation(value = "2.2修改密码")///rest/authentication/authenticate
    public String changePassword(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("userId",userBaseBean.getUserId());
        map.put("oldpassword",userBaseBean.getOldpassword());
        map.put("password",userBaseBean.getPassword());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/authentication/changePassword",map,authUsername,authPass);
    }

  /*  @PostMapping("/authenticate")
    @ApiOperation(value = "2.3单点认证规范")///rest/authentication/authenticate
    public String authenticate1(UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("userName",userBaseBean.getUserName());
        map.put("password",userBaseBean.getPassword());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/authentication/authenticate",map,authUsername,authPass);
    }*/
    //认证相关接口 end========================================================================================================================================






    //3组织相关接口 start========================================================================================================================================
    @PostMapping("/getOrgByCode")
    @ApiOperation(value = "3.1查询指定组织信息")///rest/authentication/authenticate
    public String getOrgByCode(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("orgCode",userBaseBean.getOrgCode());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/organizationApi/getOrgByCode",map,authUsername,authPass);
    }

    @PostMapping("/getSubOrgByCode")
    @ApiOperation(value = "3.2查询指定组织及其下级组织信息")///rest/authentication/authenticate
    public String getSubOrgByCode(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("orgCode",userBaseBean.getOrgCode());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/organizationApi/getSubOrgByCode",map,authUsername,authPass);
    }

    @PostMapping("/getOrgAndEmployee")
    @ApiOperation(value = "3.3查询组织及其下人员信息")///rest/authentication/authenticate
    public String getOrgAndEmployee(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("orgCode",userBaseBean.getOrgCode());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/organizationApi/getOrgAndEmployee",map,authUsername,authPass);
    }

    @PostMapping("/getOrgInfos")
    @ApiOperation(value = "3.4查询全量组织信息列表")///rest/authentication/authenticate
    public String getOrgInfos(@RequestBody UserBaseBean userBaseBean){
        return HttpUtil.httpPostWithJSON(userPath+"/rest/organizationApi/getOrgInfos",null,authUsername,authPass);
    }

    @PostMapping("/getCostCenter")
    @ApiOperation(value = "3.5查询全量成本中心列表")///rest/authentication/authenticate
    public String getCostCenter(@RequestBody UserBaseBean userBaseBean){
        return HttpUtil.httpPostWithJSON(userPath+"/rest/organizationApi/getCostCenter",null,authUsername,authPass);
    }

    @PostMapping("/getInitOrg")
    @ApiOperation(value = "3.6获取初始组织架构树")///rest/authentication/authenticate
    public String getInitOrg(@RequestBody UserBaseBean userBaseBean){
        return HttpUtil.httpPostWithJSON(userPath+"/rest/organizationApi/getInitOrg",null,authUsername,authPass);
    }

    @PostMapping("/getSubDepartment")
    @ApiOperation(value = "3.7获取所有直接下级机构")///rest/authentication/authenticate
    public String getSubDepartment(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("pid",userBaseBean.getPid());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/organizationApi/getSubDepartment",map,authUsername,authPass);
    }

    @PostMapping("/getSubDepartmentAndEmployeeByPid")
    @ApiOperation(value = "3.8根据部门id查询下一级部门和当前部门的人")///rest/authentication/authenticate
    public String getSubDepartmentAndEmployeeByPid(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("pid",userBaseBean.getPid());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/organizationApi/getSubDepartmentAndEmployeeByPid",map,authUsername,authPass);
    }
    //3组织相关接口 end========================================================================================================================================







    //4人员相关接口 start========================================================================================================================================
    @PostMapping("/findEmployee")
    @ApiOperation(value = "4.1查询指定人员信息")///rest/authentication/authenticate
    public String findEmployee(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("param",userBaseBean.getParam());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/employeeApi/findEmployee",map,authUsername,authPass);
    }

    @PostMapping("/findDirectLeader")
    @ApiOperation(value = "4.2查询直管领导信息")///rest/authentication/authenticate
    public String findDirectLeader(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("userId",userBaseBean.getUserId());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/employeeApi/findDirectLeader",map,authUsername,authPass);
    }

    @PostMapping("/findBranchLeader")
    @ApiOperation(value = "4.3查询分管领导信息")///rest/authentication/authenticate
    public String findBranchLeader(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("param",userBaseBean.getParam());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/employeeApi/findBranchLeader",map,authUsername,authPass);
    }

  /*  @PostMapping("/findDirectLeader")
    @ApiOperation(value = "4.4查询指定部门下人员列表")///rest/authentication/authenticate
    public String findDirectLeader(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("userId",userBaseBean.getUserId());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/employeeApi/findDirectLeader",null,authUsername,authPass);
    }*/

    @PostMapping("/getEmployeesInPage")
    @ApiOperation(value = "4.5全量查询人员信息")///rest/authentication/authenticate
    public String getEmployeesInPage(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("pageNum",userBaseBean.getPageNum());
        map.put("pageSize",userBaseBean.getPageSize());
        map.put("phoneNumber",userBaseBean.getPhoneNumber());
        map.put("userId",userBaseBean.getUserId());
        map.put("userName",userBaseBean.getUserName());
        map.put("email",userBaseBean.getEmail());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/employeeApi/getEmployeesInPage",map,authUsername,authPass);
    }

    @PostMapping("/findPeople")
    @ApiOperation(value = "4.6根据选择工号获取员工信息")///rest/authentication/authenticate
    public String findPeople(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("userId",userBaseBean.getUserId());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/employeeApi/findPeople",map,authUsername,authPass);
    }
    //4人员相关接口 end========================================================================================================================================






    //5消息相关接口 start========================================================================================================================================
    @PostMapping("/smsSend")
    @ApiOperation(value = "5.1短信通知接口")///rest/authentication/authenticate
    public String send(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("subject",userBaseBean.getSubject());
        map.put("bodyText",userBaseBean.getBodyText());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/sms/send",map,authUsername,authPass);
    }

    @PostMapping("/messageSend")
    @ApiOperation(value = "5.2EIS通知发送接口")///rest/authentication/authenticate
    public String messageSend(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("title",userBaseBean.getTitle());
        map.put("type",userBaseBean.getType());
        map.put("msg_content",userBaseBean.getMsg_content());
        map.put("url",userBaseBean.getUrl());
        map.put("imgurl",userBaseBean.getImgurl());
        map.put("peoples",userBaseBean.getPeoples());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/message/send",map,authUsername,authPass);
    }

    @PostMapping("/emailSend")
    @ApiOperation(value = "5.3邮件通知接口")///rest/authentication/authenticate
    public String emailSend(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("sendTo",userBaseBean.getSendTo());
        map.put("sendCC",userBaseBean.getSendCC());
        map.put("subject",userBaseBean.getSubject());
        map.put("bodyText",userBaseBean.getBodyText());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/email/send",map,authUsername,authPass);
    }
    //5消息相关接口 end========================================================================================================================================




    //6公司节假日接口 start========================================================================================================================================
    @PostMapping("/period")
    @ApiOperation(value = "6.1根据日期期限获取公司节假日")///rest/authentication/authenticate
    public String period(@RequestBody UserBaseBean userBaseBean){
        Map map = new HashMap();
        map.put("from",userBaseBean.getFrom());
        map.put("to",userBaseBean.getTo());
        return HttpUtil.httpPostWithJSON(userPath+"/rest/calendar/period",map,authUsername,authPass);
    }
    //6公司节假日接口 end========================================================================================================================================

}
