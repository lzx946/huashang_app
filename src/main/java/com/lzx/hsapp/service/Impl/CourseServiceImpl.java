package com.lzx.hsapp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lzx.hsapp.dao.*;
import com.lzx.hsapp.dto.*;
import com.lzx.hsapp.entity.*;
import com.lzx.hsapp.service.CourseService;
import com.lzx.hsapp.service.SysDictonaryService;
import com.lzx.hsapp.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by wangdaren on 2018/3/25.
 */
@Service
@Component
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Value("${preview.url}")
    private String preview;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private SysDictonaryService sysDictonaryService;

    @Autowired
    private EnlistMapper enlistMapper;

    @Autowired
    private ExpertsinfoMapper expertsinfoMapper;

    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private CourseAuditMapper courseAuditMapper;

    @Autowired
    private CourseFileMapper courseFileMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private StudentsinfoMapper studentsinfoMapper;

    @Override
    public List<CourseVo> selectByteach(CourseVo courseVo) {
        List<CourseVo> list=courseMapper.selectByteach(courseVo);
        if(list!=null)
            return list;
        return null;
    }

    @Override
    public List<CourseVo> selectAll(CourseVo courseVo1, Pagination pagination) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        PageHelper.startPage(pagination.getPageNum(),pagination.getNumPerPage());
        List<CourseVo> courseVoList=courseMapper.selectAll(courseVo1);
       /* List<CourseVo> courseVoList=new ArrayList<CourseVo>();
        if(courseVoList2==null)
            return null;
        for(int m=0;m<courseVoList2.size();m++){
            if(!courseVoList.contains(courseVoList2.get(m).getName())){
                courseVoList.add(courseVoList2.get(m));
            }
        }*/
       /* if(courseVoList2.size()<2){
            courseVoList=courseVoList2;
        }
        else {
            for (int m = 1; m < courseVoList2.size(); m++) {
                for (int k = 0; k < m; k++) {
                    if (courseVoList2.get(m).getName().equals(courseVoList2.get(k).getName())) {
                        courseVoList.add(courseVoList2.get(k));
                    }
                    else{
                        courseVoList.add(courseVoList2.get(m));
                    }
                }
            }
        }*/
        if(courseVoList!=null){
            for(int i=0;i<courseVoList.size();i++){
                courseVoList.get(i).setUrl(ActionUtil.ROOTURL+courseVoList.get(i).getUrl());
                CourseVo courseVo=new CourseVo();
                courseVo.setTeacherid(courseVoList.get(i).getTeacherid());
                courseVo.setName(courseVoList.get(i).getName());
                List<CourseVo> courses=courseMapper.selectByteach(courseVo);
                if(courses!=null) {
                    List<Date> list=new ArrayList<Date>();
                    for(int j=0;j<courses.size();j++){
                        String begindate=sdf.format(courses.get(j).getStarttime());
                        String enddate=sdf.format(courses.get(j).getStoptime());
                        courses.get(j).setTime(begindate.substring(0,4)+"年"+begindate.substring(5,7)+"月"
                        +begindate.substring(8,10)+"日"+"-"+enddate.substring(5,7)+"月"+enddate.substring(8,10)+"日");
                        try {
                            Long totaltime=(sdf.parse(enddate)).getTime();
                            courses.get(j).setEndtime(totaltime);
                        }
                       catch (Exception e){
                            e.printStackTrace();
                       }
                        list.add(courses.get(j).getStarttime());
                        list.add(courses.get(j).getStoptime());
                    }
                    Date max=list.get(0);
                    Date min=list.get(0);
                    for(int k=0;k<list.size();k++){
                        if(max.getTime()<list.get(k).getTime()){
                            max=list.get(k);
                        }
                        if(min.getTime()>list.get(k).getTime()){
                            min=list.get(k);
                        }
                    }
                    String totalbegin=sdf.format(min).substring(0,4)+"年"+sdf.format(min).substring(5,7)+"月"+sdf.format(min).substring(8,10)+"日";
                    String totalend=sdf.format(max).substring(0,4)+"年"+sdf.format(max).substring(5,7)+"月"+sdf.format(max).substring(8,10)+"日";;
                    courseVoList.get(i).setTotalbegin(totalbegin);
                    courseVoList.get(i).setTotalend(totalend);
                    courseVoList.get(i).setCourses(courses);
                }
            }
            return courseVoList;
        }
        return null;
    }
