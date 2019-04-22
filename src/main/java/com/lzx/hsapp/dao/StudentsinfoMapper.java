package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.StudentsVoinfo;
import com.lzx.hsapp.entity.Studentsinfo;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface StudentsinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Studentsinfo record);

    Studentsinfo selectByPrimaryKey(Studentsinfo studentsinfo);

    List<StudentsVoinfo> selectAll(StudentsVoinfo studentsVoinfo);

    /**
     * 更新学员信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Studentsinfo record);

    /**
     * 根据账户或邮箱登录
     * @param record
     * @return
     */
    Studentsinfo selectByAccountEmail(Studentsinfo record);
}