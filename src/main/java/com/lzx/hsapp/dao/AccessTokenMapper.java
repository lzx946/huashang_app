package com.lzx.hsapp.dao;

import com.lzx.hsapp.entity.AccessToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccessTokenMapper {

    @Update("update weixin_access_token set access_token = #{accessToken},jsapi_ticket = #{jsapiTicket}, expires_in = #{expiresIn}, create_time = now() where id = #{id}")
    void updateToken(AccessToken accessToken);

    @Select("select * from weixin_access_token where id = #{id}")
    AccessToken findById(@Param("id") Integer id);
}
