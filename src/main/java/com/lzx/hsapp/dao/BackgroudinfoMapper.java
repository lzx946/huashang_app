package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Backgroudinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BackgroudinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Backgroudinfo record);

    Backgroudinfo selectByPrimaryKey(Integer id);

    List<Backgroudinfo> selectAll();

    int updateByPrimaryKey(Backgroudinfo record);
}