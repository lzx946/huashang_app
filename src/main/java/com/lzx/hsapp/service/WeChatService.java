package com.lzx.hsapp.service;

import com.lzx.hsapp.utils.WeiXinResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface WeChatService {
    String createTempTicket(String expireSeconds, int sceneId);

    String createForeverTicket(int sceneId);

    String createForeverStrTicket(String sceneStr);

    String showQrcode(String ticket);

    WeiXinResult showQrcode(String ticket, String savePath) throws Exception;

    String getAccessToken();            //String appid, String appsecret
}
