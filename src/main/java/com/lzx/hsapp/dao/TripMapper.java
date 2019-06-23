package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Trip;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TripMapper {

    @Insert("insert into trip (id,course_ids,teacher_id,agree,board_and_lodging,board_and_lodging_time,`procedure`,status,create_time,modify_time) " +
            "values (#{id},#{courseIds},#{teacherId},#{agree},#{boardAndLodging},#{boardAndLodgingTime},#{procedure},#{status},#{createTime},#{modifyTime})")
    void insert(Trip trip);

    @Update("update trip set " +
            "course_ids = #{courseIds}," +
            "teacher_id = #{teacherId}," +
            "agree = #{agree}," +
            "board_and_lodging = #{boardAndLodging}," +
            "board_and_lodging_time = #{boardAndLodgingTime}," +
            "`procedure` = #{procedure}," +
            "status = #{status}," +
            "create_time = #{createTime}," +
            "modify_time = #{modifyTime} " +
            "where id = #{id}")
    void update(Trip trip);

    @Select("select * from trip where id = #{id}")
    Trip findById(@Param("id") Integer id);

    @Delete("delete from trip where id = #{id}")
    void delete(@Param("id") Integer id);

    @Select("select * from trip where teacher_id = #{teacherId} order by create_time desc")
    List<Trip> findByTeacherId(@Param("teacherId") Integer teacherId);
}
