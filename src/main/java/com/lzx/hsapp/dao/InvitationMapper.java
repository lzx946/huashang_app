package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Invitation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface InvitationMapper {

    @Insert("insert into invitation (id,course_id,course_name,city,place,start_time,stop_time,agree,create_time,modify_time) " +
            "values (#{id},#{courseId},#{courseName},#{city},#{place},#{startTime},#{stopTime},#{agree},#{createTime},#{modifyTime})")
    void insert(Invitation invitation);

    @Select("select * from invitation where course_id = #{courseId} order by create_time")
    List<Invitation> findByCourseId(@Param("courseId") Integer courseId);

    @Select("select * from invitation where course_name = #{courseName} and create_Time = #{createTime}")
    List<Invitation> findByCourseNameAndCreateTime(@Param("courseName") String courseName,@Param("createTime") Date createTime);

    @Select("select * from invitation where course_id in (${courseIds}) and agree = #{agree}")
    List<Invitation> findByCourseIdsAndAgree(@Param("courseIds") String courseIds,@Param("agree") String agree);

    @Update("update invitation set agree = #{agree}, modify_time = now() where id = #{id}")
    void updateAgree(@Param("agree") String agree,@Param("id") Integer id);

    @Delete("delete from invitation where course_id = #{courseId}")
    void delete(@Param("courseId") Integer courseId);
}
