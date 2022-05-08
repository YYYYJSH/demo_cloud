package com.yjsh.service;

import com.yjsh.dto.req.CreateOrder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/orderFeign")
public interface OrderService {

    boolean createOrder(CreateOrder createOrder);

}
