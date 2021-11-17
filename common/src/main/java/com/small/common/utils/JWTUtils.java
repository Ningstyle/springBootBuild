package com.small.common.utils;

import com.small.common.constant.SecurityConstant;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.exception.ResultException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * JWT token 生成工具类
 * </p>
 *
 * @author luhanlin
 */
@Slf4j
public class JWTUtils {

    /**
     * 生成token
     * @param userInfo      登录名
     * @param minutes       过期时间，分钟
     * @param authorities   权限
     * @return
     */
    public static String generateToken(String userInfo, Integer minutes, String authorities) {
        Date nowDate = new Date();
        String token = SecurityConstant.TOKEN_SPLIT + Jwts.builder()
                //主题 放入用户名
                .setSubject(userInfo)
                .setIssuedAt(nowDate)
                //自定义属性 放入用户拥有请求权限
                .claim(SecurityConstant.AUTHORITIES, authorities)
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + minutes * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                .compact();

        return token;
    }

    /**
     * 解析Claims
     *
     * @param token
     * @return
     */
    public static Claims getClaim(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                    .parseClaimsJws(token.replace(SecurityConstant.TOKEN_SPLIT, ""))
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(GenericResultCodeEnum.TOKENERROR);
        }
        return claims;
    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedTime(String token) {
        return getClaim(token).getIssuedAt();
    }

    /**
     * 获取UID
     */
    public static String getUserInfo(String token) {
        return getClaim(token).getSubject();
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpiration(String token) {
        return getClaim(token).getExpiration();
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public static boolean isExpired(String token) {
        try {
            final Date expiration = getExpiration(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

}

