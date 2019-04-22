package com.lzx.hsapp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.lzx.hsapp.dao.CourseMapper;
import com.lzx.hsapp.entity.CourseVo;
import com.lzx.hsapp.service.CourseService;
import com.lzx.hsapp.utils.ActionUtil;
import com.lzx.hsapp.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangdaren on 2018/3/25.
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<CourseVo> selectByteach(CourseVo courseVo) {
        List<CourseVo> list=courseMapper.selectByteach(courseVo);
        if(list!=null)
            return list;
        return null;
    }

    @Override
    public List<CourseVo> selectAll(CourseVo courseVo1, Pagination pagination) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        PageHelper.startPage(pagination.getPageNum(),pagination.getNumPerPage());
        List<CourseVo> courseVoList=courseMapper.selectAll(courseVo1);
       /* List<CourseVo> courseVoList=new ArrayList<CourseVo>();
        if(courseVoList2==null)
            return null;
        for(int m=0;m<courseVoList2.size();m++){
            if(!courseVoList.contains(courseVoList2.get(m).getName())){
                courseVoList.add(courseVoList2.get(m));
            }
        }*/
       /* if(courseVoList2.size()<2){
            courseVoList=courseVoList2;
        }
        else {
            for (int m = 1; m < courseVoList2.size(); m++) {
                for (int k = 0; k < m; k++) {
                    if (courseVoList2.get(m).getName().equals(courseVoList2.get(k).getName())) {
                        courseVoList.add(courseVoList2.get(k));
                    }
                    else{
                        courseVoList.add(courseVoList2.get(m));
                    }
                }
            }
        }*/
        if(courseVoList!=null){
            for(int i=0;i<courseVoList.size();i++){
                courseVoList.get(i).setUrl(ActionUtil.ROOTURL+courseVoList.get(i).getUrl());
                CourseVo courseVo=new CourseVo();
                courseVo.setTeacherid(courseVoList.get(i).getTeacherid());
                courseVo.setName(courseVoList.get(i).getName());
                List<CourseVo> courses=courseMapper.selectByteach(courseVo);
                if(courses!=null) {
                    List<Date> list=new ArrayList<Date>();
                    for(int j=0;j<courses.size();j++){
                        String begindate=sdf.format(courses.get(j).getStarttime());
                        String enddate=sdf.format(courses.get(j).getStoptime());
                        courses.get(j).setTime(begindate.substring(0,4)+"年"+begindate.substring(5,7)+"月"
                        +begindate.substring(8,10)+"日"+"-"+enddate.substring(5,7)+"月"+enddate.substring(8,10)+"日");
                        try {
                            Long totaltime=(sdf.parse(enddate)).getTime();
                            courses.get(j).setEndtime(totaltime);
                        }
                       catch (Exception e){
                            e.printStackTrace();
                       }
                        list.add(courses.get(j).getStarttime());
                        list.add(courses.get(j).getStoptime());
                    }
                    Date max=list.get(0);
                    Date min=list.get(0);
                    for(int k=0;k<list.size();k++){
                        if(max.getTime()<list.get(k).getTime()){
                            max=list.get(k);
                        }
                        if(min.getTime()>list.get(k).getTime()){
                            min=list.get(k);
                        }
                    }
                    String totalbegin=sdf.format(min).substring(0,4)+"年"+sdf.format(min).substring(5,7)+"月"+sdf.format(min).substring(8,10)+"日";
                    String totalend=sdf.format(max).substring(0,4)+"年"+sdf.format(max).substring(5,7)+"月"+sdf.format(max).substring(8,10)+"日";;
                    courseVoList.get(i).setTotalbegin(totalbegin);
                    courseVoList.get(i).setTotalend(totalend);
                    courseVoList.get(i).setCourses(courses);
                }
            }
            return courseVoList;
        }
        return null;
    }
}
