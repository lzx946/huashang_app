package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Enlist;
import com.lzx.hsapp.entity.EnlistVoinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface EnlistMapper {
    int deleteByCidAndUid(EnlistVoinfo enlistVoinfo);

    int insert(Enlist record);

    Enlist selectByPrimaryKey(Integer id);

    List<EnlistVoinfo> selectAll(EnlistVoinfo enlistVoinfo);

    int updateByPrimaryKey(Enlist record);

    @Select("select * from enlist where uid = #{studentId}")
    List<Enlist> findByStudentId(@Param("studentId") Integer studentId);
}