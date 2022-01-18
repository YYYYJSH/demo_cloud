package com.yjsh.controller;

import com.yjsh.config.NeedLogin;
import com.yjsh.config.RateLimit;
import com.yjsh.constant.R;
import com.yjsh.constant.Result;
import com.yjsh.utils.AlibabaOSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.Message;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 殷金帅
 * @date 2021-12-15 09:06
 **/
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private final String xx = "ALARM-NOTICE";

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private final String INDEX_NAME = "book";

    private final static String URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String WE_CHAT_APPID= "你的appid";
    private static final String WE_CHAT_APP_SECRET = "你的secret";

    @GetMapping("/0")
    public R hello0(@RequestParam("path") String path){
        return R.error("请勿频繁操作！").setResult(path);
    }

    @GetMapping("/notLogin")
    public R hellonotLogin(){
        return R.error("未登录！");
    }

    @RateLimit(number = 2 ,cycle = 10)
    @GetMapping("/s")
    @NeedLogin
    public Result<String> hellox(@RequestParam String name){
        log.info("----进来啦！---");
        Result<String> result = new Result<>();
        result.setResult("111");
        return result;
    }

    @RateLimit(number = 2 ,cycle = 10)
    @GetMapping("/x")
    public R hello1(@RequestParam String name){
        log.info("----进来啦！---");
        return R.ok("111");
    }


    @GetMapping("/mq")
    public R sendMq(HttpServletRequest request) {
        Map<String,String> param = new HashMap<>();
        param.put("xx","xx");//
        Message<Map<String,String>> message = MessageBuilder
                .withPayload(param)
                .build();
        rocketMQTemplate.send("COUNT_ORDER_AND_USER",message);
        return R.ok("s");
    }

    @PostMapping ("/6")
    public R testCode(HttpServletRequest request, HttpServletResponse response){
        String test = null;
        try {
            Part file = request.getPart("file");
            InputStream fileStream = file.getInputStream();

            String path = AlibabaOSSUtil.uploadFile(String.valueOf(UUID.randomUUID()), fileStream);
            //path需要存起来，这个是下载的地址
            log.info("path:{}",test);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return R.ok(test);
    }
}