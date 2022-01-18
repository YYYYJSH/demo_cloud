package com.yjsh.config.aspect;

import com.yjsh.config.RateLimit;

public interface RateLimitService {
    /**
     * 接口频次限制校验
     *
     * @param ip
     *            客户端IP
     * @param uri
     *            请求接口名
     * @param rateLimit
     *            限制频次信息
     * @return Boolean
     */
    Boolean limit(String ip, String uri, RateLimit rateLimit);
}
