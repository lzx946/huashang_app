package com.lzx.hsapp.controller;

import com.lzx.hsapp.entity.Expertsinfo;
import com.lzx.hsapp.entity.Studentsinfo;
import com.lzx.hsapp.service.LoginRegisterService;
import com.lzx.hsapp.utils.ActionUtil;
import com.lzx.hsapp.utils.webUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(value = "*")
public class LoginRegisterController {

    private Logger LOGGER = LoggerFactory.getLogger(LoginRegisterController.class.getName());
    @Autowired
    LoginRegisterService loginRegisterService;

    @RequestMapping("/yanzhengS")
    @ResponseBody
    public Boolean yanzhengS(HttpServletResponse response, Studentsinfo studentsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if (studentsinfo == null) {
            return null;
        }
        Studentsinfo studentsinfo1 = loginRegisterService.selectByAccountEmail(studentsinfo);
        if (studentsinfo1 == null)
            return false;
        else
            return true;
    }
    @RequestMapping("/yanzhengE")
    @ResponseBody
    public Boolean yanzhengE(HttpServletResponse response, Expertsinfo expertsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if (expertsinfo == null) {
            return null;
        }
        Expertsinfo expertsinfo1 = loginRegisterService.selectByAccountEmail(expertsinfo);
        if (expertsinfo1 == null)
            return false;
        else
            return true;
    }

    @RequestMapping("/loginStudent")
    @ResponseBody
    public String login(HttpServletResponse response, Studentsinfo studentsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if (studentsinfo == null) {
            return null;
        }
    //    studentsinfo.setPassword(MD5.encodeString(studentsinfo.getPassword()));
        Studentsinfo studentsinfo1 = loginRegisterService.selectByAccountEmail(studentsinfo);

        if (studentsinfo1 == null)
            return webUtil.resultTotal(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_PSDERROR, "手机号或密码错误", null,0);
        else {
            studentsinfo1.setPassword("");
            return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "OK", studentsinfo1, 0);
        }
    }

    @RequestMapping("/registerStudent")
    @ResponseBody
    public Boolean register(HttpServletResponse response, Studentsinfo studentsinfo) {
        LOGGER.info("000");
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if (studentsinfo == null) {
            LOGGER.info("111");
            return null;
        }

        return loginRegisterService.register(studentsinfo);
    }


    @RequestMapping("/loginExpert")
    @ResponseBody
    public String login(HttpServletResponse response, Expertsinfo expertsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if (expertsinfo == null) {
            return null;
        }
    //    expertsinfo.setPassword(MD5.encodeString(expertsinfo.getPassword()));
        Expertsinfo expertsinfo1 = loginRegisterService.selectByAccountEmail(expertsinfo);
        if (expertsinfo1 == null)
            return webUtil.resultTotal(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_PSDERROR, "手机号密码错误或账户未审核", null,0);
        else {
            expertsinfo1.setPassword("");
            return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "OK", expertsinfo1, 0);
        }
    }

    @RequestMapping("/registerExpert")
    @ResponseBody
    public Boolean register(HttpServletResponse response, Expertsinfo expertsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if (expertsinfo == null) {
            return null;
        }
        return loginRegisterService.register(expertsinfo);
    }
}
