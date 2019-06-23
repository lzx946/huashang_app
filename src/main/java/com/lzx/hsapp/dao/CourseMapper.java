package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Course;
import com.lzx.hsapp.entity.CourseVo;
import com.lzx.hsapp.entity.HomeData;
import com.lzx.hsapp.entity.HomeDataDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 批量插入课程
     * @param record
     * @return
     */
    int insertBatch(List<Course> record);

    Course selectByPrimaryKey(Integer id);

    List<CourseVo> selectAll(CourseVo courseVo);


    /**
     * 更新课程
     * @param record
     * @return
     */
    int updateByPrimaryKey(Course record);

    /**
     * 获取首页海报数据
     * @return
     */
    List<HomeData> selectHomeData();
    /**
     * 获取学习数据
     * @return
     */
    List<HomeData> selectHomeDataList();

    /**
     * 获取首页详情数据
     * @return
     */
    HomeDataDetail selectHomeDataDetail(Integer id);

    /**
     * 根据专家查询课程
     * @param teacherid
     * @return
     */
    List<Course> selectCoursebyteacher(Integer teacherid);

    /**
     * 根据专家id和课程名查询不同地点的课程
     * @param courseVo
     * @return
     */
    List<CourseVo> selectByteach(CourseVo courseVo);

    @Select("select * from course where teacherid = #{teacherId} and state <> 2 group by name, period order by createtime desc")
    List<Course> findByTeacherId(@Param("teacherId") Integer teacherId);

    @Select("select * from course where teacherid = #{teacherId} and name = #{name} and period = #{period} and state <> 2")
    List<Course> findByTeacherIdAndNameAndPeriod(@Param("teacherId") Integer teacherId,@Param("name") String name,@Param("period") String period);

    @Select("select * from course where id = #{id}  and state <> 2")
    Course findById(@Param("id") Integer id);

    @Select("select * from course where name = #{name} and period = #{period} and state <> 2")
    List<Course> findByNameAndPeriod(@Param("name") String name,@Param("period") String period);

    @Select("select id from course where name = #{name} and period = #{period} and state <> 2")
    List<Integer> findIdsByNameAndPeriod(@Param("name") String name,@Param("period") String period);

    @Select("select * from course where id in (${ids}) and state <> 2 group by period ")
    Course findByIds(@Param("ids") String ids);

    @Delete("delete from course where id = #{id}")
    void deleteById(@Param("id") Integer id);

    @Delete("delete from course where id in (${ids})")
    void deleteInIds(@Param("ids") String ids);

    @Insert("insert into course (name,room,teacherid,state,type,posterid,summary,content,starttime,stoptime,reason,createtime,period,classroom,iscommend) " +
            "values (#{name},#{room},#{teacherid},#{state},#{type},#{posterid},#{summary},#{content},#{starttime},#{stoptime},#{reason},#{createtime},#{period},#{classroom},#{iscommend})")
    void insertNewCourse(Course course);

    @Select("select * from course where id in (${ids}) and state <> 2")
    List<Course> findInIds(@Param("ids") String ids);

    @Select("select max(period) from course where name = #{name} and state <> 2")
    String findMaxPeriodByName(@Param("name") String name);

    @Update("update course set starttime = #{startTime}, stoptime = #{stopTime} where id = #{id}")
    void updateTimeById(@Param("id") Integer id, @Param("startTime") Date startTime,@Param("stopTime") Date stopTime);
}