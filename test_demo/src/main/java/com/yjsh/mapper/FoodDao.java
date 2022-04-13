package com.yjsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjsh.entity.Food;
import com.yjsh.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodDao extends BaseMapper<Food> {
}
