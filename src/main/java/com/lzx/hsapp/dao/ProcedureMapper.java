package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Procedure;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProcedureMapper {

    @Insert("insert into `procedure` (id,trip_id,visa,plane_ticket,create_time,modify_time) " +
            "values (#{id},#{tripId},#{visa},#{planeTicket},#{createTime},#{modifyTime})")
    void insert(Procedure procedure);

    @Update("update `procedure` set " +
            "trip_id = #{tripId}," +
            "visa = #{visa}," +
            "plane_ticket = #{planeTicket}," +
            "create_time = #{createTime}," +
            "modify_time = #{modifyTime}" +
            "where id = #{id}")
    void update(Procedure procedure);

    @Select("select * from `procedure` where trip_id = #{tripId}")
    Procedure findByTripId(@Param("tripId") Integer tripId);
}
