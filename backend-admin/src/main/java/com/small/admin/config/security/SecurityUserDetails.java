package com.small.admin.config.security;

import cn.hutool.core.util.StrUtil;
import com.small.admin.common.constant.CommonConstant;
import com.small.admin.entity.dto.UserDto;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.po.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author luhanlin
 */
@Slf4j
public class SecurityUserDetails extends UserDto implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUserDetails(UserDto user) {

        if(user!=null) {
            this.setUserName(user.getUserName());
            this.setPassword(user.getPassword());
            this.setUserStatus(user.getUserStatus());
            this.setRoles(user.getRoles());
            this.setPermissions(user.getPermissions());
        }
    }

    /**
     * 添加用户拥有的权限和角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Permission> permissions = this.getPermissions();
//        log.info(permissions.toString());
        // 添加请求权限
        if(permissions!=null&&permissions.size()>0){
            for (Permission permission : permissions) {
                if(CommonConstant.PERMISSION_OPERATION.equals(permission.getType())
                        &&StrUtil.isNotBlank(permission.getId())
                        &&StrUtil.isNotBlank(permission.getPath())) {

                    authorityList.add(new SimpleGrantedAuthority(permission.getId()));
                }
            }
        }
        // 添加角色
        List<Role> roles = this.getRoles();
        if(roles!=null&&roles.size()>0){
            roles.forEach(item -> {
                if(StrUtil.isNotBlank(item.getRoleName())){
                    authorityList.add(new SimpleGrantedAuthority(item.getRoleName()));
                }
            });
        }
        return authorityList;
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
//        return !CommonConstant.USER_STATUS_LOCK.equals(this.getUserStatus().getValue());
        return !CommonConstant.USER_STATUS_LOCK.equals(this.getUserStatus());
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
//        return CommonConstant.USER_STATUS_NORMAL.equals(this.getUserStatus().getValue());
        return CommonConstant.USER_STATUS_NORMAL.equals(this.getUserStatus());
    }
}