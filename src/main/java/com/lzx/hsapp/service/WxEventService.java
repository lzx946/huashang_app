package com.lzx.hsapp.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Component
public interface WxEventService {
    String processRequest(HttpServletRequest req);
}
