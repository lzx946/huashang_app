package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.CourseAudit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CourseAuditMapper {

    @Select("select * from course_audit where course_ids like concat('%',#{courseId},'%')")
    CourseAudit findByCourseId(@Param("courseId") String courseId);
}
