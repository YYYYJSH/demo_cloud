package com.yjsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjsh.entity.TestOrdersEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测试订单表 Mapper
 *
 * @author xx
 * @date 2022-05-08 10:47:31
 */
//@DS(DataSourceName.)  // mapper 默认数据源 需自行修改
@Mapper
public interface TestOrdersMapper extends BaseMapper<TestOrdersEntity> {

}
