package com.lzx.hsapp.controller;

import com.github.pagehelper.PageInfo;
import com.lzx.hsapp.entity.Enlist;
import com.lzx.hsapp.entity.EnlistVoinfo;
import com.lzx.hsapp.service.EnlistService;
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
 * Created by wangdaren on 2018/2/2.
 */
@RestController
@RequestMapping("enlist")
@CrossOrigin(origins = "*")
public class EnlistController {
    Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private EnlistService enlistService;

    @RequestMapping("insertEnlist")
    @ResponseBody
    public String insertEnlist(HttpServletRequest request, HttpServletResponse response, Pagination pagination) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String cid = request.getParameter("courseid");
        String uid = request.getParameter("userid");
        logger.info("courseId:{}",cid);
        logger.info("userId:{}",uid);
        Enlist enlist = new Enlist();
        try {
            if (cid != null && uid != null) {
                enlist.setCid(Integer.parseInt(cid));
                enlist.setUid(Integer.parseInt(uid));
                EnlistVoinfo enlistVoinfo = new EnlistVoinfo();
                enlistVoinfo.setCid(Integer.parseInt(cid));
                enlistVoinfo.setUid(Integer.parseInt(uid));
                List<EnlistVoinfo> enlistVoinfos = enlistService.selectAll(enlistVoinfo, pagination);
                if (enlistVoinfos.size() == 1) {
                    return webUtil.result(webUtil.UPDATE_FAILED, webUtil.ERROR_CODE_ILLEGAL, "学员已经报名", null);
                }
                int flag = enlistService.insert(enlist);
                if (flag == 1) {
                    return webUtil.result(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "学员报名课程成功", flag);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("插入报名信息失败EnlistController" + e.getMessage());
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "学员报名课程失败", null);
    }

    @RequestMapping("getEnlistbyid")
    @ResponseBody
    public String getEnlistbyid(HttpServletRequest request, HttpServletResponse response, Pagination pagination) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String uid = request.getParameter("uid");
        EnlistVoinfo enlistVoinfo = new EnlistVoinfo();
        enlistVoinfo.setUid(Integer.parseInt(uid));
        try {
            List<EnlistVoinfo> enlistVoinfoList = enlistService.selectAll(enlistVoinfo, pagination);
            PageInfo<EnlistVoinfo> page = new PageInfo<EnlistVoinfo>(enlistVoinfoList);
            if (enlistVoinfoList != null) {
                return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "学员报名情况查询成功", enlistVoinfoList, page.getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "学员报名情况查询失败", null);
    }

    @RequestMapping("backEnlist")
    @ResponseBody
    public String backEnlist(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        String uid = request.getParameter("uid");
        String cid = request.getParameter("cid");
        EnlistVoinfo enlistVoinfo = new EnlistVoinfo();
        enlistVoinfo.setUid(Integer.parseInt(uid));
        enlistVoinfo.setCid(Integer.parseInt(cid));
        try {
            int flag = enlistService.deleteByCidAndUid(enlistVoinfo);
            return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "学员退报成功", null, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webUtil.result(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "学员退报失败", null);
    }
}
