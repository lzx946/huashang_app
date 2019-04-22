package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.TbTest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface TbTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbTest record);

    TbTest selectByPrimaryKey(Integer id);

    List<TbTest> selectAll();

    int updateByPrimaryKey(TbTest record);
}