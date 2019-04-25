package com.lzx.hsapp.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface WeChatService {
    String getAccessToken();            //String appid, String appsecret
}
