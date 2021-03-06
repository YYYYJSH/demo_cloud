package com.yjsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 项目启动类
 *
 * @Author: Noire
 * @Date: 2021/11/5 23:10
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class YjshAccountStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(YjshAccountStartApplication.class,args);
    }

}
