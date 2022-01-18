package com.yjsh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.gson.JsonObject;
import com.yjsh.config.NeedLogin;
import com.yjsh.constant.R;
import com.yjsh.dto.resp.UserVo;
import com.yjsh.entity.User;
import com.yjsh.service.UserService;
import com.yjsh.utils.JWTUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 殷金帅
 * @date 2021-12-15 09:37
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/login")
    public R login(@RequestBody User param) {

        UserVo login = userService.login(param);
        if (login.getCod() != 200) {
            return R.error(login);
        }
        String token = JWTUtil.generatorToken(login.getLoginUser().getUserName(), login.getLoginUser());
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", login);

        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletResponse response = servletRequestAttributes.getResponse();
        response.addHeader("token",token);

        Cookie msgCookie = new Cookie("token",token);

        response.addCookie(msgCookie);
        return R.ok(result);
    }

    @NeedLogin
    @GetMapping("/query")
    public R query(@RequestParam("name") String name, @RequestParam("phone") String phone,
                   @RequestParam("page")Integer page, @RequestParam("limit")Integer limit){
        User user = user();
        if (user.getRoleId() != 1){
            return R.errorPower();
        }
        IPage<User> o = userService.queryByNameOrPhone(name, phone, page, limit);

        return R.ok(o);
    }
}