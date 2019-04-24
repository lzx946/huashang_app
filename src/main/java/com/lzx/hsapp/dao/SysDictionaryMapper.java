package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.SysDictionary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SysDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDictionary record);

    SysDictionary selectByPrimaryKey(Integer id);

    List<SysDictionary> selectAll(SysDictionary sysDictionary);

    int updateByPrimaryKey(SysDictionary record);

    @Select("select * from sys_dictionary where codeflag = #{codeFlag}")
    SysDictionary findByCodeFlag(@Param("codeFlag") Integer codeFlag);
}