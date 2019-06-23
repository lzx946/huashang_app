package com.lzx.hsapp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.lzx.hsapp.dao.StudentsinfoMapper;
import com.lzx.hsapp.entity.StudentsVoinfo;
import com.lzx.hsapp.entity.Studentsinfo;
import com.lzx.hsapp.service.StudentService;
import com.lzx.hsapp.util.HttpRequest;
import com.lzx.hsapp.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by wangdaren on 2018/2/1.
 */
@Service
@Component
public class StudentServiceImpl implements StudentService {
    Logger logger= LoggerFactory.getLogger(getClass().getName());

    private static String openId = "oPtUv1GDeDvj33qc5egjNq69sk-U";

    @Autowired
    private StudentsinfoMapper studentsinfoMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Studentsinfo record) {
        return 0;
    }

    public Studentsinfo selectByPrimaryKey(Studentsinfo studentsinfo) {
        Studentsinfo studentsinfolist=studentsinfoMapper.selectByPrimaryKey(studentsinfo);
        if(studentsinfolist!=null)
            return studentsinfolist;
        else
            return null;
    }

    @Override
    public boolean forgetpassword(Studentsinfo studentsinfo){
                studentsinfo.setPassword(MD5.encodeString(studentsinfo.getPassword()));
                int flag=studentsinfoMapper.updateByPrimaryKey(studentsinfo);
                if(flag==1) {
                    return true;
                }
                return  false;
    }

    @Override
    public List<StudentsVoinfo> selectAll(StudentsVoinfo studentsVoinfo, Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getNumPerPage());
        try{
           List<StudentsVoinfo> studentsVoinfoList=studentsinfoMapper.selectAll(studentsVoinfo);
           if(studentsVoinfoList!=null){
               logger.info("StudentServiceImpl查询学员列表成功");
               return studentsVoinfoList;
           }
       }
       catch (Exception e){
            e.printStackTrace();
            logger.error("StudentServiceImpl查询学员列表失败"+e.getMessage());
       }
       return null;
    }

    @Override
    public int updateByPrimaryKey(Studentsinfo record) {
       try {
            int flag=studentsinfoMapper.updateByPrimaryKey(record);
            if(flag==1){
                return 1;
            }
       }
       catch (Exception  e){
           logger.error("更新学员信息失败"+e.getMessage());
           e.printStackTrace();
       }
       return 0;
    }

    @Override
    public String submitInfo(HttpServletResponse response, Map<String, String> param){
        String phone = param.get("phone");
        logger.info("phone:{}",phone);
        String realName = param.get("realname");
        logger.info("realName:{}",realName);
        String weChat = param.get("wechat");
        logger.info("weChat:{}",weChat);
        String summary = param.get("summary");
        logger.info("summary:{}",summary);

        Cookie cookie = new Cookie("openid",weChat);
        response.addCookie(cookie);


        if (StringUtils.isEmpty(weChat)){
//            logger.info("weChat为空");
//            return Result.result("NACK","weChat为空");
            weChat = openId;
        }

        Studentsinfo studentsinfo = studentsinfoMapper.findByPhone(phone);
        if (studentsinfo != null){
            studentsinfo.setRealname(realName);
            studentsinfo.setWechat(weChat);
            studentsinfo.setSummary(summary);

            studentsinfoMapper.updateByPrimaryKey(studentsinfo);

            logger.info("提交成功");

            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "提交成功", studentsinfo);
        }else {
            Studentsinfo student = new Studentsinfo();
            student.setSummary(summary);
            student.setWechat(weChat);
            student.setPhone(phone);
            student.setState(1);
            student.setPassword(MD5.encodeString("000000"));    //默认密码"000000"
            student.setRealname(realName);

            studentsinfoMapper.insert(student);

            Studentsinfo newStudent = studentsinfoMapper.findByPhone(phone);

            logger.info("插入学员数据：{}",student);
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "提交成功", newStudent);
        }

//        return Result.result("NACK","提交失败");


    }
}
