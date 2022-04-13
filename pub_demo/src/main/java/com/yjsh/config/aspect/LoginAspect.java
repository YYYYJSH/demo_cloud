package com.yjsh.config.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yjsh.constant.R;
import com.yjsh.utils.JWTUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Aspect
@Lazy
public class LoginAspect {

    @Around(value = "@annotation(com.yjsh.config.NeedLogin)")
    public Object loginCheck(ProceedingJoinPoint proceedingJoinPoint
    ) throws Throwable {

        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes != null ? servletRequestAttributes.getRequest() : null;
        if (ObjectUtil.isNotEmpty(request)&& ObjectUtil.isNotEmpty(request.getHeader(JWTUtil.TOKEN))) {
            return  null == JWTUtil.checkToken(request.getHeader(JWTUtil.TOKEN)) ? R.errorLogin() : proceedingJoinPoint.proceed();
        }

        List<Cookie> list = new ArrayList<>(Arrays.asList(servletRequestAttributes.getRequest().getCookies()));
        List<Cookie> tokenCookie = list.stream().map(e -> {
            JWTUtil.TOKEN.equals(e.getName());
            return e;
        }).collect(Collectors.toList());
        if (ArrayUtil.isNotEmpty(tokenCookie)){
            return null ==  JWTUtil.checkToken(tokenCookie.get(0).getValue()) ? R.errorLogin() : proceedingJoinPoint.proceed();
        }
        return R.errorLogin();
    }
}
