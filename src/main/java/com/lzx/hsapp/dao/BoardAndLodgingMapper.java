package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.BoardAndLodging;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BoardAndLodgingMapper {

    @Insert("insert into board_and_lodging (id,trip_id,city,place,start_time,stop_time,create_time,modify_time) " +
            "values (#{id},#{tripId},#{city},#{place},#{startTime},#{stopTime},#{createTime},#{modifyTime})")
    void insert(BoardAndLodging boardAndLodging);

    @Select("select * from board_and_lodging where trip_id = #{tripId}")
    List<BoardAndLodging> findByTripId(@Param("tripId") Integer tripId);
}
