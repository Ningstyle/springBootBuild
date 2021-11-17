package com.small.admin.config.security;

import cn.hutool.core.util.StrUtil;
import com.small.redis.utils.RedisUtil;
import com.small.admin.entity.dto.UserDto;
import com.small.admin.exception.LoginFailLimitException;
import com.small.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-08-28 08:48]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String flagKey = "loginFailFlag:"+username;
        String value = RedisUtil.get(flagKey);
        Long timeRest = RedisUtil.getExpire(flagKey, TimeUnit.MINUTES);
        if(StrUtil.isNotBlank(value)){
            //超过限制次数
            throw new LoginFailLimitException("登录错误次数超过限制，请"+timeRest+"分钟后再试");
        }
        log.info("username=" + username);

        UserDto user = userService.findByUsername(username);
        return new SecurityUserDetails(user);
    }
}
