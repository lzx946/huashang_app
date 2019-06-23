package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.StudentsVoinfo;
import com.lzx.hsapp.entity.Studentsinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
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

    @Select("select * from studentsinfo where id = #{id}")
    Studentsinfo findById(@Param("id") Integer id);

    @Select("select * from studentsinfo where phone = #{phone} and state = 1")
    Studentsinfo findByPhone(@Param("phone") String phone);
}