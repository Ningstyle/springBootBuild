package com.small.admin.config.security.jwt;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.small.admin.common.utils.SecurityUtil;
import com.small.admin.entity.po.User;
import com.small.redis.utils.RedisUtil;
import com.small.admin.config.properties.TokenProperties;
import com.small.admin.entity.vo.TokenUser;
import com.small.common.constant.SecurityConstant;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.utils.JWTUtils;
import com.small.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 登录成功处理类
 * @author Exrickx
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //用户选择保存登录状态几天
        String saveLogin = request.getParameter(SecurityConstant.SAVE_LOGIN);
        Boolean saved = false;
        if(StrUtil.isNotBlank(saveLogin) && Boolean.valueOf(saveLogin)){
            saved = true;
            if(!tokenProperties.getRedis()){
                tokenProperties.setTokenExpireTime(tokenProperties.getSaveLoginTime() * 60 * 24);
            }
        }
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails)authentication.getPrincipal()).getAuthorities();
        List<String> list = new ArrayList<>();
        for(GrantedAuthority g : authorities){
            list.add(g.getAuthority());
        }
        // 登陆成功生成token
        String token;
        if(tokenProperties.getRedis()){
            // redis
            token = JWTUtils.generateToken(username, tokenProperties.getTokenExpireTime(), JSONUtil.toJsonStr(list));

            TokenUser user = new TokenUser(username, list, saved);
            // 不缓存权限
            if(!tokenProperties.getStorePerms()){
                user.setPermissions(null);
            }
            // 单设备登录 之前的token失效
            if(tokenProperties.getSdl()){
                String oldToken = RedisUtil.get(SecurityConstant.USER_TOKEN + username);
                if(StrUtil.isNotBlank(oldToken)){
                    RedisUtil.delete(SecurityConstant.TOKEN_PRE + oldToken);
                    RedisUtil.delete(oldToken);
                }
            }

            User currUser = securityUtil.getCurrUser();
            if(saved){
                RedisUtil.setEx(SecurityConstant.USER_TOKEN + username, token, tokenProperties.getSaveLoginTime(), TimeUnit.DAYS);
                RedisUtil.setEx(SecurityConstant.TOKEN_PRE + token, JSONUtil.toJsonStr(user), tokenProperties.getSaveLoginTime(), TimeUnit.DAYS);
                RedisUtil.setEx(token, JSONUtil.toJsonStr(currUser), tokenProperties.getSaveLoginTime(), TimeUnit.DAYS);
            }else{
                RedisUtil.setEx(SecurityConstant.USER_TOKEN + username, token, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
                RedisUtil.setEx(SecurityConstant.TOKEN_PRE + token, JSONUtil.toJsonStr(user), tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
                RedisUtil.setEx(token, JSONUtil.toJsonStr(currUser), tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
            }
        }else{
            // 不缓存权限
            if(!tokenProperties.getStorePerms()){
                list = null;
            }
            // jwt
            token = JWTUtils.generateToken(username, tokenProperties.getTokenExpireTime(), JSONUtil.toJsonStr(list));
        }

        ResponseUtil.out(response, GenericResultCodeEnum.SUCCESS,token);
    }
}
