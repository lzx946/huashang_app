package com.lzx.hsapp.controller;

import com.lzx.hsapp.dto.*;
import com.lzx.hsapp.service.TripService;
import com.lzx.hsapp.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/trip")
@CrossOrigin(value = "*")
public class TripController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripService tripService;

    @RequestMapping(value = "/getTripTrackingList",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<TripCardDto>> getTripTrackingList(@RequestBody TeacherIdDto dto){

        LOGGER.info("行程跟踪列表，入参：{}",dto);

        return tripService.getTripTrackingList(dto);
    }

    @RequestMapping(value = "/tripTrackingDetail",method = RequestMethod.POST)
    @ResponseBody
    public Result<TripTrackingDto> tripTrackingDetail(@RequestBody TripIdDto dto){

        LOGGER.info("行程跟踪详情,入参：{}",dto);

        return tripService.tripTrackingDetail(dto);
    }

    @RequestMapping(value = "/acceptInvitation",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> acceptInvitation(@RequestBody AcceptInvitationInDto dto){

        LOGGER.info("接受邀请，入参：{}",dto);

        return tripService.acceptInvitation(dto);
    }

    @RequestMapping(value = "/confirmBoardAndLodging",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> confirmBoardAndLodging(@RequestBody ConfirmBoardAndLodgingDto dto){

        LOGGER.info("食宿确认，入参：{}",dto);

        return tripService.confirmBoardAndLodging(dto);
    }

    @RequestMapping(value = "/uploadTripFile",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> uploadTripFile(@RequestBody UploadTripFileDto dto){

        LOGGER.info("上传行程手续材料，入参：{}",dto);

        return tripService.uploadTripFile(dto);
    }
}
