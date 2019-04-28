package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.StudentsVoinfo;
import com.lzx.hsapp.entity.Studentsinfo;
import com.lzx.hsapp.utils.Pagination;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@Component
public interface StudentService {
    int deleteByPrimaryKey(Integer id);

    int insert(Studentsinfo record);

    Studentsinfo selectByPrimaryKey(Studentsinfo studentsinfo);

    /**
     * 忘记密码
     * @param studentsinfo
     * @return
     */
    boolean forgetpassword(Studentsinfo studentsinfo);

    /**
     * 查询学员信息
     * @param studentsVoinfo
     * @param pagination
     * @return
     */
    List<StudentsVoinfo> selectAll(StudentsVoinfo studentsVoinfo, Pagination pagination);

    /**
     * 更新学员信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Studentsinfo record);
}
