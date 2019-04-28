package com.lzx.hsapp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.lzx.hsapp.dao.EnlistMapper;
import com.lzx.hsapp.entity.Enlist;
import com.lzx.hsapp.entity.EnlistVoinfo;
import com.lzx.hsapp.service.EnlistService;
import com.lzx.hsapp.utils.Pagination;
import com.lzx.hsapp.utils.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wangdaren on 2018/2/2.
 */
@Service
@Component
public class EnlistServiceImpl implements EnlistService {
    private static final Logger logger= LoggerFactory.getLogger(EnlistServiceImpl.class);
    @Autowired
    private EnlistMapper enlistMapper;

    public int deleteByCidAndUid(EnlistVoinfo enlistVoinfo) {
        try {
            int flag=enlistMapper.deleteByCidAndUid(enlistVoinfo);
            if(flag==1){
                return  1;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("删除报名失败EnlistServiceImpl"+e.getMessage());
        }
        return 0;
    }

    @Override
    public int insert(Enlist record) {
        record.setCreatetime(new Date());
        record.setResult(0);
       try {
           int flag=enlistMapper.insert(record);
           if(flag==1){
               return  1;
           }
       }
      catch (Exception e){
           e.printStackTrace();
           logger.error("插入报名失败EnlistServiceImpl"+e.getMessage());
      }
       return 0;
    }

    @Override
    public List<EnlistVoinfo> selectAll(EnlistVoinfo enlistVoinfo, Pagination pagination) {
        PageHelper.startPage(pagination.getPageNum(),pagination.getNumPerPage());
        try {
            List<EnlistVoinfo> enlistVoinfoList = enlistMapper.selectAll(enlistVoinfo);
            if (enlistVoinfoList != null) {
                return enlistVoinfoList;
            }
        }
        catch (Exception e){
            logger.error("查询报名失败"+e.getMessage());
            e.printStackTrace();
        }
            return null;
    }

    @Override
    public Result<String> signIn(Integer courseId,Integer studentId){
        if (courseId != null && studentId != null){
            Enlist enlist = enlistMapper.findByCourseIdAndStudentId(courseId,studentId);
            if (enlist != null){
                if (enlist.getSignIn().equals("1")){
                    logger.info("已签到");
                    return Result.result("ACK","已签到");
                }else {
                    enlistMapper.updateSingIn("1",enlist.getId());

                    logger.info("签到成功");
                    return Result.result("ACK","签到成功");
                }
            }
        }
        logger.info("入参为空");
        return Result.result("NACK","入参为空");
    }
}
