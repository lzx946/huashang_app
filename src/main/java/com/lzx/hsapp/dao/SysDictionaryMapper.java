package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.SysDictionary;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SysDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDictionary record);

    SysDictionary selectByPrimaryKey(Integer id);

    List<SysDictionary> selectAll(SysDictionary sysDictionary);

    int updateByPrimaryKey(SysDictionary record);
}