package com.small.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.small.admin.entity.dto.UserDto;
import com.small.admin.entity.po.Role;
import com.small.admin.entity.po.User;
import com.small.admin.entity.vo.UserPageVo;

import java.util.List;

/**
 * 用户接口
 */
public interface UserService extends IService<User> {

    /**
     * 通过用户名获取用户全部信息
     * @param username
     * @return
     */
    UserDto findByUsername(String username);

    /**
     * 通过手机获取用户
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 通过邮件和状态获取用户
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 分页查询符合过滤条件的所有用户
     * @param page
     * @return
     */
    IPage<User> getPageUsersByCondition(UserPageVo page);

    /**
     * 分页查询符合过滤条件的所有用户
     * @param page
     * @return
     */
    List<User> getUsersByCondition(UserPageVo page);

    /**
     * 获取全部用户
     * @return
     */
    List<UserDto> findAllUser();

    /**
     * 通过userId获取用户
     * @param userId
     * @return
     */
    UserDto findByUserId(String userId);

    /**
     * 获取指定用户的角色
     */
    List<Role> findRolesByUserId(String userId);

}
