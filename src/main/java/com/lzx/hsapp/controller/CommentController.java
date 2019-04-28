package com.lzx.hsapp.controller;

import com.lzx.hsapp.entity.Comment;
import com.lzx.hsapp.entity.CommentDetail;
import com.lzx.hsapp.service.CommentService;
import com.lzx.hsapp.utils.ActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/comment")   //评论
    @ResponseBody
    public List<CommentDetail> comment(HttpServletResponse response, Integer cid){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if(cid==null){
            return null;
        }
        List<CommentDetail> list =commentService.selectByCid(cid);
        return list;
    }

    @RequestMapping(value = "/commentSubmit")  //提交评论
    @ResponseBody
    public int commentSubmit(HttpServletResponse response, Comment comment){
        response.setHeader("Access-Control-Allow-Origin", ActionUtil.CrossDomain);
        if(comment==null){
            return 0;
        }
        int flag =commentService.insert(comment);
        return flag;
    }


}
