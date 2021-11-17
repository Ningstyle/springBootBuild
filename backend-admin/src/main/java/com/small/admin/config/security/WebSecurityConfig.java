package com.small.admin.config.security;

import com.small.admin.common.utils.SecurityUtil;
import com.small.admin.config.properties.IgnoredUrlsProperties;
import com.small.admin.config.properties.TokenProperties;
import com.small.admin.config.security.jwt.AuthenticationFailHandler;
import com.small.admin.config.security.jwt.AuthenticationSuccessHandler;
import com.small.admin.config.security.jwt.JWTAuthenticationFilter;
import com.small.admin.config.security.jwt.RestAccessDeniedHandler;
import com.small.admin.config.security.permission.MyFilterSecurityInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsUtils;
/**
 * <类详细描述>
 *      Security 核心配置类
 *      开启控制权限至Controller
 * @author luhanlin
 * @version [V_1.0.0, 2019-08-28 08:48]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailHandler failHandler;

    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

//    @Autowired
//    private ImageValidateFilter imageValidateFilter;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();

        // 除配置文件忽略路径其它所有请求都需经过认证和授权
        for(String url:ignoredUrlsProperties.getUrls()){
            registry.antMatchers(url).permitAll();
        }

        registry.and()
                // HTTP Basic
//                .httpBasic()
                // 表单登录方式
                .formLogin()
                .loginPage("/admin/common/needLogin")
                // 登录请求url
                .loginProcessingUrl("/login")
                .permitAll()
                // 成功处理类
                .successHandler(successHandler)
                // 失败
                .failureHandler(failHandler)
                .and()
                // 允许网页iframe
                .headers().frameOptions().disable()
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 任何请求
                .anyRequest()
                // 需要身份认证
                .authenticated()
                .and()
                .cors().and()
                // 关闭跨站请求防护
                .csrf().disable()
                // 前后端分离采用JWT 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 自定义权限拒绝处理类
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                // 添加自定义权限过滤器
                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                // 图形验证码过滤器
//                .addFilterBefore(imageValidateFilter, UsernamePasswordAuthenticationFilter.class)
                // 添加JWT过滤器 除已配置的其它请求都需经过此过滤器
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), tokenProperties, securityUtil));
    }
}
