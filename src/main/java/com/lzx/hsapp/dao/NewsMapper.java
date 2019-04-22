package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.News;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    News selectByPrimaryKey(Integer id);

    List<News> selectAll(News news);

    int updateByPrimaryKey(News record);

    List<News> homeNews(Integer iscommend);

}