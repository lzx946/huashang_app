package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.ExpertsVoinfo;
import com.lzx.hsapp.entity.Expertsinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface ExpertsinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Expertsinfo record);

    Expertsinfo selectByPrimaryKey(Integer id);

    List<ExpertsVoinfo> selectAll(ExpertsVoinfo expertsVoinfo);

    /**
     * 更新专家信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Expertsinfo record);

    /**
     * 根据账户或邮箱登录
     *
     * @param record
     * @return
     */
    Expertsinfo selectByAccountEmail(Expertsinfo record);
    /**
     *首页教授推荐
     */
    List<ExpertsVoinfo> homeExpert(ExpertsVoinfo record);

    @Select("select * from expertsinfo where id = #{id}")
    Expertsinfo findById(@Param("id") Integer id);

}