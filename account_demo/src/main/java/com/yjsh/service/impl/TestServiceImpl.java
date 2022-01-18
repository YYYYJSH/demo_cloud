package com.yjsh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yjsh.entity.User;
import com.yjsh.feign.UserFeign;
import com.yjsh.service.TestService;
import com.yjsh.utils.AsyncBatchCallUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author 殷金帅
 * @date 2021-12-15 15:54
 **/
@Slf4j
@Service
public class TestServiceImpl implements TestService {


    @Resource
    private UserFeign accountFeign;

    @SneakyThrows
    @Async
    @Override
    public Object syncTest() {
        int page = 1;
        int limit = 999;

        JSONObject jsonObject = new JSONObject();

        CountDownLatch countDownLatch = new CountDownLatch(2);
        AsyncBatchCallUtil.execute(() -> {
            log.info("this A");
            jsonObject.put("ResultA","this a !");
            countDownLatch.countDown();
        });
        AsyncBatchCallUtil.execute(() -> {
            log.info("this B");
            jsonObject.put("ResultB","this b !");
            countDownLatch.countDown();
        });

        countDownLatch.await();

        return jsonObject;
    }


    @Override
    public Object testFeign(String name ,String phone,Integer page,Integer limit) {

        IPage<User> r = accountFeign.listRecentRecord(name, phone, page, limit);
        return r;
    }
}
