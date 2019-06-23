package com.lzx.hsapp.controller;

import com.github.pagehelper.PageInfo;
import com.lzx.hsapp.entity.StudentsVoinfo;
import com.lzx.hsapp.entity.Studentsinfo;
import com.lzx.hsapp.service.LoginRegisterService;
import com.lzx.hsapp.service.StudentService;
import com.lzx.hsapp.service.WeChatService;
import com.lzx.hsapp.util.weChatWebAccess.HttpsClient;
import com.lzx.hsapp.util.weChatWebAccess.Response;
import com.lzx.hsapp.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangdaren on 2018/2/1.
 */
@RestController
@RequestMapping("student")
@CrossOrigin(origins = "*")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private StudentService studentService;
    @Autowired
    private LoginRegisterService loginRegisterService;

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private HttpsClient httpsClient;

    /**
     * 获取学员列表及详情
     *
     * @param request
     * @param response
     * @param pagination
     * @return
     */
    @RequestMapping("/getStudentlist")
    @ResponseBody
    public  String getStudentlist(HttpServletRequest request, HttpServletResponse response, Pagination pagination) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String id = request.getParameter("id");
        StudentsVoinfo studentsVoinfo = new StudentsVoinfo();
        List<StudentsVoinfo> studentsVoinfos = new ArrayList<StudentsVoinfo>();
        if (id != null && !"".equals(id)) {
            studentsVoinfo.setId(Integer.parseInt(id));
        }
        try {
            List<StudentsVoinfo> studentsVoinfoList = studentService.selectAll(studentsVoinfo, pagination);
            PageInfo<StudentsVoinfo> page = new PageInfo<StudentsVoinfo>(studentsVoinfoList);
            if (studentsVoinfoList != null) {
                for (int i = 0; i < studentsVoinfoList.size(); i++) {
                    if (studentsVoinfoList.size() != 0) {
                        studentsVoinfoList.get(i).setUrl(ActionUtil.ROOTURL + studentsVoinfoList.get(i).getUrl());
                    }
                    if (studentsVoinfoList.size() != 0 && id == null) {
                       StudentsVoinfo studentsVoinfoshow = new StudentsVoinfo();
/*                          studentsVoinfoshow.setId(studentsVoinfoList.get(i).getId());
                        studentsVoinfoshow.setUrl(studentsVoinfoList.get(i).getUrl());
                        studentsVoinfoshow.setRealname(studentsVoinfoList.get(i).getRealname());
                        studentsVoinfoshow.setEnname(studentsVoinfoList.get(i).getEnname());
                        studentsVoinfoshow.setPosition(studentsVoinfoList.get(i).getPosition());
                        if (studentsVoinfoList.get(i).getOtherposition() != null) {
                            String[] othpositions = studentsVoinfoList.get(i).getOtherposition().split(",");
                            if (othpositions.length > 3) {
                                studentsVoinfoshow.setOtherposition(othpositions[0] + "," + othpositions[1] + "," + othpositions[2]);
                            } else {
                                studentsVoinfoshow.setOtherposition(studentsVoinfoList.get(i).getOtherposition());
                            }
                        }*/
                        studentsVoinfoshow.setCreatetime(studentsVoinfoList.get(i).getCreatetime());
                        studentsVoinfoshow=studentsVoinfoList.get(i);
                        studentsVoinfos.add(studentsVoinfoshow);
                    }
                }
                System.out.println(studentsVoinfos.size());
                if (id == null || "".equals(id)) {
                    return webUtil.result(webUtil.FLAG_SUCCESS,webUtil.ERROR_CODE_SUCCESS,"查询学员列表成功",studentsVoinfos);
                   /// return studentsVoinfos;
                }
                logger.info("StudentController查询学员列表成功");
                return webUtil.result(webUtil.FLAG_SUCCESS,webUtil.ERROR_CODE_SUCCESS,"查询学员成功",studentsVoinfoList);
              //  return studentsVoinfoList;
            }
        } catch (Exception e) {
            logger.error("StudentController查询学员列表失败" + e.getMessage());
            e.printStackTrace();
        }
        return webUtil.result(webUtil.FLAG__FAILED,webUtil.ERROR_CODE_ILLEGAL,"查询学员失败",null);
    }

    /**
     * 更新学员信息
     *
     * @param request
     * @param response
     * @param studentsinfo
     * @return
     */
    @RequestMapping("updateStudentinfo")
    @ResponseBody
    public String updateStudentinfo(HttpServletRequest request, HttpServletResponse response, Studentsinfo studentsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String flagphone = request.getParameter("flag");
        if (flagphone != null && flagphone != "" && Integer.parseInt(flagphone) == 0 && studentsinfo.getPhone() != null) {
            Studentsinfo stuvery = new Studentsinfo();
            stuvery.setEmail(studentsinfo.getPhone());
            Studentsinfo selectinfo = loginRegisterService.selectByAccountEmail(stuvery);
            if (!selectinfo.getPhone().equals(studentsinfo.getPhone())) {
                return webUtil.result(webUtil.UPDATE_FAILED, webUtil.ERROR_CODE_ILLEGAL, "电话号码已存在", null);
            }
            Studentsinfo studentsinfoup = new Studentsinfo();
            studentsinfoup.setPhone(studentsinfo.getEmail());
            studentsinfoup.setId(studentsinfo.getId());
            int flag = studentService.updateByPrimaryKey(studentsinfoup);
            if (flag == 1) {
                return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "更新电话号码成功", flag);
            }
            return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "电话号码更新失败", null);
        }
        studentsinfo.setEmail(null);
        int flagup = studentService.updateByPrimaryKey(studentsinfo);
        if (flagup == 1) {
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "更新学员信息成功", flagup);
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "更新学员信息失败", null);
    }

    /**
     * 验证手机和姓名
     * @param request
     * @param response
     * @param studentsinfo
     * @return
     */
    @RequestMapping("verifyPhoneRealname")
    @ResponseBody
    public String verifyPhoneRealname(HttpServletRequest request, HttpServletResponse response, Studentsinfo studentsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if (studentsinfo.getRealname()!=null&&!"".equals(studentsinfo.getRealname())&&studentsinfo.getPhone()!=null&&
                !"".equals(studentsinfo.getPhone())) {
            Studentsinfo studentsinfoinfo=studentService.selectByPrimaryKey(studentsinfo);
            if (studentsinfoinfo==null) {
                return webUtil.result(webUtil.UPDATE_FAILED, webUtil.ERROR_CODE_ILLEGAL, "验证信息错误", null);
            }
            else {
                return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "验证信息成功", studentsinfoinfo);
            }
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "信息不全", null);
    }
    /**
     * 修改密码或者找回密码
     * @param request
     * @param response
     * @param studentsinfo
     * @return
     */
    @RequestMapping("forgetpassword")
    @ResponseBody
    public String forgetpassword(HttpServletRequest request, HttpServletResponse response, Studentsinfo studentsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
            boolean flag=studentService.forgetpassword(studentsinfo);
            if (flag==false) {
                return webUtil.result(webUtil.UPDATE_FAILED, webUtil.ERROR_CODE_ILLEGAL, "找回密码失败", null);
            }
            else {
                return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "找回密码成功", flag);
            }
    }

    @RequestMapping(value = "/weChat/submitInfo",method = RequestMethod.POST)
    @ResponseBody
    public String submitInfo(HttpServletRequest request,HttpServletResponse response){

//            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf58544e27a43135f&redirect_uri=http://y24584r997.zicp.vip/weChat/getOpenId&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
//            try {
////                String result = HttpRequestUtil.defaultConnection("GET",url,5000,5000,null);
//
//                Response result = httpsClient.get(url);
//
//                logger.info("result:{}",result);
//                logger.info(result.asString());
//                logger.info(String.valueOf(result.asJSONObject()));
//                return Result.success();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return Result.result("NACK","提交失败");

        String phone = request.getParameter("phone");
        logger.info("phone:{}",phone);
        String realname = request.getParameter("realname");
        logger.info("realname:{}",realname);
        String wechat = request.getParameter("wechat");
        logger.info("wechat:{}",wechat);
        String summary = request.getParameter("summary");
        logger.info("summary:{}",summary);

        Map<String,String> param = new HashMap<>();

        param.put("phone",phone);
        param.put("realname",realname);
        param.put("wechat",wechat);
        param.put("summary",summary);

        return studentService.submitInfo(response,param);

    }
}
