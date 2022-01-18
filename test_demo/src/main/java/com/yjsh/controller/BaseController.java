package com.yjsh.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yjsh.entity.User;
import com.yjsh.utils.JWTUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class BaseController {

    /**
     * 在Controller中负责获取request对象的。
     * @return 当前线程绑定的request对象。
     */
    public HttpServletRequest request(){
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }
    /**
     * 获取当前登录的用户实体
     */
    public User user(){
        String token=request().getHeader(JWTUtil.TOKEN);
        User userVO = JWTUtil.checkToken(token);
        if (ObjectUtil.isNotEmpty(userVO)){
            return userVO;
        }
        List<Cookie> list = new ArrayList<>(Arrays.asList(request().getCookies()));
        List<Cookie> tokenCookie = list.stream().map(e -> {
            JWTUtil.TOKEN.equals(e.getName());
            return e;
        }).collect(Collectors.toList());
        if (ArrayUtil.isNotEmpty(tokenCookie)){
            User user = JWTUtil.checkToken(tokenCookie.get(0).getValue());
            return user;
        }
        return null;
    }
}
