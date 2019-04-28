package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.ExpertsVoinfo;
import com.lzx.hsapp.entity.Expertsinfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
@Component
public interface ExpertService {
    /**
     * 查询专家信息
     * @param expertsVoinfo
     * @param
     * @return
     */
    List<ExpertsVoinfo> selectAll(ExpertsVoinfo expertsVoinfo);

    /**
     * 更新专家信息
     * @param record
     * @return
     */
    boolean updateByPrimaryKey(Expertsinfo record);
}
