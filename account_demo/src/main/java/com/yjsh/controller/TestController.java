package com.yjsh.controller;

import com.alibaba.fastjson.JSONObject;
import com.yjsh.config.NeedLogin;
import com.yjsh.constant.R;
import com.yjsh.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 殷金帅
 * @date 2021-12-15 13:30
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/1")
    public String hello(){
        return "hello";
    }


    @GetMapping("/sync")
    public R sync(){
        Object o = testService.syncTest();
        return R.ok(o);
    }

    @GetMapping("/phone")
    public R subPhone(@RequestParam("phone") String phone){
//        String phone = "13123456789";
        String phoneNumber = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("old",phone);
        jsonObject.put("new",phoneNumber);
        return R.ok(jsonObject);
    }


    @NeedLogin
    @GetMapping("/queryUser")
    public R queryUser(@RequestParam("name") String name, @RequestParam("phone") String phone,
                          @RequestParam("page")Integer page, @RequestParam("limit")Integer limit){
        Object o = testService.testFeign(name, phone, page, limit);
        return R.ok(o);
    }


}
