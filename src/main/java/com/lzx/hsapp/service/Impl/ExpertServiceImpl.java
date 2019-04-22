package com.lzx.hsapp.service.Impl;

import com.lzx.hsapp.dao.CourseMapper;
import com.lzx.hsapp.dao.ExpertsinfoMapper;
import com.lzx.hsapp.entity.Course;
import com.lzx.hsapp.entity.ExpertsVoinfo;
import com.lzx.hsapp.entity.Expertsinfo;
import com.lzx.hsapp.service.ExpertService;
import com.lzx.hsapp.utils.ActionUtil;
import com.lzx.hsapp.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@Service
public class ExpertServiceImpl implements ExpertService {
    Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private ExpertsinfoMapper expertsinfoMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<ExpertsVoinfo> selectAll(ExpertsVoinfo expertsVoinfo) {
        List<ExpertsVoinfo> expertsVoinfos = new ArrayList<ExpertsVoinfo>();
        try {
        //    PageHelper.startPage(pagination.getPageNum(), pagination.getNumPerPage());
            List<ExpertsVoinfo> expertsVoinfoList = expertsinfoMapper.selectAll(expertsVoinfo);
           /* PageInfo<ExpertsVoinfo> page = new PageInfo<ExpertsVoinfo>(expertsVoinfoList);*/
            if (expertsVoinfoList != null) {
                for (int i = 0; i < expertsVoinfoList.size(); i++) {
                    if (expertsVoinfoList.size() != 0) {
                        List<Course> courses=courseMapper.selectCoursebyteacher(expertsVoinfoList.get(i).getId());
                        if(courses!=null)
                            expertsVoinfoList.get(i).setCourses(courses);
                        expertsVoinfoList.get(i).setUrl(ActionUtil.ROOTURL + expertsVoinfoList.get(i).getUrl());
                    }
                    if (expertsVoinfoList.size() != 0 && expertsVoinfo.getId() == null) {
                        ExpertsVoinfo expertsVoinfoshow = new ExpertsVoinfo();
                        expertsVoinfoshow.setId(expertsVoinfoList.get(i).getId());
                        expertsVoinfoshow.setUrl(expertsVoinfoList.get(i).getUrl());
                        expertsVoinfoshow.setRealname(expertsVoinfoList.get(i).getRealname());
                        expertsVoinfoshow.setEnname(expertsVoinfoList.get(i).getEnname());
                        expertsVoinfoshow.setAcademic(expertsVoinfoList.get(i).getAcademic());
                        expertsVoinfoshow.setOpenemail(expertsVoinfoList.get(i).getOpenemail());
                        expertsVoinfoshow.setEnname(expertsVoinfoList.get(i).getEnname());
                        expertsVoinfoshow.setEmployer(expertsVoinfoList.get(i).getEmployer());
                        expertsVoinfoshow.setSummarys(expertsVoinfoList.get(i).getSummarys());
                        expertsVoinfoshow.setSummary(expertsVoinfoList.get(i).getSummary());
                        expertsVoinfoshow.setCourses(expertsVoinfoList.get(i).getCourses());
                        if (expertsVoinfoList.get(i).getOtheracademic() != null) {
                            String[] othacademic = expertsVoinfoList.get(i).getOtheracademic().split(",");
                            if (othacademic.length > 3) {
                                expertsVoinfoshow.setOtheracademic(othacademic[0] + "," + othacademic[1] + "," + othacademic[2]);
                            } else {
                                expertsVoinfoshow.setOtheracademic(expertsVoinfoList.get(i).getOtheracademic());
                            }

                        }
                        expertsVoinfoshow.setCreatetime(expertsVoinfoList.get(i).getCreatetime());
                        expertsVoinfos.add(expertsVoinfoshow);
                    }
                }
                if (expertsVoinfo.getId() == null || "".equals(expertsVoinfo.getId())) {
                    return expertsVoinfos;
                }
                return expertsVoinfoList;
            }
        } catch (Exception e) {
            logger.error("获取专家列表及详情出错");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateByPrimaryKey(Expertsinfo record) {
        try {
            if(record.getPassword()!=null){
                record.setPassword(MD5.encodeString(record.getPassword()));
            }
            int flag = expertsinfoMapper.updateByPrimaryKey(record);
            if (flag == 1) {                return true;
            }
        } catch (Exception e) {
            logger.error("更新专家信息失败" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
