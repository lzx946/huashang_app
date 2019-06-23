package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.CourseAudit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CourseAuditMapper {

    @Insert("insert into course_audit (id,course_ids,status,first_audit,first_audit_time,course_file,upload_time,start_course_time,stop_course_time,create_time,modify_time) " +
            "values (#{id},#{courseIds},#{status},#{firstAudit},#{firstAuditTime},#{courseFile},#{uploadTime},#{startCourseTime},#{stopCourseTime},#{createTime},#{modifyTime})")
    void insert(CourseAudit courseAudit);

    @Select("select * from course_audit where course_ids like concat('%',#{courseId},'%')")
    CourseAudit findByCourseId(@Param("courseId") String courseId);

    @Update("update course_audit set " +
            "course_ids = #{courseIds}," +
            "status = #{status}," +
            "first_audit = #{firstAudit}," +
            "first_audit_time = #{firstAuditTime}," +
            "course_file = #{courseFile}," +
            "upload_time = #{uploadTime}," +
            "start_course_time = #{startCourseTime}," +
            "stop_course_time = #{stopCourseTime}," +
            "create_time = #{createTime}," +
            "modify_time = #{modifyTime} " +
            "where id = #{id}")
    void update(CourseAudit courseAudit);

    @Delete("delete from course_audit where course_ids like concat('%',#{courseId},'%')")
    void deleteLikeId(@Param("courseId") String courseId);

    @Select("select * from course_audit where status = #{status} ")
    List<CourseAudit> findByStatus(@Param("status") String status);
}
