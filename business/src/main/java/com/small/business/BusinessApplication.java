package com.small.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = {"com.small"})
@SpringBootApplication
public class BusinessApplication extends SpringBootServletInitializer {

    private static Logger logger = LogManager.getLogger(BusinessApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
        logger.info("BUSSINESS服务启动完成....");
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
