package com.lzx.hsapp.service;

import com.lzx.hsapp.dto.*;
import com.lzx.hsapp.utils.Result;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface TripService {
    boolean createTripByCourseName(String courseName,String period);

    Result<List<TripCardDto>> getTripTrackingList(TeacherIdDto dto);

    Result<TripTrackingDto> tripTrackingDetail(TripIdDto dto);

    Result<String> acceptInvitation(AcceptInvitationInDto dto);

    Result<String> confirmBoardAndLodging(ConfirmBoardAndLodgingDto dto);

    Result<String> uploadTripFile(UploadTripFileDto dto);
}
