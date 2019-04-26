package com.lzx.hsapp.service.Impl;

import com.lzx.hsapp.dao.CourseQRCodeMapper;
import com.lzx.hsapp.entity.CourseQRCode;
import com.lzx.hsapp.service.CourseQRCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Component
public class CourseQRCodeServiceImpl implements CourseQRCodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseQRCodeServiceImpl.class);

    @Autowired
    private CourseQRCodeMapper courseQRCodeMapper;

    @Override
    public void addCourseQRCode(Integer courseId,Integer qrCodeID){
        CourseQRCode courseQRCode = new CourseQRCode();

        courseQRCode.setCourseId(courseId);
        courseQRCode.setQrCode(qrCodeID);
        courseQRCode.setCreateTime(new Date());
        courseQRCodeMapper.insert(courseQRCode);

        LOGGER.info("添加CourseQRCode成功：{}",courseQRCode);
    }
}
