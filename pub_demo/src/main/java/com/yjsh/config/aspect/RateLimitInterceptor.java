package com.yjsh.config.aspect;

import com.yjsh.config.RateLimit;
import com.yjsh.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class RateLimitInterceptor extends HandlerInterceptorAdapter {

    private RateLimitService rateLimitService;

    public void setRateLimitService(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes != null ? servletRequestAttributes.getRequest() : null;
        HttpServletResponse httpServletResponse = servletRequestAttributes.getResponse();
        // 判断请求是否属于方法的请求
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法中的注解,看是否有该注解
            RateLimit rateLimit = handlerMethod.getMethodAnnotation(RateLimit.class);
            if (rateLimit == null) {
                return true;
            }
            // 请求IP地址
            String ip = request.getRemoteAddr();
            // 请求url路径
            String uri = request.getRequestURI();
            String token = httpServletRequest.getHeader(JWTUtil.TOKEN);
            Boolean limit = rateLimitService.limit(ip, uri, rateLimit);
            if (limit){
                return limit;
            }
            this.redirctLimit(request,httpServletResponse);
        }
        return false;
    }

    private void redirctLimit(HttpServletRequest request,HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/hello/0?path=redirectFrom："+request.getServletPath());
    }
    //notLogin
    private void redirctLogIn(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/hello/notLogin");
    }
}