package com.small.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Redis配置文件的参数
 *
 * @author luhanlin
 * @date 2019/09/14 17:35
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    private String host;
    private int port;
//    private String password;
    private int database;
}
