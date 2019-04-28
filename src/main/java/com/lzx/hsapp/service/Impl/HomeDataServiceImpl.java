package com.lzx.hsapp.service.Impl;

import com.lzx.hsapp.dao.BackgroudinfoMapper;
import com.lzx.hsapp.dao.CourseMapper;
import com.lzx.hsapp.dao.ExpertsinfoMapper;
import com.lzx.hsapp.dao.NewsMapper;
import com.lzx.hsapp.entity.*;
import com.lzx.hsapp.service.HomeDataService;
import com.lzx.hsapp.utils.ActionUtil;
import com.lzx.hsapp.utils.DateUtils;
import com.lzx.hsapp.utils.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class HomeDataServiceImpl implements HomeDataService {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private BackgroudinfoMapper backgroudinfoMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ExpertsinfoMapper expertsinfoMapper;

    @Override
    public List<HomeData> selectHomeData() {
        try {
            List<HomeData> list = courseMapper.selectHomeData();
            if (list == null || list.size() <= 0) {
                return null;
            } else {
                int sum = list.size();
                for (int i = 0; i < sum; i++) {
                    list.get(i).setCourseStartTimeStr(DateUtils.getMonth(list.get(i).getCourseStartTime()) +
                            "月" + DateUtils.getDayOfMonth(list.get(i).getCourseStartTime()) + "日"
                    );
                    list.get(i).setCourseStopTimeStr(DateUtils.getDayOfMonth(list.get(i).getCourseStopTime()) + "日"
                    );
                    list.get(i).setImgUrl(ActionUtil.ROOTURL + list.get(i).getImgUrl());
                }
                return list;
            }

        } catch (Exception e) {
            log.error("获取首页数据异常" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<HomeData> selectHomeDataList() {
        try {
            List<HomeData> list = courseMapper.selectHomeDataList();
            if (list == null || list.size() <= 0) {
                return null;
            } else {
                int sum = list.size();
                for (int i = 0; i < sum; i++) {
                    list.get(i).setCourseStartTimeStr(DateUtils.getMonth(list.get(i).getCourseStartTime()) +
                            "月" + DateUtils.getDayOfMonth(list.get(i).getCourseStartTime()) + "日"
                    );
                    list.get(i).setCourseStopTimeStr(DateUtils.getDayOfMonth(list.get(i).getCourseStopTime()) + "日"
                    );
                    list.get(i).setImgUrl(ActionUtil.ROOTURL + list.get(i).getImgUrl());
                }
                return list;
            }
        } catch (Exception e) {
            log.error("获取学习数据异常" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HomeDataDetail selectHomeDataDetail(Integer id) {
        try {
            HomeDataDetail homeData = courseMapper.selectHomeDataDetail(id);
            homeData.setCourseStartTimeStr(DateUtils.getMonth(homeData.getCourseStartTime()) +
                    "月" + DateUtils.getDayOfMonth(homeData.getCourseStartTime()) + "日"
            );
            homeData.setCourseStopTimeStr(DateUtils.getDayOfMonth(homeData.getCourseStopTime()) + "日"
            );
            homeData.setImgUrl(ActionUtil.ROOTURL + homeData.getImgUrl());
            return homeData;
        } catch (Exception e) {
            log.error("获取首页详情数据异常" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertBatch(List<Course> record) {
        try{
            int flag=courseMapper.insertBatch(record);
            if(flag!=0){
                return true;
            }
        }catch (Exception  e){
            e.printStackTrace();
            log.error("批量插入课程信息失败"+e.getMessage());
        }
        return false;
    }

    @Override
    public List<Course> selectCoursebyteacher(Integer teacherid, Pagination pagination) {
    //    PageHelper.startPage(pagination.getPageNum(),pagination.getNumPerPage());
        try {
            List<Course> courses=courseMapper.selectCoursebyteacher(teacherid);
            if(courses!=null){
                return courses;
            }
        }
        catch (Exception e){
            log.error("根据专家ID查询课程失败"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateByPrimaryKey(Course record) {
        int flag=courseMapper.updateByPrimaryKey(record);
        if(flag==1){
            return 1;
        }
        return 0;
    }

    @Override
    public List<Backgroudinfo> selectAll() {
        List<Backgroudinfo> backgroudinfoList=backgroudinfoMapper.selectAll();
        if(backgroudinfoList!=null)
            return backgroudinfoList;
        return null;
    }

    @Override
    public List<News> homeNews(Integer iscommend) {
        List<News> iscommendnews=newsMapper.homeNews(0);
        if(iscommendnews!=null&&iscommendnews.size()<3){
            List<News> news=newsMapper.homeNews(1);
            if(news!=null) {
                for (int i = 0; i < 3 - iscommendnews.size(); i++) {
                    iscommendnews.add(news.get(i));
                    if(i+1==news.size()){
                        break;
                    }
                }
            }
            else
                return null;
            return iscommendnews;
        }
        return null;
    }

    @Override
    public List<ExpertsVoinfo> homeExpert(ExpertsVoinfo record) {
        List<ExpertsVoinfo> expertsVoinfos=expertsinfoMapper.homeExpert(record);
        if(expertsVoinfos!=null)
            return expertsVoinfos;
        return null;
    }
}
