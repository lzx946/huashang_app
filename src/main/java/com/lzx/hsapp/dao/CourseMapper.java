package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Course;
import com.lzx.hsapp.entity.CourseVo;
import com.lzx.hsapp.entity.HomeData;
import com.lzx.hsapp.entity.HomeDataDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

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

    @Select("select * from course where teacherid = #{teacherId} group by period")
    List<Course> findByTeacherId(@Param("teacherId") Integer teacherId);

    @Select("select * from course where teacherid = #{teacherId} and name = #{name} and period = #{period}")
    List<Course> findByTeacherIdAndNameAndPeriod(@Param("teacherId") Integer teacherId,@Param("name") String name,@Param("period") String period);
}