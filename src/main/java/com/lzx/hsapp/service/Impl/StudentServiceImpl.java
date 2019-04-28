package com.lzx.hsapp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.lzx.hsapp.dao.StudentsinfoMapper;
import com.lzx.hsapp.entity.StudentsVoinfo;
import com.lzx.hsapp.entity.Studentsinfo;
import com.lzx.hsapp.service.StudentService;
import com.lzx.hsapp.utils.MD5;
import com.lzx.hsapp.utils.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@Service
@Component
public class StudentServiceImpl implements StudentService {
    Logger logger= LoggerFactory.getLogger(getClass().getName());
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
}
