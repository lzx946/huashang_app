package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.SysManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysManagement record);

    SysManagement selectByPrimaryKey(Integer id);

    List<SysManagement> selectAll();

    int updateByPrimaryKey(SysManagement record);
}