package com.small.admin.config.security.jwt;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.small.redis.utils.RedisUtil;
import com.small.admin.common.utils.SecurityUtil;
import com.small.admin.config.properties.TokenProperties;
import com.small.admin.entity.vo.TokenUser;
import com.small.common.constant.SecurityConstant;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.utils.JWTUtils;
import com.small.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Luhanlin
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter   {

    private TokenProperties tokenProperties;


    private SecurityUtil securityUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, TokenProperties tokenProperties, SecurityUtil securityUtil) {
        super(authenticationManager);
        this.tokenProperties = tokenProperties;
        this.securityUtil = securityUtil;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(SecurityConstant.HEADER);
        if(StrUtil.isBlank(token)){
            token = request.getParameter(SecurityConstant.HEADER);
        }
        Boolean notValid = StrUtil.isBlank(token) || !token.startsWith(SecurityConstant.TOKEN_SPLIT);
        if (notValid) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            log.error("校验 token 异常, msg: {}",e.getMessage());
            ResponseUtil.out(response, GenericResultCodeEnum.TOKENERROR,"登录已失效，请重新登录");
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token, HttpServletResponse response) {
//        log.info("login token: {}", token);
        // 用户名
        String username = null;
        // 权限
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 验证token的有效性
        if (JWTUtils.isExpired(token)) {
            ResponseUtil.out(response, GenericResultCodeEnum.TOKENERROR,"登录已失效，请重新登录");
        }

        username = JWTUtils.getUserInfo(token);

        if(tokenProperties.getRedis()){
            // redis
            String tokenUser = RedisUtil.get(SecurityConstant.TOKEN_PRE + token);
            if(StrUtil.isBlank(tokenUser)){
                ResponseUtil.out(response, GenericResultCodeEnum.TOKENERROR,"登录已失效，请重新登录");
                return null;
            }
            TokenUser user = JSONUtil.toBean(tokenUser, TokenUser.class);

            if(tokenProperties.getStorePerms()){
                // 缓存了权限
                for(String ga : user.getPermissions()){
                    authorities.add(new SimpleGrantedAuthority(ga));
                }
            }else{
                // 未缓存 读取权限数据
                authorities = securityUtil.getCurrUserPerms(username);
            }
            if(!user.getSaveLogin()){
                // 若未保存登录状态重新设置失效时间
                RedisUtil.setEx(SecurityConstant.USER_TOKEN + username, token, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
                RedisUtil.setEx(SecurityConstant.TOKEN_PRE + token, tokenUser, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
            }
        }

//        log.info("authorities: {}", authorities);

        if(StrUtil.isNotBlank(username)) {
            //踩坑提醒 此处password不能为null
            User principal = new User(username, "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        }
        return null;
    }
}

