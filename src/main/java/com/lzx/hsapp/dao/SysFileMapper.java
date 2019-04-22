package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.SysFile;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SysFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysFile record);

    SysFile selectByPrimaryKey(Integer id);

    List<SysFile> selectAll();

    int updateByPrimaryKey(SysFile record);
}