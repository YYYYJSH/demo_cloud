package com.yjsh.config;


import com.yjsh.config.aspect.RateLimitInterceptor;
import com.yjsh.config.aspect.RateLimitService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private RateLimitService rateLimitService;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        RateLimitInterceptor rateLimitInterceptor = new RateLimitInterceptor();
        rateLimitInterceptor.setRateLimitService(rateLimitService);
        registry.addInterceptor(rateLimitInterceptor);
    }
}
