package com.yjsh.controller;

import com.yjsh.constant.R;
import com.yjsh.dto.req.CreateOrder;
import com.yjsh.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 殷金帅
 * @date 2022-05-08 10:51
 **/
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public R hellonotLogin(@RequestBody CreateOrder createOrder){

        try {
            orderService.createOrder(createOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }

        System.out.println(createOrder);

        return R.ok("下单成功！");
    }

}
