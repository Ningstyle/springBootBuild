package com.small.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.small.admin.common.enums.AdminResultCodeEnum;
import com.small.admin.common.utils.SecurityUtil;
import com.small.admin.entity.dto.UserDto;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.po.Role;
import com.small.admin.entity.po.User;
import com.small.admin.entity.po.UserRole;
import com.small.admin.entity.vo.PasswordVo;
import com.small.admin.entity.vo.UserPageVo;
import com.small.admin.service.PermissionService;
import com.small.admin.service.RoleService;
import com.small.admin.service.UserRoleService;
import com.small.admin.service.UserService;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.exception.ResultException;
import com.small.common.result.ResultInfo;
import com.small.common.utils.BlankUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(description = "用户接口")
@RequestMapping("/users")
@CacheConfig(cacheNames = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private SecurityUtil securityUtil;

    @GetMapping("/getAllInfo")
    @ApiOperation(value = "获取全部用户的全部信息")
    public ResultInfo getAllUserInfo(){
        List<UserDto> users = userService.findAllUser();
        return ResultInfo.ok(users);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "获取指定用户的全部信息")
    public ResultInfo getUserInfoByUserId(@PathVariable(name = "userId") String userId){
        UserDto userDto = userService.findByUserId(userId);
        if(BlankUtil.isBlank(userDto)){
            throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
        }
        return ResultInfo.ok(userDto);
    }

    @GetMapping("/{userId}/roles")
    @ApiOperation(value = "获取指定用户的角色")
    public ResultInfo getRolesByUserId(@PathVariable(name = "userId") String userId){
        List<Role> roles = userService.findRolesByUserId(userId);
        if(BlankUtil.isBlank(roles)){
            throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
        }
        return ResultInfo.ok(roles);
    }

    @PostMapping
    @ApiOperation(value = "添加用户")
    public ResultInfo register(@RequestBody User user){

        if(BlankUtil.isBlank(user) || StrUtil.isBlank(user.getUserName())
                || StrUtil.isBlank(user.getPassword())){
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        // 验证用户是否被注册
        User existUser = userService.findByUsername(user.getUserName());
        if(!BlankUtil.isBlank(existUser)){
            throw new ResultException(AdminResultCodeEnum.REGISTERED);
        }
        //2. 判断手机号是否已经存在
        if(BlankUtil.isNotBlank(user.getMobile())){
            User userExists = userService.findByMobile(user.getMobile());
            if(BlankUtil.isNotBlank(userExists)){
                throw new ResultException(AdminResultCodeEnum.MOBILE_EXIST);
            }
        }
        //3. 判断邮箱是否已经存在
        if(BlankUtil.isNotBlank(user.getEmail())){
            User userExists = userService.findByEmail(user.getEmail());
            if(BlankUtil.isNotBlank(userExists)){
                throw new ResultException(AdminResultCodeEnum.EMAIL_EXIST);
            }
        }

        String encryptPass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptPass);
        boolean update = userService.save(user);
        if(!update){
            throw new ResultException(AdminResultCodeEnum.USER_UPDATE_ERROR);
        }
//        // 新建用户赋予默认角色
        List<Role> roleList = roleService.findByDefaultRole(true);
        if(roleList!=null&&roleList.size()>0){
            for(Role role : roleList){
                UserRole ur = new UserRole();
                ur.setUserId(user.getId());
                ur.setRoleId(role.getId());
                userRoleService.save(ur);
            }
        }
        log.info("userName [{}] 的用户添加成功.", user.getUserName());
        user.setPassword(null);
        return ResultInfo.ok(user);
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "修改用户基本信息")
    public ResultInfo updateUserInfoByUserId(@PathVariable(name = "userId") String userId, @RequestBody User user){
        if(BlankUtil.isBlank(userId) || BlankUtil.isBlank(user) ||  BlankUtil.isBlank(user.getUserName()) ||
                BlankUtil.isBlank(user.getMobile()) ||  BlankUtil.isBlank(user.getEmail()) ){
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        //1. 判断修改的用户名是否已经存在
        if(BlankUtil.isNotBlank(user.getUserName())){
            User userExists = userService.findByUsername(user.getUserName());
            if(BlankUtil.isNotBlank(userExists) && !userExists.getId().equals(userId)){
                throw new ResultException(AdminResultCodeEnum.USER_NAME_EXIST);
            }
        }
        //2. 判断手机号是否已经存在
        if(BlankUtil.isNotBlank(user.getMobile())){
            User userExists = userService.findByMobile(user.getMobile());
            if(BlankUtil.isNotBlank(userExists) && !userExists.getId().equals(userId)){
                throw new ResultException(AdminResultCodeEnum.MOBILE_EXIST);
            }
        }
        //3. 判断邮箱是否已经存在
        if(BlankUtil.isNotBlank(user.getEmail())){
            User userExists = userService.findByEmail(user.getEmail());
            if(BlankUtil.isNotBlank(userExists) && !userExists.getId().equals(userId)){
                throw new ResultException(AdminResultCodeEnum.EMAIL_EXIST);
            }
        }
        user.setId(userId);
        boolean update = userService.updateById(user);
        if(!update){
            throw new ResultException(AdminResultCodeEnum.USER_UPDATE_ERROR);
        }
        user.setPassword(null);
        log.info("userId [{}] 的用户基本信息已被修改.", userId);
        return ResultInfo.ok();
    }

    @PutMapping("/{userId}/roles")
    @ApiOperation(value = "修改用户角色")
    public ResultInfo updateRolesByUserId(@PathVariable(name = "userId") String userId, @RequestBody String[] roles){
        if (BlankUtil.isBlank(userId) || BlankUtil.isBlank(roles)) {
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        //删除该用户角色
        boolean delete = userRoleService.deleteByUserId(userId);
        if(!delete){
            //TODO
            throw new ResultException(GenericResultCodeEnum.DBDeleteFailure);
        }
        log.info("userId [{}] 的用户角色已被删除.", userId);
        if(roles!=null&&roles.length>0){
            //添加新角色
            for(String roleId : roles){
                UserRole ur = new UserRole();
                ur.setRoleId(roleId);
                ur.setUserId(userId);
                userRoleService.save(ur);
            }
        }
        log.info("userId [{}] 的用户角色已被修改.", userId);
        return  ResultInfo.ok();
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "删除用户")
    public ResultInfo deleteUserByUserId(@PathVariable(name = "userId") String userId){
        //删除用户
        boolean deleteflage = userService.removeById(userId);
        if(!deleteflage){
            throw new ResultException(GenericResultCodeEnum.DBDeleteFailure);
        }
        //删除用户角色关联关系
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("user_id", userId);
        userRoleService.removeByMap(columnMap);
        log.info("userId [{}] 用户已被删除.", userId);
        return  ResultInfo.ok();
    }

    @GetMapping
    @ApiOperation(value = "根据条件获取符合条件的用户")
    public ResultInfo getAllUsers(@ModelAttribute UserPageVo page){
        // 如果不进行分页则返回所有数据
        if (!page.isPage()) {
            List<User> usersByCondition = userService.getUsersByCondition(page);
            if (BlankUtil.isBlank(usersByCondition)) {
                throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
            }
            return ResultInfo.ok(usersByCondition);
        }
        IPage<User> users = userService.getPageUsersByCondition(page);
        return ResultInfo.ok(users);
    }

    @PostMapping("/{userId}/password")
    @ApiOperation(value = "重置密码")
    public ResultInfo updatePasswordByUserId(@PathVariable(name = "userId") String userId, @RequestBody PasswordVo passwordVo){
        if (BlankUtil.isBlank(userId) || BlankUtil.isBlank(passwordVo) || BlankUtil.isBlank(passwordVo.getOldpassword()) || BlankUtil.isBlank(passwordVo.getNewpassword())) {
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        //查询用户的基本信息
        User u =  userService.getById(userId);
        if(!new BCryptPasswordEncoder().matches(passwordVo.getOldpassword(), u.getPassword())){
            throw new ResultException(AdminResultCodeEnum.OLDPASSWORD_ERROR);
        }
        u.setPassword(new BCryptPasswordEncoder().encode(passwordVo.getNewpassword()));
        boolean update = userService.updateById(u);
        if(!update){
            throw new ResultException(AdminResultCodeEnum.USER_UPDATE_ERROR);
        }
        log.info("userId [{}] 用户密码已修改", userId);
        u.setPassword(null);
        return  ResultInfo.ok();
    }

    @PostMapping("/{userId}/disable")
    @ApiOperation(value = "后台禁用用户")
    public ResultInfo disable(@PathVariable(name = "userId") String userId){
        User user = userService.findByUserId(userId);
        if(BlankUtil.isBlank(user)){
            throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
        }
        user.setUserStatus(-1);
        boolean update = userService.updateById(user);
        if(!update){
            throw new ResultException(AdminResultCodeEnum.USER_UPDATE_ERROR);
        }
        log.info("userId [{}] 用户已被禁用", userId);
        return  ResultInfo.ok();
    }

    @PostMapping("/{userId}/enable")
    @ApiOperation(value = "后台启用用户")
    public ResultInfo enable(@PathVariable(name = "userId") String userId){
        User user = userService.findByUserId(userId);
        if(BlankUtil.isBlank(user)){
            throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
        }
        user.setUserStatus(0);
        boolean update = userService.updateById(user);
        if(!update){
            throw new ResultException(AdminResultCodeEnum.USER_UPDATE_ERROR);
        }
        log.info("userId [{}] 用户已被启用", userId);
        return  ResultInfo.ok();
    }

    @GetMapping("/{userId}/uiPermissions")
    @ApiOperation(value = "获取指定用户的权限")
    public ResultInfo getPermissionsByUserId(@PathVariable(name = "userId") String userId){
        List<Permission> permissions = permissionService.findByUserId(userId);
        if(BlankUtil.isBlank(permissions)){
            throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
        }
        return ResultInfo.ok(permissions);
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取当前登录用户接口")
    public ResultInfo getUserInfo(){
        User user = securityUtil.getCurrUser();
        if(BlankUtil.isBlank(user)){
            throw new ResultException(GenericResultCodeEnum.DBSelectFailure);
        }
        user.setPassword(null);
        return ResultInfo.ok(user);
    }


}
