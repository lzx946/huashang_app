package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.*;
import com.lzx.hsapp.utils.Pagination;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HomeDataService {


    /**
     * 获取首页海报数据
     *
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
     *
     * @return
     */
    HomeDataDetail selectHomeDataDetail(Integer id);

    /**
     * 批量插入信息
     * @param record
     * @return
     */
    boolean insertBatch(List<Course> record);

    /**
     * 根据专家ID获取课程信息
     * @param teacherid
     * @return
     */
    List<Course> selectCoursebyteacher(Integer teacherid, Pagination pagination);

    /**
     * 更新课程
     */
    int updateByPrimaryKey(Course record);

    /**
     * 获取首页背景图
     * @return
     */
    List<Backgroudinfo> selectAll();

    /**
     * 获取首页新闻
     * @return
     */
    List<News> homeNews(Integer iscommend);

    /**
     * 获取教师推荐
     * @param record
     * @return
     */
    List<ExpertsVoinfo> homeExpert(ExpertsVoinfo record);
}
