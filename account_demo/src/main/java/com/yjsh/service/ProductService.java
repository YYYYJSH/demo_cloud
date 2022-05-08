package com.yjsh.service;

import com.yjsh.entity.TestProductEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 产品服务
 *
 * @author YYYYJSH
 * @date 2022/05/08
 */
@Component
@RequestMapping("/productFeign")
public interface ProductService {

    @ResponseBody
    @PostMapping("/getProductByIds")
    public List<TestProductEntity> listProduct(@RequestBody  List<Integer> skuIds);

}
