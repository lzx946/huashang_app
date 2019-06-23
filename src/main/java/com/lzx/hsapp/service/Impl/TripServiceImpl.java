package com.lzx.hsapp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzx.hsapp.dao.*;
import com.lzx.hsapp.dto.*;
import com.lzx.hsapp.entity.*;
import com.lzx.hsapp.service.SysDictonaryService;
import com.lzx.hsapp.service.TripService;
import com.lzx.hsapp.utils.PageUtil;
import com.lzx.hsapp.utils.Result;
import com.lzx.hsapp.utils.Transform;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Component
public class TripServiceImpl implements TripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripServiceImpl.class);

    @Value("${preview.url}")
    private String preview;

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private SysDictonaryService sysDictonaryService;

    @Autowired
    private CourseAuditMapper courseAuditMapper;

    @Autowired
    private InvitationMapper invitationMapper;

    @Autowired
    private BoardAndLodgingMapper boardAndLodgingMapper;

    @Autowired
    private ProcedureMapper procedureMapper;

    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private ExpertsinfoMapper expertsinfoMapper;

    @Override
    public boolean createTripByCourseName(String courseName,String period){

        if (StringUtils.isEmpty(courseName)){
            return false;
        }

        List<Integer> courseIdList = courseMapper.findIdsByNameAndPeriod(courseName,period);

        if (!courseIdList.isEmpty()){

            Course course = courseMapper.findById(courseIdList.get(0));

            if (course != null){
                Trip trip = new Trip();

                trip.setCourseIds(Transform.listIntegerToString(courseIdList));
                trip.setTeacherId(course.getTeacherid());
                trip.setStatus("0");
                trip.setBoardAndLodging("0");
                trip.setAgree("0");
                trip.setProcedure("0");
                trip.setCreateTime(new Date());

                tripMapper.insert(trip);

                LOGGER.info("新建行程表：{}",trip);
            }
        }

        return true;

    }

    @Override
    public Result<List<TripCardDto>> getTripTrackingList(TeacherIdDto dto){
        if (dto.getTeacherId() == null){
            LOGGER.info("teacherId为空");
            return Result.result("NACK","teacherId为空");
        }

        PageUtil page = PageUtil.defaultPage(dto.getPageNum(),dto.getPageSize());
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Trip> trips = tripMapper.findByTeacherId(dto.getTeacherId());

        if(trips.isEmpty()){

            LOGGER.info("无数据");

            return Result.success("无满足条件的数据", Collections.EMPTY_LIST);
        }
        PageInfo<Trip> tripPageInfo = new PageInfo<>(trips);
        List<Trip> tripList = tripPageInfo.getList();

        if (tripList.isEmpty()){
            LOGGER.info("无数据");

            return Result.success("无满足条件的数据", Collections.EMPTY_LIST);
        }

        List<TripCardDto> tripCardDtoList = new ArrayList<>();
        for (Trip trip : tripList
             ) {
            TripCardDto tripCardDto = new TripCardDto();
            tripCardDto.setTripId(trip.getId());
            tripCardDto.setStatus(trip.getStatus());
            List<Course> courseList = courseMapper.findInIds(trip.getCourseIds());
            if (!courseList.isEmpty()){
                Date totalBegin = trip.getCreateTime();
                Date totalEnd = trip.getCreateTime();
                List<TeachPointDto> teachPointDtoList = new ArrayList<>();
                for (Course course : courseList
                     ) {

                    if (course.getStarttime().before(totalBegin)){
                        totalBegin = course.getStarttime();             //最早的时间为总开课时间
                    }
                    if (course.getStoptime().after(totalEnd)){
                        totalEnd = course.getStoptime();                //最晚的时间为总结课时间
                    }

                    tripCardDto.setName(course.getName());
                    tripCardDto.setPeriod(course.getPeriod());
                    tripCardDto.setSummary(course.getSummary());

                    TeachPointDto teachPointDto = new TeachPointDto();
                    teachPointDto.setCourseId(course.getId());
                    teachPointDto.setState(course.getState());
                    teachPointDto.setStartTime(course.getStarttime());
                    teachPointDto.setStopTime(course.getStoptime());
                    teachPointDto.setRoom(course.getRoom());

                    SysDictionary sysDictionary = sysDictonaryService.getByCodeFlag(Integer.valueOf(course.getClassroom()));
                    if (sysDictionary != null){
                        teachPointDto.setCodeFlag(sysDictionary.getCodeflag());
                        teachPointDto.setCodeFlagName(sysDictionary.getCodeflagname());
                    }

                    teachPointDtoList.add(teachPointDto);
                }

                tripCardDto.setTotalStartTime(totalBegin);
                tripCardDto.setTotalStopTime(totalEnd);
                tripCardDto.setTeachPointList(teachPointDtoList);
            }
            tripCardDtoList.add(tripCardDto);
        }
        LOGGER.info("查询行程跟踪列表成功：{}",tripCardDtoList);
        return Result.result("ACK","success",tripCardDtoList,tripPageInfo.getTotal(),page.getPageNum(),page.getPageSize());
    }

    @Override
    public Result<TripTrackingDto> tripTrackingDetail(TripIdDto dto){
        if (dto.getTripId() == null){
            LOGGER.info("tripId为空");
            return Result.result("NACK","tripId为空");
        }

        Trip trip = tripMapper.findById(dto.getTripId());

        if (trip == null){
            LOGGER.info("无数据");
            return Result.result("ACK","无数据");
        }
        TripTrackingDto tripTrackingDto = new TripTrackingDto();
        tripTrackingDto.setTripId(trip.getId());            //tripID

        TripProgressDto tripProgressDto = new TripProgressDto();        //行程进度
        tripProgressDto.setStatus(trip.getStatus());        //进度状态
        tripProgressDto.setWaitInvitationTime(trip.getCreateTime());        //等待邀请时间

        Course course = courseMapper.findByIds(trip.getCourseIds());
        if (course == null){
            LOGGER.info("无数据");
            return Result.result("ACK","无数据");
        }

        //课程详细信息
        CourseDetailDto courseDetailDto = new CourseDetailDto();

        courseDetailDto.setCourseId(course.getId());
        courseDetailDto.setCourseName(course.getName());
        courseDetailDto.setPeriod(course.getPeriod());
        courseDetailDto.setSummary(course.getSummary());

        //教授信息
        Expertsinfo teacher = expertsinfoMapper.findById(course.getTeacherid());
        if (teacher != null){
            courseDetailDto.setTeacherId(teacher.getId());
            courseDetailDto.setEmployer(teacher.getEmployer());
            courseDetailDto.setRealName(teacher.getRealname());
            courseDetailDto.setAcademic(teacher.getAcademic());
            SysFile sysFile = sysFileMapper.selectByPrimaryKey(teacher.getPhotoid());
            if (sysFile != null){
                courseDetailDto.setPhotoId(sysFile.getId());
                courseDetailDto.setPhotoUrl(preview + sysFile.getUrl());
            }
        }

        //教学点列表
        List<String> courseIdList = new ArrayList<>();      //课程ID列表

        Date totalBegin = course.getStarttime();
        Date totalEnd = course.getStoptime();

        List<TeachPointDto> teachPointDtoList = new ArrayList<>();
        List<Course> courseList = courseMapper.findByNameAndPeriod(course.getName(),course.getPeriod());
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
        courseDetailDto.setTotalBegin(totalBegin);
        courseDetailDto.setTotalEnd(totalEnd);
        courseDetailDto.setTeachPointList(teachPointDtoList);

        tripTrackingDto.setCourseDetail(courseDetailDto);

        CourseAudit courseAudit = courseAuditMapper.findByCourseId("'" + String.valueOf(course.getId()) + "'");

        LOGGER.info("考核：{}",courseAudit);
        if (courseAudit != null){
            if (Integer.valueOf(trip.getStatus()) >= 0 && Integer.valueOf(courseAudit.getStatus()) >= 0){       //更改
                //邀请表

                List<Invitation> invitationList = invitationMapper.findByCourseIdsAndAgree(trip.getCourseIds(),"2");
                LOGGER.info("invitationList:{}",invitationList);
                if (!invitationList.isEmpty()){
                    List<InvitationDto> invitationDtoList = new ArrayList<>();
                    InvitationDto invitationDto = new InvitationDto();
                    List<TripDetailDto> tripDetailDtoList = new ArrayList<>();
                    for (Invitation invitation : invitationList
                    ) {
                        TripDetailDto tripDetailDto = new TripDetailDto();
                        tripDetailDto.setId(invitation.getId());        //邀请表ID
                        tripDetailDto.setCity(invitation.getCity());
                        tripDetailDto.setPlace(invitation.getPlace());
                        tripDetailDto.setStartTime(invitation.getStartTime());
                        tripDetailDto.setStopTime(invitation.getStopTime());

                        tripDetailDtoList.add(tripDetailDto);


                    }
                    invitationDto.setInviteList(tripDetailDtoList);

                    invitationDtoList.add(invitationDto);
                    tripTrackingDto.setInvitationList(invitationDtoList);
                    tripTrackingDto.setInvitationAgree("2");
                    tripTrackingDto.setInvitationMessage("您的更改已提交审核，请耐心等待。");
                }
            }
            if (Integer.valueOf(trip.getStatus()) >= 0 && Integer.valueOf(courseAudit.getStatus()) >= 1){       //申请课程，通过初审


                List<Invitation> invitationList1 = invitationMapper.findByCourseIdsAndAgree(trip.getCourseIds(),"0");      //审核通过
                if (!invitationList1.isEmpty()){
                    List<InvitationDto> invitationDtoList = new ArrayList<>();
                    InvitationDto invitationDto = new InvitationDto();
                    List<TripDetailDto> tripDetailDtoList = new ArrayList<>();
                    for (Invitation invitation : invitationList1
                    ) {
                        TripDetailDto tripDetailDto = new TripDetailDto();
                        tripDetailDto.setId(invitation.getId());        //邀请表ID
                        tripDetailDto.setCity(invitation.getCity());
                        tripDetailDto.setPlace(invitation.getPlace());
                        tripDetailDto.setStartTime(invitation.getStartTime());
                        tripDetailDto.setStopTime(invitation.getStopTime());

                        tripDetailDtoList.add(tripDetailDto);


                    }
                    invitationDto.setInviteList(tripDetailDtoList);

                    invitationDtoList.add(invitationDto);
                    tripTrackingDto.setInvitationList(invitationDtoList);
                    tripTrackingDto.setInvitationAgree("0");
                    tripTrackingDto.setInvitationMessage("等待确认邀请中.....");
                }

                List<Invitation> invitationList2 = invitationMapper.findByCourseIdsAndAgree(trip.getCourseIds(),"1");      //审核通过
                if (!invitationList2.isEmpty()){
                    List<InvitationDto> invitationDtoList = new ArrayList<>();
                    InvitationDto invitationDto = new InvitationDto();
                    List<TripDetailDto> tripDetailDtoList = new ArrayList<>();
                    for (Invitation invitation : invitationList2
                    ) {
                        TripDetailDto tripDetailDto = new TripDetailDto();
                        tripDetailDto.setId(invitation.getId());        //邀请表ID
                        tripDetailDto.setCity(invitation.getCity());
                        tripDetailDto.setPlace(invitation.getPlace());
                        tripDetailDto.setStartTime(invitation.getStartTime());
                        tripDetailDto.setStopTime(invitation.getStopTime());

                        tripDetailDtoList.add(tripDetailDto);


                    }
                    invitationDto.setInviteList(tripDetailDtoList);

                    invitationDtoList.add(invitationDto);
                    tripTrackingDto.setInvitationList(invitationDtoList);
                    tripTrackingDto.setInvitationAgree("1");
                    tripTrackingDto.setInvitationMessage("您已接受邀请，学院已确认您的操作！");
                }
//                if (!invitationList.isEmpty()){
//                    List<InvitationDto> invitationDtoList = new ArrayList<>();
//                    for (Invitation invitation : invitationList
//                         ) {
//                        InvitationDto invitationDto = new InvitationDto();
//                        List<TripDetailDto> tripDetailDtoList = new ArrayList<>();
//                        List<Invitation> invitationList1 = invitationMapper.findByCourseNameAndCreateTime(course.getName(),invitation.getCreateTime());     //单个时间点，多个教学点
//                        for (Invitation currentInvitation : invitationList1
//                             ) {
//                            TripDetailDto tripDetailDto = new TripDetailDto();
//                            tripDetailDto.setId(currentInvitation.getId());     //邀请表ID
//                            tripDetailDto.setStartTime(invitation.getStartTime());      //开课时间
//                            tripDetailDto.setStopTime(invitation.getStopTime());        //结课时间
//                            tripDetailDto.setCity(invitation.getCity());     //具体上课地点
//                            Course course1 = courseMapper.findById(invitation.getCourseId());
//                            if (course1 != null){
//                                tripDetailDto.setPlace(course.getRoom());       //具体地点
////                                tripDetailDto.setStartTime(course.getStarttime());      //开课时间
////                                tripDetailDto.setStopTime(course.getStoptime());        //结课时间
////                                SysDictionary sysDictionary = sysDictonaryService.getByCodeFlag(Integer.valueOf(course1.getClassroom()));
////                                if (sysDictionary != null){
////                                    tripDetailDto.setCity(sysDictionary.getCodeflagname());     //具体上课地点
////                                }
//                            }
//                            tripDetailDtoList.add(tripDetailDto);
//                        }
//                        invitationDto.setInviteList(tripDetailDtoList);
//                        invitationDto.setAgree(invitation.getAgree());
//                        if (invitation.getAgree().equals("0")){
//                            invitationDto.setMessage("等待确认邀请中.....");
//
//                            tripTrackingDto.setInvitationAgree("0");
//                            tripTrackingDto.setInvitationMessage("等待确认邀请中.....");
//                        }
//                        if (invitation.getAgree().equals("1")){
//
//                            tripProgressDto.setAcceptInvitationTime(invitation.getModifyTime());        //接受邀请时间
//
//                            invitationDto.setMessage("学院已确认您的操作！");
//
//                            tripTrackingDto.setInvitationAgree("1");
//                            tripTrackingDto.setInvitationMessage("您已接受邀请，学院已确认您的操作！");
//                        }
//                        if (invitation.getAgree().equals("2")){
//
//                            invitationDto.setMessage("请求更改，请稍等！");
//                        }
//                        invitationDtoList.add(invitationDto);
//                    }
//                    tripTrackingDto.setInvitationList(invitationDtoList);
//
//                }

                if (courseAudit.getFirstAudit().equals("2")){
                    tripTrackingDto.setInvitationAgree("2");
                    tripTrackingDto.setInvitationMessage("您的更改未通过，请重新选择！");
                }


            }

            //食宿确定
            if (Integer.valueOf(trip.getStatus()) >= 1 && Integer.valueOf(courseAudit.getStatus()) >= 1){       //通过初审，接受邀请

                if (!trip.getBoardAndLodging().equals("0") && trip.getBoardAndLodgingTime() != null){
                    tripProgressDto.setBoardAndLodgingTime(trip.getBoardAndLodgingTime());      //食宿确认时间
                }

                if (trip.getBoardAndLodging().equals("0")){
                    tripTrackingDto.setNeedBoardAndLodging("0");        //未确认
                    tripTrackingDto.setBoardAndLodgingMessage("等待食宿确认中......");
                }else if (trip.getBoardAndLodging().equals("1")){     //需要学院安排食宿

                    tripTrackingDto.setNeedBoardAndLodging("1");        //需要
                    tripTrackingDto.setBoardAndLodgingMessage("本次行程需要安排食宿");

                    List<TripDetailDto> tripDetailDtoList = new ArrayList<>();

                    List<BoardAndLodging> boardAndLodgingList = boardAndLodgingMapper.findByTripId(trip.getId());

                    if (!boardAndLodgingList.isEmpty()){
                        for (BoardAndLodging boardAndLodging : boardAndLodgingList
                             ) {
                            TripDetailDto tripDetailDto = new TripDetailDto();

                            tripDetailDto.setId(boardAndLodging.getId());       //食宿表ID
                            tripDetailDto.setCity(boardAndLodging.getCity());
                            tripDetailDto.setPlace(boardAndLodging.getPlace());
                            tripDetailDto.setStartTime(boardAndLodging.getStartTime());
                            tripDetailDto.setStopTime(boardAndLodging.getStopTime());

                            tripDetailDtoList.add(tripDetailDto);
                        }
                        tripTrackingDto.setBoardAndLodgingList(tripDetailDtoList);
                    }
                }else if (trip.getBoardAndLodging().equals("2")){
                    tripTrackingDto.setNeedBoardAndLodging("2");        //不需要
                    tripTrackingDto.setBoardAndLodgingMessage("本次行程不需要安排食宿！");
                }else {
                    tripTrackingDto.setNeedBoardAndLodging("3");        //已安排
                    tripTrackingDto.setBoardAndLodgingMessage("已为本次行程安排食宿！");
                    List<TripDetailDto> tripDetailDtoList = new ArrayList<>();

                    List<BoardAndLodging> boardAndLodgingList = boardAndLodgingMapper.findByTripId(trip.getId());

                    if (!boardAndLodgingList.isEmpty()){
                        for (BoardAndLodging boardAndLodging : boardAndLodgingList
                        ) {
                            TripDetailDto tripDetailDto = new TripDetailDto();

                            tripDetailDto.setId(boardAndLodging.getId());       //食宿表ID
                            tripDetailDto.setCity(boardAndLodging.getCity());
                            tripDetailDto.setPlace(boardAndLodging.getPlace());
                            tripDetailDto.setStartTime(boardAndLodging.getStartTime());
                            tripDetailDto.setStopTime(boardAndLodging.getStopTime());

                            tripDetailDtoList.add(tripDetailDto);
                        }
                        tripTrackingDto.setBoardAndLodgingList(tripDetailDtoList);
                    }
                }


            }

            //材料上传
            if (Integer.valueOf(trip.getStatus()) >= 2 && Integer.valueOf(courseAudit.getStatus()) >= 1){       //通过初审，食宿确定之后

                Procedure procedure = procedureMapper.findByTripId(trip.getId());

                if (procedure != null){
                    TripFileDto tripFileDto = new TripFileDto();

                    //签证
                    if (procedure.getVisa() != null){
                        SysFile visa = sysFileMapper.selectByPrimaryKey(procedure.getVisa());

                        if (visa != null){
                            tripFileDto.setVisaId(visa.getId());
                            tripFileDto.setVisaUrl(preview + visa.getUrl());
                        }
                    }

                    //机票
                    if (procedure.getPlaneTicket() != null){
                        SysFile planeTicket = sysFileMapper.selectByPrimaryKey(procedure.getPlaneTicket());

                        if (planeTicket != null){
                            tripFileDto.setPlaneTicketId(planeTicket.getId());
                            tripFileDto.setPlaneTicketUrl(preview + planeTicket.getUrl());
                        }
                    }

                    if (tripFileDto != null){
                        tripTrackingDto.setTripFile(tripFileDto);
                    }

                    if (trip.getProcedure().equals("1")){
                        tripProgressDto.setUploadTime(procedure.getModifyTime());       //上传材料审核成功时间
                    }

                }
            }

            tripTrackingDto.setTripProgress(tripProgressDto);

            LOGGER.info("行程跟踪详情：{}",tripTrackingDto);
            return Result.success(tripTrackingDto);
        }
        LOGGER.info("无数据");
        return Result.result("ACK","无数据");
    }

    @Override
    public Result<String> acceptInvitation(AcceptInvitationInDto dto){
        if (dto.getTripId() == null){
            LOGGER.info("tripId为空");
            return Result.result("NACK","tripId为空");
        }
        if (StringUtils.isEmpty(dto.getAgree())){
            LOGGER.info("入参agree为空");
            return Result.result("NACK","入参agree为空");
        }

        Trip trip = tripMapper.findById(dto.getTripId());
        if (trip == null){
            LOGGER.info("无此行程");
            return Result.result("ACK","无此行程");
        }

        List<Invitation> invitationList = invitationMapper.findByCourseIdsAndAgree(trip.getCourseIds(),"0");

        if (invitationList.isEmpty()){
            LOGGER.info("无邀请记录");
            return Result.result("ACK","无邀请记录");
        }

        if (dto.getAgree().equals("1")){        //同意
            for (Invitation invitation : invitationList
                 ) {
                invitationMapper.updateAgree("1",invitation.getId());

                LOGGER.info("接受邀请，Invitation.agree = 1");
            }

            trip.setAgree("1");         //接受邀请
            trip.setStatus("1");        //状态改为-接受邀请
            trip.setModifyTime(new Date());

            tripMapper.update(trip);

            LOGGER.info("接受邀请，Trip.agree = 1");

            //将总开课时间、总结课时间记录到课程考核表中
            List<Course> courseList = courseMapper.findInIds(trip.getCourseIds());
            if (!courseList.isEmpty()){
                Date totalBegin = null;
                Date totalEnd = null;
                for (Course course : courseList
                     ) {
                    if (totalBegin == null){
                        totalBegin = course.getStarttime();
                    }
                    if (totalEnd == null){
                        totalEnd = course.getStoptime();
                    }
                    if (totalBegin.after(course.getStarttime())){
                        totalBegin = course.getStarttime();
                    }
                    if (totalEnd.before(course.getStoptime())){
                        totalEnd = course.getStoptime();
                    }
                }
                CourseAudit courseAudit = courseAuditMapper.findByCourseId("'" + String.valueOf(courseList.get(0).getId()) + "'");
                if (courseAudit != null){
                    courseAudit.setStartCourseTime(totalBegin);
                    courseAudit.setStopCourseTime(totalEnd);
                    courseAudit.setModifyTime(new Date());

                    courseAuditMapper.update(courseAudit);

                    LOGGER.info("更新课程考核表，设置课程总开课时间，总结课时间");
                }
            }
        }else if (dto.getAgree().equals("2")){          //更改

            if (dto.getClassList().isEmpty()){
                LOGGER.info("更改内容为空，请重新选择");
                return Result.result("ACK","更改内容为空，请重新选择");
            }

            Integer index = 0;
            for (Invitation invitation : invitationList
            ) {
                invitationMapper.updateAgree("2",invitation.getId());

                LOGGER.info("更改时间，Invitation.agree = 2");

                Course course = courseMapper.findById(invitation.getCourseId());
                if (course != null){
//                    course.setStarttime(dto.getClassList().get(index).getStartTime());
//                    course.setStoptime(dto.getClassList().get(index).getStopTime());
                    Date startTime = dto.getClassList().get(index).getStartTime();

                    Date stopTime = dto.getClassList().get(index).getStopTime();

//                    courseMapper.updateByPrimaryKey(course);
                    courseMapper.updateTimeById(course.getId(),startTime,stopTime);

                    LOGGER.info("更新课程：{}",course);

                    index++;
                }

            }

            Course course = courseMapper.findById(invitationList.get(0).getCourseId());
            if (course != null){
//                courseMapper.deleteInIds(trip.getCourseIds());          //删除原课程内容
//
//                for (ClassDto classDto : dto.getClassList()     //新建课程
//                     ) {
//                    Course newCourse = course;
//                    newCourse.setClassroom(String.valueOf(classDto.getCodeFlag()));
//                    newCourse.setStarttime(classDto.getStartTime());
//                    newCourse.setStoptime(classDto.getStopTime());
//
//                    courseMapper.insertNewCourse(newCourse);
//
//                    LOGGER.info("新建课程：{}",newCourse);
//                }

//                List<Integer> courseIdList = courseMapper.findIdsByNameAndPeriod(course.getName(),course.getPeriod());
//
//                //更改时间，修改trip
//                if (!courseIdList.isEmpty()){
//                    trip.setCourseIds(Transform.listIntegerToString(courseIdList));
//
//                    trip.setModifyTime(new Date());
//
//                    tripMapper.update(trip);
//
//                    LOGGER.info("更改时间，修改trip");
//                }

                //修改课程考核表，状态回到最开始
                CourseAudit courseAudit = courseAuditMapper.findByCourseId("'" + String.valueOf(course.getId()) + "'");

                if (courseAudit != null){
//                    courseAudit.setCourseIds(Transform.listIntegerToString(courseIdList));
                    courseAudit.setStatus("0");     //状态回到最开始
                    courseAudit.setFirstAudit("0");
                    courseAudit.setFirstAuditTime(null);
                    courseAudit.setModifyTime(new Date());

                    courseAuditMapper.update(courseAudit);

                    LOGGER.info("修改课程考核表，状态回到最开始");
                }
            }



//            trip.setAgree("2");         //接受邀请
//            trip.setStatus("1");        //状态改为-接受邀请
//            trip.setModifyTime(new Date());
//
//            tripMapper.update(trip);
//
//            LOGGER.info("接受邀请，Trip.agree = 1");

        }else if (dto.getAgree().equals("3")){      //取消开课

            Course course = courseMapper.findByIds(trip.getCourseIds());
            if (course != null){
                //删除课程
                courseMapper.deleteInIds(trip.getCourseIds());

                LOGGER.info("删除课程");

                //删除课程审核表
                courseAuditMapper.deleteLikeId("'" + String.valueOf(course.getId()) + "'");

                LOGGER.info("删除课程审核表");

                //删除邀请表
                List<Integer> courseIdList = courseMapper.findIdsByNameAndPeriod(course.getName(),course.getPeriod());
                if (!courseIdList.isEmpty()){
                    for (Integer courseId : courseIdList
                         ) {
                        invitationMapper.delete(courseId);

                        LOGGER.info("删除邀请表");
                    }
                }

                //删除行程表
                tripMapper.delete(trip.getId());

                LOGGER.info("删除行程表");
            }

        }
        LOGGER.info("邀请确认成功");
        return Result.success();
    }

    @Override
    public Result<String> confirmBoardAndLodging(ConfirmBoardAndLodgingDto dto){

        if (dto.getTripId() == null){
            LOGGER.info("tripId为空");
            return Result.result("NACK","tripId为空");
        }
        if (StringUtils.isEmpty(dto.getNeed())){
            LOGGER.info("入参need为空");
            return Result.result("NACK","入参agree为空");
        }

        Trip trip = tripMapper.findById(dto.getTripId());
        if (trip == null){
            LOGGER.info("无此行程");
            return Result.result("ACK","无此行程");
        }

        if (dto.getNeed().equals("1")){     //需要学院安排食宿

            //新建食宿表
//            List<Course> courseList = courseMapper.findInIds(trip.getCourseIds());
//            if (!courseList.isEmpty()){
//                for (Course course : courseList
//                     ) {
//                    BoardAndLodging boardAndLodging = new BoardAndLodging();
//
//                    boardAndLodging.setTripId(trip.getId());
//                    boardAndLodging.setStartTime(course.getStarttime());
//                    boardAndLodging.setStopTime(course.getStoptime());
//                    boardAndLodging.setPlace(course.getRoom());
//
//                    SysDictionary sysDictionary = sysDictonaryService.getByCodeFlag(Integer.valueOf(course.getClassroom()));
//
//                    if (sysDictionary != null){
//                        boardAndLodging.setCity(sysDictionary.getCodeflagname());
//                    }
//                    boardAndLodging.setCreateTime(new Date());
//
//                    boardAndLodgingMapper.insert(boardAndLodging);
//
//                    LOGGER.info("新建食宿表：{}",boardAndLodging);
//                }
//
//
//            }

            trip.setBoardAndLodging("1");       //需要
            trip.setModifyTime(new Date());

        }else {
            trip.setBoardAndLodging("2");       //不需要
            trip.setBoardAndLodgingTime(new Date());
            trip.setStatus("2");
            trip.setModifyTime(new Date());
        }

        tripMapper.update(trip);

        LOGGER.info("更新trip:{}",trip);

        LOGGER.info("食宿确认成功");

        return Result.success();
    }

    @Override
    public Result<String> uploadTripFile(UploadTripFileDto dto){
        if (dto.getTripId() == null){
            LOGGER.info("tripId为空");
            return Result.result("NACK","tripId为空");
        }

        Trip trip = tripMapper.findById(dto.getTripId());
        if (trip == null){
            LOGGER.info("无此行程");
            return Result.result("ACK","无此行程");
        }

        Procedure responseProcedure = new Procedure();      //返回行程材料用

        Procedure procedure = procedureMapper.findByTripId(trip.getId());
        if (procedure == null){
            Procedure newPpocedure = new Procedure();
            newPpocedure.setTripId(trip.getId());
            newPpocedure.setCreateTime(new Date());
            if (dto.getVisaId() != null){
                newPpocedure.setVisa(dto.getVisaId());
            }
            if (dto.getPlaneTicketId() != null){
                newPpocedure.setPlaneTicket(dto.getPlaneTicketId());
            }

            procedureMapper.insert(newPpocedure);

            responseProcedure = newPpocedure;

        }else {
            if (dto.getVisaId() != null){
                procedure.setVisa(dto.getVisaId());
            }
            if (dto.getPlaneTicketId() != null){
                procedure.setPlaneTicket(dto.getPlaneTicketId());
            }
            procedure.setModifyTime(new Date());

            procedureMapper.update(procedure);

            trip.setProcedure("0");

            tripMapper.update(trip);

            LOGGER.info("更新trip,行程材料审核：{}",trip);

            responseProcedure = procedure;
        }
        LOGGER.info("上传成功：{}",procedure);

        //返回本行程的手续材料文件

        List<FileDto> fileDtoList = new ArrayList<>();
        if (responseProcedure.getVisa() != null){
            SysFile sysFile = sysFileMapper.selectByPrimaryKey(responseProcedure.getVisa());
            if (sysFile != null){
                FileDto fileDto = new FileDto();

                fileDto.setFileId(sysFile.getId());
                fileDto.setFileName("visa");
                fileDto.setFileUrl(preview + sysFile.getUrl());

                fileDtoList.add(fileDto);       //签证

                LOGGER.info("签证:{}",fileDto);
            }
        }

        if (responseProcedure.getPlaneTicket() != null){
            SysFile sysFile = sysFileMapper.selectByPrimaryKey(responseProcedure.getPlaneTicket());
            if (sysFile != null){
                FileDto fileDto = new FileDto();

                fileDto.setFileId(sysFile.getId());
                fileDto.setFileName("planeTicket");
                fileDto.setFileUrl(preview + sysFile.getUrl());

                fileDtoList.add(fileDto);       //机票

                LOGGER.info("机票:{}",fileDto);
            }
        }

        LOGGER.info("上传成功",fileDtoList);

        if (fileDtoList.isEmpty()){
            return Result.success("上传成功",Collections.EMPTY_LIST);
        }else {
            return Result.success("上传成功",fileDtoList);
        }
    }
}
