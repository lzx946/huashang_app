package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.CourseQRCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CourseQRCodeMapper {

    @Insert("insert into course_qr_code (id,course_id,qr_code,create_time,modify_time) values (#{id},#{courseId},#{qrCode},now(),#{modifyTime})")
    void insert(CourseQRCode courseQRCode);
}
