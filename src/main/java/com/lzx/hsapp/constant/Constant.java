package com.lzx.hsapp.constant;

/**
 * Created by rongyaowen
 * on 2019/1/15.
 * 常量类
 */
public class Constant {
    /**
     * accessToken
     */
    public static String ACCESS_TOKEN = "";

    /**
     * 微信公众号参数
     */
    public class WechatAccount {
        public static final String APPID = "wxf58544e27a43135f";
        public static final String APPSECRET = "20d998726a507348901e3afdf88eccfa";
        public static final String TOKEN = "weixin_lzx98";
        public static final String ENCODINGAESKEY = "nznOSCiYGuXKuzAdpax7YiXJkCuFRBQhDvsnrClmMmb";
    }

    /**
     * 消息类型
     */
    public class MsgType {
        public static final String TEXT = "text";
        public static final String EVENT = "event";
    }

    /**
     * 事件类型
     */
    public class Event {
        // 订阅
        public static final String SUBSCRIBE = "subscribe";
        public static final String CLICK = "CLICK";
        public static final String SCAN = "SCAN";
    }

    /**
     * 请求头类型
     */
    public class ContentType{
        public static final String APPLICATION_JSON = "application/json;charset=utf-8";
    }
}
