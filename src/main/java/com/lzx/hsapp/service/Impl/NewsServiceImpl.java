package com.lzx.hsapp.service.Impl;

import com.lzx.hsapp.dao.NewsMapper;
import com.lzx.hsapp.entity.News;
import com.lzx.hsapp.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@Service
public class NewsServiceImpl implements NewsService {
    Logger logger= LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(News record) {
        return 0;
    }

    @Override
    public News selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<News> selectAll(News news) {
    //    PageHelper.startPage(pagination.getPageNum(),pagination.getNumPerPage());
        try{
            List<News> newsinfoList=newsMapper.selectAll(news);
            if(newsinfoList!=null){
                logger.info("查询新闻列表成功ServiceImpl");
                return newsinfoList;
            }
        }
        catch (Exception e){
            logger.error("查询新闻列表失败ServiceImpl"+e.getMessage());
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public int updateByPrimaryKey(News record) {
        return 0;
    }
}
