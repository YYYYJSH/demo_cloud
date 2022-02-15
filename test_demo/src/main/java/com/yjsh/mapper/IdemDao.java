package com.yjsh.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjsh.model.Idem;
import com.yjsh.model.IdemExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@DS("talk")
public interface IdemDao extends BaseMapper<Idem> {
    long countByExample(IdemExample example);

    int deleteByExample(IdemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Idem record);

    @DS("talk")
    int insertSelective(Idem record);

    List<Idem> selectByExample(IdemExample example);

    Idem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Idem record, @Param("example") IdemExample example);

    int updateByExample(@Param("record") Idem record, @Param("example") IdemExample example);

    int updateByPrimaryKeySelective(Idem record);

    int updateByPrimaryKey(Idem record);
}