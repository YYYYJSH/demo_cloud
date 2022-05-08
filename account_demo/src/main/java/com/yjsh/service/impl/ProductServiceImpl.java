package com.yjsh.service.impl;

import com.yjsh.entity.TestProductEntity;
import com.yjsh.mapper.TestProductMapper;
import com.yjsh.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 殷金帅
 * @date 2022-05-08 11:14
 **/
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private TestProductMapper productMapper;

    @Override
    public List<TestProductEntity> listProduct(List<Integer> skuIds) {
        List<TestProductEntity> testProductEntities = productMapper.selectBatchIds(skuIds);

        return testProductEntities;
    }
}
