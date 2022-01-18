package com.yjsh.config.aspect;

import com.yjsh.config.RateLimit;
import com.yjsh.config.aspect.RateLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DefaultRateLimitServiceImpl implements RateLimitService {

    private RedisTemplate<String, Integer> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public Boolean limit(String ip, String uri, RateLimit rateLimit) {
        String key = "rate:" +"userId"  + ip + ":" + uri;
        // 缓存中存在key，在限定访问周期内已经调用过当前接口
        if (redisTemplate.hasKey(key)) {
            // 访问次数自增1
            redisTemplate.opsForValue().increment(key, 1);
            // 超出访问次数限制
            Object times = redisTemplate.opsForValue().get(key);
            if (Integer.valueOf((String) times) > rateLimit.number()) {
                return false;
            }
            // 未超出访问次数限制，不进行任何操作，返回true
        } else {
            // 第一次设置数据,过期时间为注解确定的访问周期
            redisTemplate.opsForValue().set(key, 1, rateLimit.cycle(), TimeUnit.SECONDS);
        }
        return true;
    }
}