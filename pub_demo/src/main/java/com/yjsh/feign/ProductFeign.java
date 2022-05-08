package com.yjsh.feign;

import com.yjsh.entity.TestProductEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "account-demo"
)
public interface ProductFeign {
    @PostMapping({"/productFeign/getProductByIds"})
    List<TestProductEntity> getProductByIds(@RequestBody List<Integer> skuIds);

}
