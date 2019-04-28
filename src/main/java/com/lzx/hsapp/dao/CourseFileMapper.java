package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.CourseFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CourseFileMapper {

    @Insert("insert into course_file (id,course_ids,file_id,create_time,modify_time) values (#{id},#{courseIds},#{fileId},#{createTime},#{modifyTime})")
    void insert(CourseFile courseFile);

    @Select("select * from course_file where course_ids like concat('%',#{courseId},'%')")
    List<CourseFile> findByCourseId(@Param("courseId") String courseId);
}
