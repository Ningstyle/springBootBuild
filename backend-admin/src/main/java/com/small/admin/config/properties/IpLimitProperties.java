package com.small.admin.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author luhanlin
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "small.iplimit")
public class IpLimitProperties {

    /**
     * 是否开启全局限流
     */
    private Boolean enable = false;

    /**
     * 限制请求个数
     */
    private Integer limit = 100;

    /**
     * 每单位时间内（毫秒）
     */
    private Integer timeout = 1000;
}