/****************************************************************/


    @Override
    public boolean createCourseAuditByCourseName(String courseName){
        if (StringUtils.isEmpty(courseName)){
            return false;
        }

        List<Integer> courseIdList = courseMapper.findIdsByName(courseName);

        if (!courseIdList.isEmpty()){

            CourseAudit courseAudit = new CourseAudit();
            courseAudit.setCourseIds(Transform.listIntegerToString(courseIdList));
            courseAudit.setFirstAudit("0");
            courseAudit.setStatus("0");
            courseAudit.setCourseFile("0");
            courseAudit.setCreateTime(new Date());

            courseAuditMapper.insert(courseAudit);

            LOGGER.info("新建课程考核表");
        }
        return true;
    }

    @Override
    public Result<List<GetCourseByTeacherIdOutDto>> getCoursesByTeacherId(TeacherIdDto dto){

        if (dto.getTeacherId() == null){
            LOGGER.info("teacherId为空");
            return Result.fail(ErrorCode.PARAMETER_CHECK_ERROR);
        }

        PageUtil page = PageUtil.defaultPage(dto.getPageNum(),dto.getPageSize());
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Course> courses = courseMapper.findByTeacherId(dto.getTeacherId());
        if(courses.isEmpty()){

            LOGGER.info("无数据");

            return Result.success("无满足条件的数据",Collections.EMPTY_LIST);
        }
        PageInfo<Course> coursePageInfo = new PageInfo<>(courses);
        List<Course> courseList = coursePageInfo.getList();

        if(courseList.isEmpty()){
            LOGGER.info("无数据");
            return Result.success("无满足条件的数据",Collections.EMPTY_LIST);
        }

        List<GetCourseByTeacherIdOutDto> getCourseByTeacherIdOutDtoList = new ArrayList<>();
        for (Course course : courseList
             ) {
            GetCourseByTeacherIdOutDto getCourseByTeacherIdOutDto = new GetCourseByTeacherIdOutDto();
            getCourseByTeacherIdOutDto.setId(course.getId());
            getCourseByTeacherIdOutDto.setName(course.getName());
            getCourseByTeacherIdOutDto.setPeriod(course.getPeriod());
            getCourseByTeacherIdOutDto.setSummary(course.getSummary());

            //课程审核状态
            CourseAudit courseAudit = courseAuditMapper.findByCourseId(String.valueOf(course.getId()));

            if (courseAudit != null){
                getCourseByTeacherIdOutDto.setStatus(courseAudit.getStatus());
            }else {
                getCourseByTeacherIdOutDto.setStatus("");
            }

            List<Course> courses1 = courseMapper.findByTeacherIdAndNameAndPeriod(dto.getTeacherId(),course.getName(),course.getPeriod());

            LOGGER.info("course1:{}",courses1);

            List<TeachPointDto> teachPointDtoList = new ArrayList<>();
            Date totalStartTime = course.getStarttime();
            Date totalStopTime = course.getStoptime();
            for (Course newCourse : courses1                                    //一期课有多个教学点
                 ) {
                TeachPointDto teachPointDto = new TeachPointDto();
                teachPointDto.setRoom(newCourse.getRoom());
                teachPointDto.setStartTime(newCourse.getStarttime());
                teachPointDto.setStopTime(newCourse.getStoptime());
                teachPointDto.setState(newCourse.getState());
                SysDictionary sysDictionary = sysDictonaryService.getByCodeFlag(Integer.valueOf(newCourse.getClassroom()));
                if (sysDictionary != null){                                                                             //查找教学点
                    teachPointDto.setCodeFlag(sysDictionary.getCodeflag());
                    teachPointDto.setCodeFlagName(sysDictionary.getCodeflagname());
                }
                if (totalStartTime.after(newCourse.getStarttime())){            //最早的开始时间为总的开课时间
                    totalStartTime = newCourse.getStarttime();
                }
                if (totalStopTime.before(newCourse.getStoptime())){         //最晚的结束时间为总的结课时间
                    totalStopTime = newCourse.getStoptime();
                }

                teachPointDtoList.add(teachPointDto);
            }

            getCourseByTeacherIdOutDto.setTeachPointList(teachPointDtoList);

            LOGGER.info("最早的开始时间为总的开课时间:{}",totalStartTime);

            LOGGER.info("最晚的结束时间为总的结课时间:{}",totalStopTime);

            getCourseByTeacherIdOutDto.setTotalStartTime(totalStartTime);
            getCourseByTeacherIdOutDto.setTotalStopTime(totalStopTime);

            getCourseByTeacherIdOutDtoList.add(getCourseByTeacherIdOutDto);

        }

        LOGGER.info("根据教授ID查询课程：{}",getCourseByTeacherIdOutDtoList);

        return Result.success("success",getCourseByTeacherIdOutDtoList,coursePageInfo.getTotal(),page.getPageNum(),page.getPageSize());

    }

    @Override
    public Result<List<MyCourseListOutDto>> getMyCourseList(MyCourseListDto dto){

        if (dto.getStudentId() == null){
            LOGGER.info("studentId为空");
            return Result.result("NACK","studentId为空" );
        }
        List<MyCourseListOutDto> myCourseListOutDtoList = new ArrayList<>();

        PageUtil page = PageUtil.defaultPage(dto.getPageNum(),dto.getPageSize());
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Enlist> enlists = enlistMapper.findByStudentId(dto.getStudentId());

        PageInfo<Enlist> enlistPageInfo = new PageInfo<>(enlists);
        List<Enlist> enlistList = enlistPageInfo.getList();

        if (!enlistList.isEmpty()){
            for (Enlist enlist : enlistList
                 ) {
                MyCourseListOutDto myCourseListOutDto = new MyCourseListOutDto();

                myCourseListOutDto.setEnlistId(enlist.getId());

                Course course = courseMapper.findById(enlist.getCid());

                if (course != null){
                    myCourseListOutDto.setCourseId(course.getId());
                    myCourseListOutDto.setName(course.getName());
                    myCourseListOutDto.setPeriod(course.getPeriod());
                    SysDictionary sysDictionary = sysDictonaryService.getByCodeFlag(Integer.valueOf(course.getClassroom()));
                    if (sysDictionary != null){
                        myCourseListOutDto.setCodeFlag(sysDictionary.getCodeflag());
                        myCourseListOutDto.setCodeFlagName(sysDictionary.getCodeflagname());
                    }
                    myCourseListOutDto.setRoom(course.getRoom());
                    myCourseListOutDto.setStartTime(course.getStarttime());
                    myCourseListOutDto.setStopTime(course.getStoptime());

                    Date date = new Date();
                    if (date.before(course.getStarttime())){
                        myCourseListOutDto.setStatus("0");      //0==未开课
                    }else if (course.getStarttime().before(date) && date.before(course.getStoptime())){
                        myCourseListOutDto.setStatus("1");          //1==已开课
                    }else {
                        myCourseListOutDto.setStatus("2");          //2==已结束
                    }

                    if(enlist.getResult().equals(1)){
                        myCourseListOutDto.setStatus("3");      //3==已退报
                    }

                    Expertsinfo teacher = expertsinfoMapper.findById(course.getTeacherid());

                    if (teacher != null){
                        myCourseListOutDto.setPhotoId(teacher.getPhotoid());

                        SysFile sysFile = sysFileMapper.selectByPrimaryKey(teacher.getPhotoid());

                        if (sysFile != null){
                            myCourseListOutDto.setPhotoUrl(preview + sysFile.getUrl());
                        }
                        myCourseListOutDto.setEmployer(teacher.getEmployer());
                        myCourseListOutDto.setRealName(teacher.getRealname());
                        myCourseListOutDto.setAcademic(teacher.getAcademic());
                    }

                }

                myCourseListOutDtoList.add(myCourseListOutDto);
            }

            LOGGER.info("查询我的课程列表成功：{}",myCourseListOutDtoList);
            return Result.success("success",myCourseListOutDtoList,enlistPageInfo.getTotal(),page.getPageNum(),page.getPageSize());
        }

        LOGGER.info("无数据");

        return Result.success("无满足条件的数据",Collections.EMPTY_LIST);

    }

    @Override
    public Result<CourseDetailSingleDto> getCourseDetailSingle(CourseIdDto dto){
        if (dto.getCourseId().equals(null)){
            LOGGER.info("courseId为空");
            return Result.result("NACK","courseId为空");
        }
        CourseDetailSingleDto courseDetailSingleDto = new CourseDetailSingleDto();
        Course course = courseMapper.findById(dto.getCourseId());
        if (course != null){

            courseDetailSingleDto.setId(course.getId());
            courseDetailSingleDto.setName(course.getName());
            courseDetailSingleDto.setCreateTime(course.getCreatetime());
            courseDetailSingleDto.setPeriod(course.getPeriod());
            courseDetailSingleDto.setRoom(course.getRoom());
            courseDetailSingleDto.setStartTime(course.getStarttime());
            courseDetailSingleDto.setStopTime(course.getStoptime());
            courseDetailSingleDto.setSummary(course.getSummary());
            courseDetailSingleDto.setTeacherId(course.getTeacherid());

            SysDictionary sysDictionary = sysDictonaryService.getByCodeFlag(Integer.valueOf(course.getClassroom()));

            if (sysDictionary != null){
                courseDetailSingleDto.setCodeFlagName(sysDictionary.getCodeflagname());
            }

            Expertsinfo teacher = expertsinfoMapper.findById(course.getTeacherid());
            if (teacher != null){
                courseDetailSingleDto.setAcademic(teacher.getAcademic());
                courseDetailSingleDto.setEmployer(teacher.getEmployer());
                courseDetailSingleDto.setRealName(teacher.getRealname());
                SysFile sysFile = sysFileMapper.selectByPrimaryKey(teacher.getPhotoid());
                if (sysFile != null){
                    courseDetailSingleDto.setUrl(preview + sysFile.getUrl());
                }
            }
            LOGGER.info("查询我的课程详情成功：{}",courseDetailSingleDto);
            return Result.success("ACK",courseDetailSingleDto);
        }
        LOGGER.info("无数据");
        return Result.result("ACK","无数据");
    }

    @Override
    public Result<CourseTrackingDto> courseTrackingDetail(CourseIdDto dto){
        if (dto.getCourseId() != null){
            Course course = courseMapper.findById(dto.getCourseId());
            if (course == null){
                LOGGER.info("查无此课程");
                return Result.result("NACK","查无此课程");
            }
            CourseTrackingDto courseTrackingDto = new CourseTrackingDto();

            CourseAudit courseAudit = courseAuditMapper.findByCourseId(String.valueOf(course.getId()));

            if (courseAudit != null){
                courseTrackingDto.setStatus(courseAudit.getStatus());
                courseTrackingDto.setApplyTime(courseAudit.getCreateTime());        //申请开课时间
                courseTrackingDto.setAuditMessage("您好！您申请的"+ course.getName() +"课程有新动态，请前往行程跟踪接受邀请。");
                courseTrackingDto.setAuditTime(new Date());
                courseTrackingDto.setAuditor("admin");
                if (courseAudit.getFirstAuditTime() != null && Integer.valueOf(courseAudit.getStatus()) >= 1){
                    courseTrackingDto.setAuditSuccessTime(courseAudit.getFirstAuditTime());        //初步审核时间
                    courseTrackingDto.setAuditMessage("您好！您申请的\""+ course.getName() +"\"课程已经通过初步审核，请尽快上传课程材料、办理签证、机票等相关手续，完成后续工作。");
                    courseTrackingDto.setAuditTime(courseAudit.getFirstAuditTime());
                    courseTrackingDto.setAuditor("admin");
                }
                if(courseAudit.getUploadTime() != null && Integer.valueOf(courseAudit.getStatus()) >= 2){
                    courseTrackingDto.setUploadTime(courseAudit.getUploadTime());       //上传材料成功时间
                }
                if (courseAudit.getStartCourseTime() != null && Integer.valueOf(courseAudit.getStatus()) >= 3){
                    courseTrackingDto.setCourseStartTime(courseAudit.getStartCourseTime());         //开课时间
                }
                if (courseAudit.getStopCourseTime() != null && Integer.valueOf(courseAudit.getStatus()) >= 4){
                    courseTrackingDto.setCourseStopTime(courseAudit.getStopCourseTime());           //结课时间
                }
            }
            //显示课程详细信息
            courseTrackingDto.setCourseId(course.getId());
            courseTrackingDto.setCourseName(course.getName());
            courseTrackingDto.setPeriod(course.getPeriod());
            courseTrackingDto.setSummary(course.getSummary());
            Expertsinfo teacher = expertsinfoMapper.findById(course.getTeacherid());
            if (teacher != null){
                courseTrackingDto.setTeacherId(teacher.getId());
                courseTrackingDto.setRealName(teacher.getRealname());
                courseTrackingDto.setAcademic(teacher.getAcademic());
                courseTrackingDto.setEmployer(teacher.getEmployer());
                courseTrackingDto.setPhotoId(teacher.getPhotoid());
                SysFile sysFile = sysFileMapper.selectByPrimaryKey(teacher.getPhotoid());

                if (sysFile != null){
                    courseTrackingDto.setPhotoUrl(preview + sysFile.getUrl());
                }
            }

            //教学点列表
            List<String> courseIdList = new ArrayList<>();      //课程ID列表

            Date totalBegin = course.getStarttime();
            Date totalEnd = course.getStoptime();

            List<TeachPointDto> teachPointDtoList = new ArrayList<>();
            List<Course> courseList = courseMapper.findByName(course.getName());
            for (Course currentCourse : courseList
                 ) {

                if (currentCourse.getStarttime().before(totalBegin)){
                    totalBegin = currentCourse.getStarttime();
                }
                if (currentCourse.getStoptime().after(totalEnd)){
                    totalEnd = currentCourse.getStoptime();
                }

                courseIdList.add(String.valueOf(currentCourse.getId()));        //把所有教学点的课程ID存入List

                TeachPointDto teachPointDto = new TeachPointDto();
                teachPointDto.setCourseId(currentCourse.getId());
                teachPointDto.setState(currentCourse.getState());
                teachPointDto.setStartTime(currentCourse.getStarttime());
                teachPointDto.setStopTime(currentCourse.getStoptime());
                teachPointDto.setRoom(currentCourse.getRoom());
                SysDictionary sysDictionary = sysDictonaryService.getByCodeFlag(Integer.valueOf(currentCourse.getClassroom()));
                if (sysDictionary != null){
                    teachPointDto.setCodeFlag(sysDictionary.getCodeflag());
                    teachPointDto.setCodeFlagName(sysDictionary.getCodeflagname());
                }

                teachPointDtoList.add(teachPointDto);
            }

            courseTrackingDto.setTotalBegin(totalBegin);
            courseTrackingDto.setTotalEnd(totalEnd);

            courseTrackingDto.setTeachPointList(teachPointDtoList);

            //文件材料
            List<CourseFile> courseFileList = courseFileMapper.findByCourseId(String.valueOf(course.getId()));
            if (courseFileList.isEmpty()){
                courseTrackingDto.setFileList(Collections.EMPTY_LIST);
            }else {
                List<FileDto> fileDtoList = new ArrayList<>();

                int i = 1;
                for (CourseFile courseFile : courseFileList
                     ) {
                    FileDto fileDto = new FileDto();
                    SysFile sysFile = sysFileMapper.selectByPrimaryKey(courseFile.getFileId());
                    if (sysFile != null){
                        fileDto.setFileId(sysFile.getId());
                        fileDto.setFileName("课程文件 《 " + i + " 》");
                        fileDto.setFileUrl(preview + sysFile.getUrl());

                        fileDtoList.add(fileDto);
                    }
                }
                courseTrackingDto.setFileList(fileDtoList);
            }

            //评论列表
            List<Comment> commentList = commentMapper.findByIds(Transform.listToString(courseIdList));
            List<SimpleCommentDto> commentDtoList = new ArrayList<>();
            if (commentList == null){
                commentDtoList = Collections.EMPTY_LIST;
            }else {
                for (Comment comment : commentList
                     ) {
                    SimpleCommentDto simpleCommentDto = new SimpleCommentDto();
                    simpleCommentDto.setContent(comment.getContent());
                    simpleCommentDto.setCommentTime(comment.getCreatetime());
                    Studentsinfo studentsinfo = studentsinfoMapper.findById(comment.getUid());
                    if (studentsinfo != null){
                        simpleCommentDto.setStudentId(studentsinfo.getId());
                        simpleCommentDto.setRealName(studentsinfo.getRealname());
                        SysFile sysFile = sysFileMapper.selectByPrimaryKey(studentsinfo.getPhotoid());
                        if (sysFile != null){
                            simpleCommentDto.setPhotoUrl(preview + sysFile.getUrl());
                        }
                    }
                    commentDtoList.add(simpleCommentDto);
                }

            }

            courseTrackingDto.setCommentList(commentDtoList);

            LOGGER.info("课程跟踪详情，结果：{}",courseTrackingDto);

            return Result.success("success",courseTrackingDto);

        }
        LOGGER.info("courseId为空");
        return Result.result("NACK","courseId为空");
    }

    @Override
    public Result<List<FileDto>> uploadCourseFile(UploadCourseFileDto dto){
        if (dto.getCourseId() == null){
            LOGGER.info("courseId为空");
            return Result.result("NACK","courseId为空");
        }

        if (dto.getFileIdList().isEmpty()){
            LOGGER.info("fileIdList为空");
            return Result.result("ACK","fileIdList为空");
        }
        List<String> courseIdList = new ArrayList<>();
        Course course = courseMapper.findById(dto.getCourseId());
        if (course != null){

            List<Course> courseList = courseMapper.findByName(course.getName());
            for (Course currentCourse : courseList
            ) {
                courseIdList.add(String.valueOf(currentCourse.getId()));
            }
        }else {
            LOGGER.info("无此课程");
            return Result.result("ACK","无此课程");
        }

        for (Integer fileId : dto.getFileIdList()
             ) {
            CourseFile courseFile = new CourseFile();
            courseFile.setCourseIds(Transform.listToString(courseIdList));
            courseFile.setFileId(fileId);
            courseFile.setCreateTime(new Date());

            courseFileMapper.insert(courseFile);

            LOGGER.info("添加courseFile：{}",courseFile);
        }
        List<CourseFile> courseFileList = courseFileMapper.findByCourseId(String.valueOf(course.getId()));
        if (courseFileList != null){
            List<FileDto> fileDtoList = new ArrayList<>();
            int i = 1;
            for (CourseFile courseFile : courseFileList
                 ) {
                SysFile sysFile = sysFileMapper.selectByPrimaryKey(courseFile.getFileId());
                if (sysFile != null){
                    FileDto fileDto = new FileDto();

                    fileDto.setFileId(sysFile.getId());
                    fileDto.setFileName("课程文件 《 " + i + " 》");
                    fileDto.setFileUrl(preview + sysFile.getUrl());

                    fileDtoList.add(fileDto);
                }
            }
            LOGGER.info("上传成功：{}",fileDtoList);
            return Result.result("ACK","上传成功",fileDtoList);
        }

        LOGGER.info("上传成功");
        return Result.result("ACK","上传成功",Collections.EMPTY_LIST);

    }

}
