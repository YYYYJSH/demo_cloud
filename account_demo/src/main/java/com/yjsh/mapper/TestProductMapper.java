package com.yjsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjsh.entity.TestProductEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测试商品表 Mapper
 *
 * @author xx
 * @date 2022-05-08 10:49:43
 */
//@DS(DataSourceName.)  // mapper 默认数据源 需自行修改
@Mapper
public interface TestProductMapper extends BaseMapper<TestProductEntity> {

}
