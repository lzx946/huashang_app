package com.lzx.hsapp.controller;

import com.lzx.hsapp.dto.*;
import com.lzx.hsapp.service.CourseService;
import com.lzx.hsapp.utils.ActionUtil;
import com.lzx.hsapp.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("course")
@CrossOrigin(origins = "*")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/getCoursesByTeacherId",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<GetCourseByTeacherIdOutDto>> getCoursesByTeacherId(@RequestBody TeacherIdDto dto){

        LOGGER.info("根据教授ID查询课程，入参：{}",dto);

        return courseService.getCoursesByTeacherId(dto);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping(value = "/getMyCourseList",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<MyCourseListOutDto>> getMyCourseList(@RequestBody MyCourseListDto dto,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);

        LOGGER.info("我的课程，根据学生ID查询课程，入参：{}",dto);

        return courseService.getMyCourseList(dto);
    }

    @RequestMapping(value = "/getCourseDetailSingle",method = RequestMethod.POST)
    @ResponseBody
    public Result<CourseDetailSingleDto> getCourseDetailSingle(@RequestBody CourseIdDto dto){

        LOGGER.info("单个教学点课程详情，入参：{}",dto);

        return courseService.getCourseDetailSingle(dto);

    }

    @RequestMapping(value = "/courseTrackingDetail",method = RequestMethod.POST)
    @ResponseBody
    public Result<CourseTrackingDto> courseTrackingDetail(@RequestBody CourseIdDto dto){

        LOGGER.info("课程跟踪详情，入参：{}",dto);

        return courseService.courseTrackingDetail(dto);
    }

    @RequestMapping(value = "/uploadCourseFile",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<FileDto>> uploadCourseFile(@RequestBody UploadCourseFileDto dto){

        LOGGER.info("上传课程材料文件，入参：{}",dto);

        return courseService.uploadCourseFile(dto);
    }
}
