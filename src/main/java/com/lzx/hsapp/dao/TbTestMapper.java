package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.TbTest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TbTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbTest record);

    TbTest selectByPrimaryKey(Integer id);

    List<TbTest> selectAll();

    int updateByPrimaryKey(TbTest record);
}