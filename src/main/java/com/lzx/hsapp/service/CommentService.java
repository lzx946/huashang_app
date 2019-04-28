package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.Comment;
import com.lzx.hsapp.entity.CommentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface CommentService {
    /**
     * 根据课程ID查询评论列表
     * @param cid
     * @return
     */
    List<CommentDetail> selectByCid(Integer cid);

    /**
     * 插入一条评论
     * @param record
     * @return
     */
    int insert(Comment record);
}
