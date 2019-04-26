package com.lzx.hsapp.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface CourseQRCodeService {


    void addCourseQRCode(Integer courseId, Integer qrCodeID);
}
