package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.News;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@Component
public interface NewsService {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    News selectByPrimaryKey(Integer id);

    /**
     * 查询新闻信息
     * @param news
     * @param pagination
     * @return
     */
//    List<News> selectAll(News news,Pagination pagination);

    List<News> selectAll(News news);

    int updateByPrimaryKey(News record);
}
