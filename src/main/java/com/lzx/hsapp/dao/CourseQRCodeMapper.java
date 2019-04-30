package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.CourseQRCode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CourseQRCodeMapper {

    @Insert("insert into course_qr_code (id,course_id,qr_code,create_time,modify_time) values (#{id},#{courseId},#{qrCode},now(),#{modifyTime})")
    void insert(CourseQRCode courseQRCode);

    @Update("update course_qr_code set course_id = #{courseId}, qr_code = #{qrCode}, create_time = #{createTime}, modify_time = #{modifyTime} where id = #{id}")
    void update(CourseQRCode courseQRCode);

    @Select("select * from course_qr_code where course_id = #{courseId}")
    CourseQRCode findByCourseId(@Param("courseId") Integer courseId);
}
