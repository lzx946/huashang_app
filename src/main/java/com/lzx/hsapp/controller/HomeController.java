package com.lzx.hsapp.controller;

import com.github.pagehelper.PageInfo;
import com.lzx.hsapp.entity.*;
import com.lzx.hsapp.service.CourseService;
import com.lzx.hsapp.service.HomeDataService;
import com.lzx.hsapp.service.TripService;
import com.lzx.hsapp.utils.ActionUtil;
import com.lzx.hsapp.utils.DateUtils;
import com.lzx.hsapp.utils.Pagination;
import com.lzx.hsapp.utils.webUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {

    Logger logger= LoggerFactory.getLogger(getClass().getName());

    @Autowired
    HomeDataService homeDataService ;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TripService tripService;

    /**
     * 获取首页Poster数据
     * @param response
     * @return
     */
    @RequestMapping("/homeData")
    @ResponseBody
    public List<HomeData> homeData(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        List<HomeData> list =homeDataService.selectHomeData();
        return list;
    }
    /**
     * 获取首页Poster数据
     * @param response
     * @return
     */
    @RequestMapping("/homeDataList")
    @ResponseBody
    public List<HomeData> homeDataList(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        List<HomeData> list =homeDataService.selectHomeDataList();
        return list;
    }
    @RequestMapping("homeNews")
    @ResponseBody
    public String homeNews(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        List<News> newsList=homeDataService.homeNews(0);
        if(newsList!=null) {
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "首页新闻获取成功",newsList);
        }
        else
        {
            return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "首页新闻获取失败",null);
        }
    }

    /**
     * 根据课程id获取Poster数据详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/homeDataDetail" )
    public HomeDataDetail homeDataDetail(HttpServletResponse response, Integer id){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if(id==null){
            return null;
        }
        HomeDataDetail homeData =homeDataService.selectHomeDataDetail(id);
        return homeData;
    }
    @RequestMapping("/insertCourse")
    public String insertCourse(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin",ActionUtil.CrossDomain);
        String name=request.getParameter("courseName");
        String summary=request.getParameter("courseIntroduction");
        String teacherid=request.getParameter("teacherid");
        String info=request.getParameter("timeLocals");
        List<Course> courses=new ArrayList<Course>();
        JSONArray jsonArray = JSONArray.fromObject(info);
        for(int i=0;i<jsonArray.size();i++){
            Course course=new Course();
            course.setName(name);
            course.setSummary(summary);
            course.setTeacherid(Integer.parseInt(teacherid));
            course.setContent(999);
            course.setClassroom(((JSONObject)jsonArray.get(i)).getString("ban"));
            course.setRoom(((JSONObject)jsonArray.get(i)).getString("local"));
            String starttime=((JSONObject)jsonArray.get(i)).getString("timeStart").replace("/","-");
            String stoptime=((JSONObject)jsonArray.get(i)).getString("timeStop").replace("/","-");
            course.setStarttime(DateUtils.getFormatDate(starttime,DateUtils.yyyyMMdd));
            course.setStoptime(DateUtils.getFormatDate(stoptime,DateUtils.yyyyMMdd));
            course.setCreatetime(new Date());
            course.setState(0);//待审核
            courses.add(course);
       }
       try{
           boolean flag= homeDataService.insertBatch(courses);
           boolean createCourseAudit = courseService.createCourseAuditByCourseName(name);
           boolean createTrip = tripService.createTripByCourseName(name);
           if(flag==true && createCourseAudit==true && createTrip==true){

               return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "课程提交成功", flag);
           }
       }
       catch (Exception  e){
            e.printStackTrace();
       }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "课程提交失败", null);
    }
    @RequestMapping("/updateCourse")
    public String updateCourse(HttpServletRequest request, HttpServletResponse response, Course course) {
        response.setHeader("Access-Control-Allow-Origin",ActionUtil.CrossDomain);
        int flag=homeDataService.updateByPrimaryKey(course);
        if(flag==1){
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "课程更新成功", flag);
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "课程更新失败", null);
    }

    @RequestMapping("/homeBackfroud")
    public String getBackfroud(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin",ActionUtil.CrossDomain);
        List<Backgroudinfo> backgroudinfoList=homeDataService.selectAll();
        if(backgroudinfoList.size()==1){
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "背景图片获取成功",ActionUtil.ROOTURL+backgroudinfoList.get(0).getUrl());
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "背景图片获取失败", null);
    }

    @RequestMapping("/homeExpert")
    public String homeExpert(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin",ActionUtil.CrossDomain);
        ExpertsVoinfo expertsVoinfo=new ExpertsVoinfo();
        expertsVoinfo.setIscommend(0);
        List<ExpertsVoinfo> expertsinfos=homeDataService.homeExpert(expertsVoinfo);
        if(expertsinfos!=null){
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "专家信息获取成功",expertsinfos);
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "专家信息获取失败", null);
    }

    @RequestMapping("/getCourse")
    public String getCourse(HttpServletRequest request, HttpServletResponse response, Pagination pagn) {
        response.setHeader("Access-Control-Allow-Origin",ActionUtil.CrossDomain);
        CourseVo courseVo=new CourseVo();
        List<CourseVo> courseVoList=courseService.selectAll(courseVo,pagn);
        PageInfo<CourseVo> page=new PageInfo<CourseVo>(courseVoList);
        if(courseVoList!=null){
            return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "课程信息获取成功",courseVoList,page.getTotal());
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "课程信息获取失败", null);
    }
    @RequestMapping("/getCourseDetail")
    public String getCourse(HttpServletRequest request, HttpServletResponse response, CourseVo courseVo, Pagination pagn) {
        response.setHeader("Access-Control-Allow-Origin",ActionUtil.CrossDomain);
        List<CourseVo> courseVoList=courseService.selectAll(courseVo,pagn);
        if(courseVoList!=null){
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "课程信息获取成功",courseVoList);
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "课程信息获取失败", null);
    }
}
