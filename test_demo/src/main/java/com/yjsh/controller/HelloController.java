package com.yjsh.controller;

import cn.hutool.core.util.StrUtil;
import com.yjsh.YjshTestStartApplication;
import com.yjsh.config.NeedLogin;
import com.yjsh.config.RateLimit;
import com.yjsh.constant.R;
import com.yjsh.constant.Result;
import com.yjsh.utils.AlibabaOSSUtil;
import com.yjsh.utils.RandomColor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 殷金帅
 * @date 2021-12-15 09:06
 **/
@Slf4j
@RestController
@RequestMapping("/hello")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=YjshTestStartApplication.class)
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

    @GetMapping("/empty")
    public R empty(HttpServletRequest request,@RequestParam("str")String str) {
        String all = "";
        log.info("str:{}",str);

        if (StrUtil.isEmpty(str)){
            all = "empty";
            log.info("empty");
        }
        if (StrUtil.isBlank(str)){
            all = all + "blank";
            log.info("blank");
        }

        return R.ok(all);
    }

    @GetMapping("/random")
    public R random() {
        Map<Integer,String> result = new ConcurrentHashMap<>();

        result = initnew(result);
        int times = 1;
        while (result.get("3") == null){
            result = initnew(result);
            times++;
        }

        return R.ok(result);
    }

    private Map<Integer,String> initnew(Map result){
        Random rand = new Random();
        for (int i = 0; i < 4 ; i++) {
            int ran = rand.nextInt(10 + 1);
            if (ran != 10){
                break;
            }else {
                result.put(i, String.valueOf(ran));
            }
        }
        return result;
    }


    @GetMapping("/5")
    public R sendSaleMq(HttpServletRequest request) {
        Map<String,String> param = new HashMap<>();
        param.put("type","5");//这里是推送统计订单的类型
        param.put("memberNum","325");
        param.put("orderNum","55");
        param.put("orderAmountSum", "21271.80");
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


    @Test
    @GetMapping("/template")
    public void testWxTemplate(){
        //@RequestParam("type")String type,@RequestParam("message")String message
        Map<String,String> param = new HashMap<>();
        log.info("start");
//        param.put("type",request.getParameter("type"));
        param.put("projectName","测试的项目");
        param.put("environment","本地环境？");
        param.put("time","就在刚才！");
//        param.put("message","有没有颜色捏！？");
//        param.put("memberNum",request.getParameter("memberNum"));
//        param.put("orderNum",request.getParameter("orderNum"));
//        param.put("orderAmountSum",request.getParameter("orderAmountSum"));
        param.put("type","5");
        param.put("message","### **<font color=#00FF00 >服务信息 - \uD83D\uDE05-</font>**\n" +
                "时间：yyyy-MM-dd hh:mm:ss\n"+
                "\n" +
                ">   **系统环境**: 环境名称 \n" +
                ">   **发生接口**: 接口名称（带上路径）\n" +
                ">   **信息描述**: 这里是问题描述信息\n" +
                ">   **处理人员**: 殷金帅\n" +
                ">   **信息来源**: [SkyWalking](http://skywalking.simeitol.net\\target=out)");
        Message<Map<String,String>> messages = MessageBuilder
                .withPayload(param)
                .build();
        rocketMQTemplate.send("COUNT_ORDER_AND_USER",messages);
        log.info("end");

    }

    @Test
    public void orderCount(){
        Map<String,String> param = new HashMap<>();
        param.put("type","5");//这里是推送统计订单的类型
        param.put("memberNum","325");
        param.put("orderNum","55");
        param.put("orderAmountSum", "21271.80");
        Message<Map<String,String>> message = MessageBuilder
                .withPayload(param)
                .build();
        rocketMQTemplate.send("COUNT_ORDER_AND_USER",message);
    }

    @Test
    public void orderCountAndUser(){
        Map<String,String> param = new HashMap<>();
        param.put("type","1");//这里是推送统计订单的类型
        param.put("message","### **<font color=#00FF00 >姿美汇小程序每日统计  </font>**\n" +
                "\n" +
                ">   **统计日期**: 2022-01-20\n" +
                ">   **新增用户**: 325\n" +
                ">   **订单数量**: 55\n" +
                ">   **销售金额**: 21271.80\n" +
                ">   **更多信息**: [微信公众平台](https://mp.weixin.qq.com/)\n");
        Message<Map<String,String>> message = MessageBuilder
                .withPayload(param)
                .build();
        rocketMQTemplate.send("COUNT_ORDER_AND_USER",message);
    }

    @Test
    public void finalOrderCountAndUser(){
        Map<String,String> param = new HashMap<>();
        param.put("type","6");
        //新增用户
        param.put("memberNum","325");
        //订单数量
        param.put("orderNum","55");
        //销售金额
        param.put("orderAmount", "21271.80");


        //用户数量
        param.put("memberNumSum", "用户数量");
        //订单总量
        param.put("订单总量", "订单总量");
        //订单成交总金额
        param.put("orderAmountSum", "21271.80");


        Message<Map<String,String>> message = MessageBuilder
                .withPayload(param)
                .build();
//        rocketMQTemplate.send("COUNT_ORDER_AND_USER",message);

        rocketMQTemplate.convertAndSend("COUNT_ORDER_AND_USER", param);
    }

}