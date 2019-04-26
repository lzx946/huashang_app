package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.SysFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SysFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysFile record);

    SysFile selectByPrimaryKey(Integer id);

    List<SysFile> selectAll();

    int updateByPrimaryKey(SysFile record);

    @Select("select * from sys_file where url = #{url}")
    SysFile findByUrl(@Param("url") String url);
}