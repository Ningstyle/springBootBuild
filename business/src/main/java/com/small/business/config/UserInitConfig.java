package com.small.business.config;

import com.small.common.constant.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-09-12 14:44]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Log4j2
@Configuration
public class UserInitConfig {

    @Bean
    public Object initWorkerId(@Value("${system.type}") String sysType) throws Exception {
        ResultCode.SysType = sysType;
        log.info("进入SysType：========================"+ ResultCode.SysType);

        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();

        ResultCode.SysIp = hostAddress;
        log.info("进入hostAddress：========================"+ hostAddress);
        return ResultCode.SysType;
    }
}
