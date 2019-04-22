package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Comment;
import com.lzx.hsapp.entity.CommentDetail;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface  CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<CommentDetail> selectAll(Integer iscommend);

    int updateByPrimaryKey(Comment record);

    /**
     * 根据课程ID查询评论列表
     * @param cid
     * @return
     */
    List<CommentDetail> selectByCid(Integer cid);
}