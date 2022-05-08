package com.yjsh.service.impl;

import com.yjsh.dto.req.CreateOrder;
import com.yjsh.dto.req.CreateProduct;
import com.yjsh.entity.TestOrdersEntity;
import com.yjsh.entity.TestOrdersProductEntity;
import com.yjsh.entity.TestProductEntity;
import com.yjsh.feign.ProductFeign;
import com.yjsh.mapper.TestOrdersMapper;
import com.yjsh.mapper.TestOrdersProductMapper;
import com.yjsh.service.OrderService;
import com.yjsh.utils.AsyncBatchCallUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 殷金帅
 * @date 2022-05-08 10:53
 **/
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private TestOrdersMapper ordersMapper;
    @Resource
    private TestOrdersProductMapper ordersProductMapper;
    @Resource
    private ProductFeign productFeign;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Resource
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 创建订单
     *
     * @param createOrder 创建订单
     * @return boolean
     */
    @Override
    public boolean createOrder(CreateOrder createOrder) {
        String userId = createOrder.getUserId();
        List<CreateProduct> products = createOrder.getProducts();

        List<Integer> skuIds = products.stream().map(CreateProduct::getSkuId).collect(Collectors.toList());

        List<TestProductEntity> productByIds = productFeign.getProductByIds(skuIds);
        Map<Integer, TestProductEntity> productEntityMap = productByIds.stream().collect(Collectors.toMap(TestProductEntity::getId, Function.identity(), (key1, key2) -> key2));

        products.stream().forEach(e -> {
            boolean b = productByIds.stream().anyMatch(testProductEntity -> testProductEntity.getId().equals(e.getSkuId()));
            log.info("skuid:{},success:{}", e, b);
            if (!b) {
                throw new RuntimeException("下单商品有误");
            }
            TestProductEntity testProductEntity = productEntityMap.get(e.getSkuId());
            if (testProductEntity.getSkuInventory() < e.getSkuNum()) {
                throw new RuntimeException("商品" + testProductEntity.getSkuName() + "库存不足！");
            }
        });

        //订单号
        String orderSn = UUID.randomUUID().toString();

        //创建订单
        BigDecimal allmoney = products.stream().map(e -> {
            TestProductEntity testProductEntity = productEntityMap.get(e.getSkuId());
            return testProductEntity.getSkuPrice().multiply(BigDecimal.valueOf(e.getSkuNum()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        TestOrdersEntity testOrdersEntity = new TestOrdersEntity();
        testOrdersEntity.setOrderMoney(allmoney);
        testOrdersEntity.setOrderSn(orderSn);
        ordersMapper.insert(testOrdersEntity);



        //生成订单商品
        List<TestOrdersProductEntity> ordersProductList = products.stream().map(e -> {
            TestProductEntity testProductEntity = productEntityMap.get(e.getSkuId());
            TestOrdersProductEntity ordersProductEntity = new TestOrdersProductEntity();
            ordersProductEntity.setOrdersSn(orderSn);
            ordersProductEntity.setSkuId(e.getSkuId());
            ordersProductEntity.setSkuNum(e.getSkuNum());
            ordersProductEntity.setSkuPrice(testProductEntity.getSkuPrice());
            ordersProductEntity.setTotalPrice(testProductEntity.getSkuPrice().multiply(BigDecimal.valueOf(e.getSkuNum())));
            return ordersProductEntity;
        }).collect(Collectors.toList());

        //创建商品订单
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            TestOrdersProductMapper productDao_ = session.getMapper(TestOrdersProductMapper.class);
            for (int i = 0; i < ordersProductList.size(); i++) {
                productDao_.insert(ordersProductList.get(i));
            }
            session.commit();
            session.clearCache();
        }catch (Exception e){
            session.rollback();
            throw new RuntimeException("批量添加失败");
        }finally{
            if(session != null){
                session.close();
            }
        }

        //扣减库存
        AsyncBatchCallUtil.execute(() -> {
            Map<String,List<TestOrdersProductEntity>> param = new HashMap<>();

            param.put("sku",ordersProductList);//

            Message<Map<String,List<TestOrdersProductEntity>>> message = MessageBuilder
                    .withPayload(param)
                    .build();
            rocketMQTemplate.send("SKU_NUM",message);
        });

        return false;
    }
}
