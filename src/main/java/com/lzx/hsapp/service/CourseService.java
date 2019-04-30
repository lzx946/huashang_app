package com.lzx.hsapp.service;

import com.lzx.hsapp.dto.*;
import com.lzx.hsapp.entity.CourseVo;
import com.lzx.hsapp.utils.Pagination;
import com.lzx.hsapp.utils.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by wangdaren on 2018/3/25.
 */
@Component
public interface CourseService {
    /**
     * 根据专家id和课程名查询不同地点的课程
     * @param courseVo
     * @return
     */
    List<CourseVo> selectByteach(CourseVo courseVo);
    /**
     * 查询所有的课程
     * @return
     */
    List<CourseVo> selectAll(CourseVo courseVo, Pagination pagination);

    Result<List<GetCourseByTeacherIdOutDto>> getCoursesByTeacherId(TeacherIdDto dto);

    Result<List<MyCourseListOutDto>> getMyCourseList(MyCourseListDto dto);

    Result<CourseDetailSingleDto> getCourseDetailSingle(CourseIdDto dto);

    Result<CourseTrackingDto> courseTrackingDetail(@RequestBody CourseIdDto dto);

    Result<List<FileDto>> uploadCourseFile(UploadCourseFileDto dto);
}
