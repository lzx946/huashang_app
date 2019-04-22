package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Enlist;
import com.lzx.hsapp.entity.EnlistVoinfo;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface EnlistMapper {
    int deleteByCidAndUid(EnlistVoinfo enlistVoinfo);

    int insert(Enlist record);

    Enlist selectByPrimaryKey(Integer id);

    List<EnlistVoinfo> selectAll(EnlistVoinfo enlistVoinfo);

    int updateByPrimaryKey(Enlist record);
}