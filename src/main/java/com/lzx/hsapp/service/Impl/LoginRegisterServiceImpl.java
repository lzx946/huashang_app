package com.lzx.hsapp.service.Impl;

import com.lzx.hsapp.dao.ExpertsinfoMapper;
import com.lzx.hsapp.dao.StudentsinfoMapper;
import com.lzx.hsapp.entity.Expertsinfo;
import com.lzx.hsapp.entity.Studentsinfo;
import com.lzx.hsapp.service.LoginRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private StudentsinfoMapper studentsinfoMapper;

    @Autowired
    private ExpertsinfoMapper expertsinfoMapper;

    @Override
    public Studentsinfo selectByAccountEmail(Studentsinfo record) {
        try {
            Studentsinfo studentsinfo = studentsinfoMapper.selectByAccountEmail(record);
            if (studentsinfo != null) {
                return studentsinfo;
            }
        } catch (Exception e) {
            log.error("查询邮箱账户密码失败");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean register(Studentsinfo record) {
        try {
            int flag = 0;
            record.setPhotoid(1);
            record.setCreatetime(new Date());
            record.setState(1);
            if(record.getArea()==null) {
                record.setArea(0);
                log.info("222");
            }
            log.info("333");
            flag = studentsinfoMapper.insert(record);
            if (flag != 0) {
                log.info("444");
                return true;
            }
            log.info("555");
        } catch (Exception e) {
            log.error("注册失败");
            log.info("666");
            e.printStackTrace();
        }
        log.info("777");
        return false;
    }

    @Override
    public Expertsinfo selectByAccountEmail(Expertsinfo record) {
        try {
            Expertsinfo expertsinfo = expertsinfoMapper.selectByAccountEmail(record);
            if (expertsinfo != null) {
                return expertsinfo;
            }
        } catch (Exception e) {
            log.error("查询邮箱账户密码失败");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean register(Expertsinfo record) {
        try {
            int flag = 0;
            record.setPhotoid(1);
            record.setCreatetime(new Date());
            record.setState(0);
            flag = expertsinfoMapper.insert(record);
            if (flag != 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("专家注册失败");
            e.printStackTrace();
        }
        return false;
    }
}
