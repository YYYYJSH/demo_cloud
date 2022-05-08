package com.yjsh.consumer;

import com.yjsh.entity.TestOrdersProductEntity;
import com.yjsh.entity.TestProductEntity;
import com.yjsh.mapper.TestProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 殷金帅
 * @date 2022-05-08 13:52
 **/
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "SKU_NUM",
        consumerGroup = "GROUP-ORDERS")
public class ProductConsumer implements RocketMQListener<Map<String,List<TestOrdersProductEntity>>> {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Resource
    private TestProductMapper productMapper;

    @Override
    public void onMessage(Map<String, List<TestOrdersProductEntity>> stringStringMap) {
        log.info("来新消息了！开始扣减库存 :{}",stringStringMap);
        List<TestOrdersProductEntity> ordersProductEntityList = stringStringMap.get("sku");
        Map<Integer, TestOrdersProductEntity> pMap = ordersProductEntityList.stream().collect(Collectors.toMap(TestOrdersProductEntity::getSkuId, Function.identity(), (key1, key2) -> key2));
        List<Integer> skuIds = ordersProductEntityList.stream().map(TestOrdersProductEntity::getSkuId).collect(Collectors.toList());
        List<TestProductEntity> testProductEntities = productMapper.selectBatchIds(skuIds);
        testProductEntities.stream().forEach(e->{
            e.setSkuInventory(e.getSkuInventory() - pMap.get(e.getId()).getSkuNum());
        });

        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            TestProductMapper productDao_ = session.getMapper(TestProductMapper.class);
            for (int i = 0; i < testProductEntities.size(); i++) {
                productDao_.updateById(testProductEntities.get(i));
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

    }
}