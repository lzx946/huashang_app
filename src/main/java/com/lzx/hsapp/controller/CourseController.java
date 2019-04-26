package com.lzx.hsapp.controller;

import com.github.pagehelper.PageInfo;
import com.lzx.hsapp.dto.GetCourseByTeacherIdInDto;
import com.lzx.hsapp.dto.GetCourseByTeacherIdOutDto;
import com.lzx.hsapp.dto.MyCourseListDto;
import com.lzx.hsapp.dto.MyCourseListOutDto;
import com.lzx.hsapp.entity.CourseVo;
import com.lzx.hsapp.service.CourseService;
import com.lzx.hsapp.utils.ActionUtil;
import com.lzx.hsapp.utils.Pagination;
import com.lzx.hsapp.utils.Result;
import com.lzx.hsapp.utils.webUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/getCoursesByTeacherId",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<GetCourseByTeacherIdOutDto>> getCoursesByTeacherId(@RequestBody GetCourseByTeacherIdInDto dto){

        LOGGER.info("根据教授ID查询课程，入参：{}",dto);

        return courseService.getCoursesByTeacherId(dto);
    }

    @RequestMapping(value = "/getMyCourseList",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<MyCourseListOutDto>> getMyCourseList(@RequestBody MyCourseListDto dto){

        LOGGER.info("我的课程，根据学生ID查询课程，入参：{}",dto);

        return courseService.getMyCourseList(dto);
    }
}
