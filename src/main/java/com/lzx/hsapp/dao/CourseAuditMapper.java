package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.CourseAudit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CourseAuditMapper {

    @Select("select * from course_audit where course_ids like concat('%',#{courseId},'%')")
    CourseAudit findByCourseId(@Param("courseId") String courseId);

    @Update("update course_audit set" +
            "course_ids = #{courseIds}," +
            "status = #{status}," +
            "first_audit = #{firstAudit}," +
            "first_audit_time = #{firstAuditTime}," +
            "course_file = #{courseFile}," +
            "upload_time = #{uploadTime}," +
            "start_course_time = #{startCourseTime}," +
            "stop_course_time = #{stopCourseTime}," +
            "create_time = #{createTime}," +
            "modify_time = #{modifyTime}" +
            "where id = #{id}")
    void update(CourseAudit courseAudit);

    @Delete("delete from course_audit where course_ids like concat('%',#{courseId},'%')")
    void deleteLikeId(@Param("courseId") String courseId);
}
