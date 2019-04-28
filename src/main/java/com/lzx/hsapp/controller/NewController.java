package com.lzx.hsapp.controller;

import com.lzx.hsapp.entity.News;
import com.lzx.hsapp.service.NewsService;
import com.lzx.hsapp.utils.ActionUtil;
import com.lzx.hsapp.utils.DateUtils;
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
@RequestMapping("news")
@CrossOrigin(value = "*")
public class NewController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NewsService newsService;

    /**
     * 获取新闻列表及详情
     * @param request
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/getNewslist")
    @ResponseBody
    public String getNewslist(HttpServletRequest request, HttpServletResponse response){
            response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
            String id=request.getParameter("id");
            News news=new News();
            if(id!=null&&!"".equals(id)){
                news.setId(Integer.parseInt(id));
            }
            try {
                List<News> newsList = newsService.selectAll(news);
            //    PageInfo<News> page = new PageInfo<News>(newsList);
                if (newsList != null) {
                    if(newsList.size()!=0) {
                        for (int i = 0; i < newsList.size(); i++) {
                            newsList.get(i).setUrl(ActionUtil.ROOTURL+newsList.get(i).getUrl());
                            newsList.get(i).setNewsTime(DateUtils.getFormattedString(newsList.get(i).getCreatetime(),DateUtils.yyyyMMdd));
                        }
                    }
                    log.info("NewController查询新闻列表成功");
                    return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "查询新闻列表成功", newsList, 0);
                }
            }
            catch (Exception e){
                log.error("NewController查询新闻列表失败"+e.getMessage());
                e.printStackTrace();
            }
                return webUtil.resultTotal(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "查询新闻列表失败", null, 0);
    }

    @RequestMapping("/appgetNewslist")
    @ResponseBody
    public String appgetNewslist(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin",ActionUtil.CrossDomain);
        String id=request.getParameter("id");
        News news=new News();
        if(id!=null&&!"".equals(id)){
            news.setId(Integer.parseInt(id));
        }
        try {
            List<News> newsList = newsService.selectAll(news);
        //    PageInfo<News> page = new PageInfo<News>(newsList);
            if (newsList != null) {
                if(newsList.size()!=0) {
                    for (int i = 0; i < newsList.size(); i++) {
                        newsList.get(i).setUrl(ActionUtil.ROOTURL+newsList.get(i).getUrl());
                        newsList.get(i).setNewsTime(DateUtils.getFormattedString(newsList.get(i).getCreatetime(),DateUtils.yyyyMMdd));
                    //   newsList.get(i).setContent(newsList.get(i).getContent().replaceAll("<(?!img)[^>]*>",""));
                        newsList.get(i).setContent(newsList.get(i).getContent().replaceAll("<(?!img).*?>"  ,""));
                    }
                }
                log.info("NewController查询新闻列表成功");
                return webUtil.resultTotal(webUtil.FLAG_SUCCESS, webUtil.ERROR_CODE_SUCCESS, "查询新闻列表成功", newsList, 0);
            }
        }
        catch (Exception e){
            log.error("NewController查询新闻列表失败"+e.getMessage());
            e.printStackTrace();
        }
        return webUtil.resultTotal(webUtil.FLAG__FAILED, webUtil.ERROR_CODE_ILLEGAL, "查询新闻列表失败", null, 0);
    }


}
