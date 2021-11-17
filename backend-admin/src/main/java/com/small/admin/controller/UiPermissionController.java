package com.small.admin.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.small.redis.utils.RedisUtil;
import com.small.admin.common.constant.CommonConstant;
import com.small.admin.common.enums.AdminResultCodeEnum;
import com.small.admin.common.utils.SecurityUtil;
import com.small.admin.config.security.permission.MySecurityMetadataSource;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.po.RolePermission;
import com.small.admin.entity.po.User;
import com.small.admin.entity.vo.MenuVo;
import com.small.admin.entity.vo.PermissionPageVo;
import com.small.admin.service.PermissionService;
import com.small.admin.service.RolePermissionService;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.exception.ResultException;
import com.small.common.result.ResultInfo;
import com.small.common.utils.BlankUtil;
import com.small.common.utils.ConverUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-09-29 16:40]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@RestController
@Api(description = "UI权限相关接口")
@RequestMapping("/uiPermissions")
public class UiPermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private MySecurityMetadataSource mySecurityMetadataSource;

    @GetMapping("/getMenuList")
    @ApiOperation(value = "获取用户页面菜单数据")
    public ResultInfo getAllMenuList(){

        List<MenuVo> menuList = new ArrayList<>();
        // 读取缓存
        User u = securityUtil.getCurrUser();
        String key = "permission::userMenuList:" + u.getId();
        String cacheMenu = RedisUtil.get(key);
        if(StrUtil.isNotBlank(cacheMenu)){
            // 获取到菜单直接返回json串
            JSONArray array = JSONUtil.parseArray(cacheMenu);
            menuList = JSONUtil.toList(array, MenuVo.class);
            return ResultInfo.ok(menuList);
        }

        // 用户所有权限 已排序去重
        List<Permission> list = permissionService.findByUserId(u.getId());

        // 以及页面菜单
        List<MenuVo> firstMenuList = new ArrayList<>();
        // 二级页面菜单
        List<MenuVo> secondMenuList = new ArrayList<>();
        // 二级页面拥有的api权限
        List<MenuVo> apiPermissions = new ArrayList<>();

        for(Permission p : list){
            // 筛选0级页面
            if(CommonConstant.PERMISSION_NAV.equals(p.getType())&&CommonConstant.LEVEL_ZERO.equals(p.getLevel())){
                menuList.add(ConverUtils.copyToCls(p, MenuVo.class));
                continue;
            }

            // 筛选一级页面
            if(CommonConstant.PERMISSION_PAGE.equals(p.getType())&&CommonConstant.LEVEL_ONE.equals(p.getLevel())){
                firstMenuList.add(ConverUtils.copyToCls(p, MenuVo.class));
                continue;
            }

            // 筛选二级页面
            if(CommonConstant.PERMISSION_PAGE.equals(p.getType())&&CommonConstant.LEVEL_TWO.equals(p.getLevel())){
                secondMenuList.add(ConverUtils.copyToCls(p, MenuVo.class));
            }

            // 筛选二级页面拥有的按钮权限
            if(CommonConstant.PERMISSION_OPERATION.equals(p.getType())&&CommonConstant.LEVEL_THREE.equals(p.getLevel())){
                apiPermissions.add(ConverUtils.copyToCls(p, MenuVo.class));
            }

        }

        // 匹配二级页面拥有权限
        secondMenuList.forEach(menuVo -> {
            List<MenuVo> apiPerms = new ArrayList<>();
            apiPermissions.stream()
                    .filter(p -> menuVo.getId().equals(p.getParentId()))
                    .forEach(apiPerms::add);

            menuVo.setChildren(apiPerms);
        });

        // 匹配一级页面拥有二级页面
        firstMenuList.forEach(menuVo -> {
            List<MenuVo> secondPages = new ArrayList<>();
            secondMenuList.stream()
                    .filter(page -> menuVo.getId().equals(page.getParentId()))
                    .forEach(secondPages::add);

            menuVo.setChildren(secondPages);
        });

        // 匹配0级页面拥有一级页面
        menuList.forEach(menuVo -> {
            List<MenuVo> firstPages = new ArrayList<>();
            firstMenuList.stream()
                    .filter(page -> menuVo.getId().equals(page.getParentId()))
                    .forEach(firstPages::add);
            menuVo.setChildren(firstPages);
        });

        // 缓存
        RedisUtil.set(key, JSONUtil.toJsonStr(menuList));
        return ResultInfo.ok(menuList);
    }

    @GetMapping("/getAllList")
    @ApiOperation(value = "获取权限菜单树")
    public ResultInfo getAllList(){

        // 0级
        List<Permission> list0 = permissionService.findByLevelOrderBySortOrder(CommonConstant.LEVEL_ZERO);
        for(Permission p0 : list0){
            // 一级
            List<Permission> list1 = permissionService.findByParentIdOrderBySortOrder(p0.getId());
            p0.setChildren(list1);

            // 二级
            for(Permission p1 : list1){
                List<Permission> children1 = permissionService.findByParentIdOrderBySortOrder(p1.getId());
                p1.setChildren(children1);
                // 三级
                for(Permission p2 : children1){
                    List<Permission> children2 = permissionService.findByParentIdOrderBySortOrder(p2.getId());
                    p2.setChildren(children2);
                }
            }
        }
        return ResultInfo.ok(list0);
    }

    @PostMapping
    @ApiOperation(value = "添加权限")
    public ResultInfo addPermission(@RequestBody Permission permission){
        // 1. 判断权限名是否已经存在
        if (BlankUtil.isBlank(permission) || BlankUtil.isBlank(permission.getName())) {
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }

        // 不能保存权限名相同的权限
        Permission perm = permissionService.findByName(permission.getName());
        if (!BlankUtil.isBlank(perm)) {
            throw new ResultException(AdminResultCodeEnum.PERMISSION_NAME_EXIST);
        }

        // 2. 保存权限
        permissionService.save(permission);

        // 3. 从新加载权限
        mySecurityMetadataSource.loadResourceDefine();

        // TODO 4. 删除相关联缓存

        return ResultInfo.ok(permission);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据权限ID获取权限信息接口")
    public ResultInfo getPermissionInfo(@PathVariable(name = "id") String id){

        if (BlankUtil.isBlank(id)) {
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }

        Permission permission = permissionService.getPermissionInfo(id);

        if (BlankUtil.isBlank(permission)) {
            throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
        }

        return ResultInfo.ok(permission);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改权限")
    public ResultInfo updatePermission(@PathVariable(name = "id") String id, @RequestBody Permission permission){

        if (BlankUtil.isBlank(id) || BlankUtil.isBlank(permission)) {
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }

        // 如果需要修改权限名称,不能修改保存权限名在数据库已存在的权限
        if (!BlankUtil.isBlank(permission.getName())){
            Permission perm = permissionService.findByName(permission.getName());
            if (!BlankUtil.isBlank(perm) && !id.equals(perm.getId())) {
                // 查出来的权限数据如果ID不是修改的数据ID，则被修改的数据的名称在数据库已存在不能修改
                throw new ResultException(AdminResultCodeEnum.PERMISSION_NAME_EXIST);
            }
        }

        permissionService.updateById(permission);

        log.info("id [{}] 的权限信息已被修改.", id);

        // 从新加载权限
        mySecurityMetadataSource.loadResourceDefine();

        // TODO 4. 删除相关联缓存

        return ResultInfo.ok(permission);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据权限ID删除权限接口")
    public ResultInfo deletePermission(@PathVariable(name = "id") String id){

        if (BlankUtil.isBlank(id)) {
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }

        // 查看需要被删除的权限是否有被使用
        List<RolePermission> rpList = rolePermissionService.getByPermissionId(id);

        if (!BlankUtil.isBlank(rpList)) {
            throw new ResultException(AdminResultCodeEnum.PERMISSION_IS_USED);
        }

        permissionService.removeById(id);

        log.info("id [{}] 的权限信息已被删除.", id);

        // 从新加载权限
        mySecurityMetadataSource.loadResourceDefine();

        // TODO 4. 删除相关联缓存

        return ResultInfo.ok();
    }

    @GetMapping
    @ApiOperation(value = "根据条件获取UI权限接口")
    public ResultInfo getAllPermissions(@ModelAttribute PermissionPageVo page){

        // 如果不进行分页则返回所有数据
        if (!page.isPage()) {
            List<Permission> permissions = permissionService.getAllPermissions();

            if (BlankUtil.isBlank(permissions)) {
                throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
            }

            return ResultInfo.ok(permissions);
        }

        IPage<Permission> data = permissionService.getPagePermissionsByCondition(page);

        return ResultInfo.ok(data);
    }

}
