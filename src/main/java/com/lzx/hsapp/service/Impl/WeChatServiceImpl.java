package com.lzx.hsapp.service.Impl;

import com.lzx.hsapp.dao.AccessTokenMapper;
import com.lzx.hsapp.entity.AccessToken;
import com.lzx.hsapp.service.WeChatService;
import com.lzx.hsapp.utils.HttpRequestUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class WeChatServiceImpl implements WeChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServiceImpl.class);

    private static String appID = "wxf58544e27a43135f";

    private static String appSecret = "20d998726a507348901e3afdf88eccfa";

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Scheduled(cron = "0 0 0/1 * * ?")
    @Override
    public String getAccessToken() {                //String appid, String appsecret
        String result = HttpRequestUtil.getAccessToken(appID,appSecret);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (null != jsonObject) {
            try {
                result = jsonObject.getString("access_token");

                Integer expiresIn = jsonObject.getInt("expires_in");

                LOGGER.info("access_token:{}",result);

                AccessToken accessToken = accessTokenMapper.findById(1);

                if (accessToken != null){
                    accessToken.setAccessToken( result);

                    if (expiresIn != null){
                        accessToken.setExpiresIn(expiresIn);

                        LOGGER.info("expiresIn：{}",expiresIn);
                    }

                    accessTokenMapper.updateToken(accessToken);

                    LOGGER.info("更新access_token");

                }
            } catch (JSONException e) {
                LOGGER.info("获取token失败 errcode:"+jsonObject.getInt("errcode") +",errmsg:"+ jsonObject.getString("errmsg"));
            }
        }
        return result;
    }


}
