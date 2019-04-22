package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.Enlist;
import com.lzx.hsapp.entity.EnlistVoinfo;
import com.lzx.hsapp.utils.Pagination;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/2.
 */
public interface EnlistService {
    /**
     * 根据cid和uid删除一条信息
     * @param enlistVoinfo
     * @return
     */
    int deleteByCidAndUid(EnlistVoinfo enlistVoinfo);
    /**
     * 插入课程报名
     * @param record
     * @return
     */
    int insert(Enlist record);

    /**
     * 查询报名情况
     * @param enlistVoinfo
     * @return
     */
    List<EnlistVoinfo> selectAll(EnlistVoinfo enlistVoinfo, Pagination pagination);
}
