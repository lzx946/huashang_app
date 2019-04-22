package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Backgroudinfo;

import java.util.List;


public interface BackgroudinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Backgroudinfo record);

    Backgroudinfo selectByPrimaryKey(Integer id);

    List<Backgroudinfo> selectAll();

    int updateByPrimaryKey(Backgroudinfo record);
}