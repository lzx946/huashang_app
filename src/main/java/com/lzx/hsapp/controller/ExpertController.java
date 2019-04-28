package com.lzx.hsapp.controller;

import com.lzx.hsapp.entity.Course;
import com.lzx.hsapp.entity.ExpertsVoinfo;
import com.lzx.hsapp.entity.Expertsinfo;
import com.lzx.hsapp.service.ExpertService;
import com.lzx.hsapp.service.HomeDataService;
import com.lzx.hsapp.service.LoginRegisterService;
import com.lzx.hsapp.utils.ActionUtil;
import com.lzx.hsapp.utils.Pagination;
import com.lzx.hsapp.utils.webUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@RestController
@RequestMapping("expert")
@CrossOrigin(value = "*")
public class ExpertController {
    Logger logger= LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private ExpertService expertService;
    @Autowired
    private HomeDataService homeDataService;
    @Autowired
    private LoginRegisterService loginRegisterService;

    /**
     * 获取专家列表及详情
     * @param request
     * @param response
     * @param
     * @return
     */
    @RequestMapping("getExpertlist")
    @ResponseBody
    public String getExpertlist(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        ExpertsVoinfo expertsVoinfo=new ExpertsVoinfo();
        String id=request.getParameter("id");
        if(id!=null&&!"".equals(id)){
            expertsVoinfo.setId(Integer.parseInt(id));
        }
        List<ExpertsVoinfo> expertsVoinfoList=expertService.selectAll(expertsVoinfo);
    //    PageInfo<ExpertsVoinfo> page=new PageInfo<ExpertsVoinfo>(expertsVoinfoList);
        if(expertsVoinfoList!=null){
            logger.info("查询专家列表及详情成功");
        //    return expertsVoinfoList;
            return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "查询专家列表成功", expertsVoinfoList, 0);
        }
        logger.error("查询专家列表及详情失败");
     //   return null;
       return webUtil.resultTotal(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "查询专家列表失败", null, 0);
    }

    /**
     * 更新专家信息
     * @param request
     * @param response
     * @param expertsinfo
     * @param pagination
     * @return
     */
    @RequestMapping("updateExpertinfo")
    @ResponseBody
    public String updateExpertinfo(HttpServletRequest request, HttpServletResponse response, Expertsinfo expertsinfo, Pagination pagination){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String flag=request.getParameter("id");
        if(flag==null){
            return null;
        }
//        if(flagemail!=null&&flagemail!=""&&Integer.parseInt(flagemail)==0&&expertsinfo.getEmail()!=null){
//            ExpertsVoinfo expertsVoinfo=new ExpertsVoinfo();
//            expertsVoinfo.setId(expertsinfo.getId());
//            List<ExpertsVoinfo> expertsVoinfoList=expertService.selectAll(expertsVoinfo,pagination);
//            if(expertsVoinfoList.get(0).getEmail()==expertsinfo.getEmail()){
//                ExpertsVoinfo expertsVoinfovery=new ExpertsVoinfo();
//                expertsVoinfovery.setEmail(expertsinfo.getEmail());
//                List<ExpertsVoinfo> expertsVoinfos=expertService.selectAll(expertsVoinfovery,pagination);
//                if(expertsVoinfos.size()==1){
//                    return webUtil.result(webUtil.UPDATE_FAILED, webUtil.ERROR_CODE_ILLEGAL, "邮箱已存在", null);
//                }
//            }
//                Expertsinfo expertsinfoup=new Expertsinfo();
//                expertsinfoup.setEmail(expertsinfo.getEmail());
//                expertsinfoup.setId(expertsinfo.getId());
//                boolean flag=expertService.updateByPrimaryKey(expertsinfoup);
//                if(flag==true){
//                    return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "更新邮箱成功", flag);
//                }
//            return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "邮箱更新失败", null);
//        }
//        expertsinfo.setEmail(null);
        boolean flagup=expertService.updateByPrimaryKey(expertsinfo);
        if(flagup==true){
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "更新专家信息成功", flagup);
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "更新专家信息失败", null);
    }
    /**
     * 根据专家id查询课程信息
     * @param request
     * @param response
     * @param pagination
     * @return
     */
    @RequestMapping("getCoursebyteacherid")
    @ResponseBody
    public String getCoursebyteacherid(HttpServletRequest request, HttpServletResponse response, Pagination pagination) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String teacherid = request.getParameter("teacherid");
        if (teacherid != null && teacherid != "") {
            List<Course> courseList = homeDataService.selectCoursebyteacher(Integer.parseInt(teacherid), pagination);
        //    PageInfo<Course> page = new PageInfo<Course>(courseList);
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "专家查询课程成功", courseList);
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "专家查询课程失败", null);
    }
    /**
     * 验证手机和姓名
     * @param request
     * @param response
     * @param expertsinfo
     * @return
     */
    @RequestMapping("verifyPhoneRealname")
    @ResponseBody
    public String verifyPhoneRealname(HttpServletRequest request, HttpServletResponse response, Expertsinfo expertsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if (expertsinfo.getRealname()!=null&&!"".equals(expertsinfo.getRealname())&&expertsinfo.getPhone()!=null&&
                !"".equals(expertsinfo.getPhone())) {
            Expertsinfo expertsinfoinfo=loginRegisterService.selectByAccountEmail(expertsinfo);
            if (expertsinfoinfo==null) {
                return webUtil.result(webUtil.UPDATE_FAILED, webUtil.ERROR_CODE_ILLEGAL, "验证信息错误", null);
            }
            else {
                return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "验证信息成功", expertsinfoinfo);
            }
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "信息不全", null);
    }
    /**
     * 修改密码或者找回密码
     * @param request
     * @param response
     * @param expertsinfo
     * @return
     */
    @RequestMapping("forgetpassword")
    @ResponseBody
    public String forgetpassword(HttpServletRequest request, HttpServletResponse response, Expertsinfo expertsinfo) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        boolean flag=expertService.updateByPrimaryKey(expertsinfo);
        if (flag==false) {
            return webUtil.result(webUtil.UPDATE_FAILED, webUtil.ERROR_CODE_ILLEGAL, "找回密码失败", null);
        }
        else {
            return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "找回密码成功", flag);
        }
    }
}
