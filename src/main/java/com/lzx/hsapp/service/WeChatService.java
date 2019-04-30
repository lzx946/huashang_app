package com.lzx.hsapp.service;

import com.lzx.hsapp.utils.Result;
import com.lzx.hsapp.utils.WeiXinResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface WeChatService {
    String createTempTicket(String expireSeconds, int sceneId);

    String createForeverTicket(Integer sceneId);

    String createForeverStrTicket(String sceneStr);

    String showQrcode(String ticket);

    WeiXinResult showQrcode(String ticket, String savePath) throws Exception;

    String getAccessTokenById(Integer id);

    String getTicket(String accessToken);

    Result<String> uploadQRCode(Integer courseId);

    String getAccessToken();            //String appid, String appsecret
}
