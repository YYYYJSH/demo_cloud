package com.yjsh.config;

import com.yjsh.config.aspect.DefaultRateLimitServiceImpl;
import com.yjsh.config.aspect.RateLimitService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@Configuration
public class RateLimitBeanConfig {

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Bean
    @ConditionalOnMissingBean(RateLimitService.class)
    public RateLimitService rateLimitService() {
        DefaultRateLimitServiceImpl defaultRateLimitServiceImpl = new DefaultRateLimitServiceImpl();
        defaultRateLimitServiceImpl.setRedisTemplate(redisTemplate);
        return defaultRateLimitServiceImpl;
    }
}