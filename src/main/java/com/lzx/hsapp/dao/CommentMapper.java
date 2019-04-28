package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.Comment;
import com.lzx.hsapp.entity.CommentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
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

    @Select("select * from comment where cid in (${courseIds})")
    List<Comment> findByIds(@Param("courseIds") String courseIds);
}