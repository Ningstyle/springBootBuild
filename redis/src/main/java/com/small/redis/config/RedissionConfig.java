package com.small.redis.config;


import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redission配置类
 *
 * @author luhanlin
 * @date 2019/09/24 14:49
 */
@Slf4j
@Configuration
public class RedissionConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {
        RedissonClient redissonClient;

        Config config = new Config();
//        String url = "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort();'
        String url = "redis://10.12.9.219:6379";
        config.useSingleServer().setAddress(url)
//                .setPassword(redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase());

        try {
            redissonClient = Redisson.create(config);
            return redissonClient;
        } catch (Exception e) {
            log.error("RedissonClient init redis url:[{}], Exception:", url, e);
            return null;
        }
    }

}
