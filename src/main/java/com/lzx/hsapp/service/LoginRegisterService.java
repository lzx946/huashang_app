package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.Expertsinfo;
import com.lzx.hsapp.entity.Studentsinfo;
import org.springframework.stereotype.Component;

@Component
public interface LoginRegisterService {
    /**
     * 根据账户或邮箱登录学员
     * @param record
     * @return
     */
    Studentsinfo selectByAccountEmail(Studentsinfo record);
    /**
     * 学员注册
     * @param record
     * @return
     */
    Boolean register(Studentsinfo record);
    /**
     * 根据账户或邮箱登录专家
     * @param record
     * @return
     */
    Expertsinfo selectByAccountEmail(Expertsinfo record);
    /**
     * 专家注册
     * @param record
     * @return
     */
    Boolean register(Expertsinfo record);
}
