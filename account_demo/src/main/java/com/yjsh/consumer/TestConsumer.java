package com.yjsh.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 殷金帅
 * @date 2022-04-23 21:42
 **/


@Slf4j
@Component
@RocketMQMessageListener(
        topic = "COUNT_ORDER_AND_USER",
        consumerGroup = "GROUP-ORDERS")
public class TestConsumer implements RocketMQListener<Map<String,String>> {

    @Override
    public void onMessage(Map<String, String> stringStringMap) {
        log.info("来新消息了！:{}",stringStringMap);
    }
}
