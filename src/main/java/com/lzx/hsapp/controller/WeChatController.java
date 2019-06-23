package com.lzx.hsapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzx.hsapp.constant.Constant;
import com.lzx.hsapp.entity.AccessToken;
import com.lzx.hsapp.entity.Studentsinfo;
import com.lzx.hsapp.entity.msg.Message;
import com.lzx.hsapp.service.*;
import com.lzx.hsapp.service.msg.IMsgService;

import com.lzx.hsapp.util.SignUtil;
import com.lzx.hsapp.util.XmlUtil;
import com.lzx.hsapp.util.aes.AesException;
import com.lzx.hsapp.util.aes.WXBizMsgCrypt;
import com.lzx.hsapp.util.mapUtil.GetLocationMsg;
import com.lzx.hsapp.util.mapUtil.Gps;
import com.lzx.hsapp.util.mapUtil.PositionUtil;
import com.lzx.hsapp.util.mapUtil.UserDetails;
import com.lzx.hsapp.util.weChatWebAccess.Configuration;
import com.lzx.hsapp.util.weChatWebAccess.OAuth2;
import com.lzx.hsapp.util.weChatWebAccess.OAuth2Token;
import com.lzx.hsapp.util.weChatWebAccess.WeixinExtend;
import com.lzx.hsapp.utils.CheckOutUtil;
import com.lzx.hsapp.utils.HttpRequestUtil;
import com.lzx.hsapp.utils.Result;
import com.lzx.hsapp.utils.WeiXinResult;
//import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信接入
 */
@Controller
@RequestMapping("/wechat")
@CrossOrigin(origins = "*")
public class WeChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private WxEventService wxEventService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private Message message;

    @RequestMapping(value = "/tokenVerify",produces = "application/json;charset=utf-8")
    public void tokenVerify(HttpServletRequest request, HttpServletResponse response) throws IOException  {
//        boolean isGet = request.getMethod().toLowerCase().equals("get");
//        PrintWriter print;
//        if (isGet) {
//            // 微信加密签名
//            String signature = request.getParameter("signature");
//            // 时间戳
//            String timestamp = request.getParameter("timestamp");
//            // 随机数
//            String nonce = request.getParameter("nonce");
//            // 随机字符串
//            String echostr = request.getParameter("echostr");
//            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
//            if (signature != null && CheckOutUtil.checkSignature(signature, timestamp, nonce)) {
//                try {
//                    print = response.getWriter();
//                    print.write(echostr);
//                    print.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        // 微信加密签名
//        String msgSignature = request.getParameter("signature");
//        // 时间戳
//        String timeStamp = request.getParameter("timestamp");
//        // 随机数
//        String nonce = request.getParameter("nonce");
//        // 随机字符串
//        String echoStr = request.getParameter("echostr");
//        Map<String, String> map = null;
//        String msgType = null;
//
//        message.setSubscribe("感谢您的关注！");
//
//        try {
//            // 解析xml数据，将解析结果存储在HashMap中
//            map = new HashMap<>();
//            // 读取输入流
//            SAXReader reader = new SAXReader();
//            Document document = reader.read(request.getInputStream());
//            // 得到xml根元素
//            Element root = document.getRootElement();
//            XmlUtil.parserXml(root, map);
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                System.out.println("key:" + entry.getKey() + "-value:" + entry.getValue());
//            }
//            if (null != map && !map.isEmpty()) {
//                // 消息类型
//                msgType = map.get("MsgType");
//                String fromUserName = map.get("FromUserName");// 消息来源用户标识
//                String toUserName = map.get("ToUserName");// 消息目的用户标识
////                String msgType = map.get("MsgType");// 消息类型
//                String eventKey = map.get("EventKey");// 消息内容
//                String eventType = map.get("Event");
//
//            }
//        } catch (DocumentException e) {
//        } catch (IOException e) {
//        }
//
//        String result = null;
//        try {
//            if (StringUtils.isEmpty(msgType)) {
//                // 创建加密类
//                WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(Constant.WechatAccount.TOKEN, Constant.WechatAccount.ENCODINGAESKEY, Constant.WechatAccount.APPID);
//                // 比对msgSignature 用token, timeStamp, nonce加密的参数是否一致，一致证明该接口来自微信，异常则不是来自微信
//                result = wxcpt.verifyUrl_WXGZ(msgSignature, Constant.WechatAccount.TOKEN, timeStamp, nonce, echoStr);
//            } else {
//                if (msgType.equals(Constant.MsgType.TEXT)) {
//                    result = msgService.returnText(map,"hello");
//                } else if (msgType.equals(Constant.MsgType.EVENT)) { //事件处理
//                    String event = map.get("Event");
//                    if (event.equals(Constant.Event.SUBSCRIBE)) { // 关注公众号
//                        result = msgService.returnText(map, message.getSubscribe());
//                    } else if (event.equals(Constant.Event.CLICK)) { // 自定义菜单点击事件
//                        String eventKey = map.get("EventKey");
//                        if (eventKey.equals("V1001_TODAY_MUSIC")) {
//                            result = msgService.returnText(map, "今日歌曲如下：");
//                        }
//                    } else if (event.equals(Constant.Event.SCAN)) { // 扫码事件
//                        String eventKey = map.get("EventKey");
//                        if (eventKey.equals("temp_qrcode_test")) { //临时二维码
//                            result = msgService.returnText(map, "扫描临时二维码");
//                        } else if (eventKey.equals("44")) {
//                            result = msgService.returnText(map, "课程ID：" + eventKey);
//                        }
//                    }
//                }
//            }
//        } catch (AesException e) {
//            e.printStackTrace();
//        } finally {
//            return result;
//        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("requestData:" + JSON.toJSONString(parameterMap));
        LOGGER.info("requestData:" + JSON.toJSONString(parameterMap));
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        System.out.println(
                "signature=" + signature + ";timestamp=" + timestamp + ";nonce=" + nonce + ";echostr=" + echostr);
        LOGGER.info("signature=" + signature + ";timestamp=" + timestamp + ";nonce=" + nonce + ";echostr=" + echostr);
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        LOGGER.info("checkSignature:" + SignUtil.checkSignature(signature, timestamp, nonce));
        System.out.println("checkSignature:" + SignUtil.checkSignature(signature, timestamp, nonce));

        // 首次请求申请验证,返回echostr
        if (echostr != null) {
            out.println(echostr);
            if (out != null) {
                out.close();
            }
            return;
        }
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("success");
            LOGGER.info("success");
            String message = "success";
            try {
                message = wxEventService.processRequest(request);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.println(message);
                if (out != null) {
                    out.close();
                }
            }
        } else {
            System.out.println("The request signature is invalid");
            LOGGER.info("The request signature is invalid");
        }

    }

    @RequestMapping(value = "/getAccessToken",method = RequestMethod.POST)
    @ResponseBody
    public String getAccessToken(){
        return weChatService.getAccessToken();
    }

    @RequestMapping(value = "/getQRCode",method = RequestMethod.GET)
    @ResponseBody
    public String getQRCode(@Param("courseId") Integer courseId){

        String ticket = weChatService.createForeverTicket(courseId);

        try {
//            return weChatService.showQrcode(ticket,"/");
            String url = weChatService.showQrcode(ticket);
            LOGGER.info("url:{}",url);
            return HttpRequestUtil.downloadQRCode(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @RequestMapping(value = "/uploadQRCode",method = RequestMethod.GET)
//    @ResponseBody
//    public String uploadQRCode(@Param("courseId") Integer courseId){
//
//        String ticket = weChatService.createForeverTicket(courseId);
//
//        try {
////            return weChatService.showQrcode(ticket,"/");
//            String url = weChatService.showQrcode(ticket);
//            LOGGER.info("url:{}",url);
//            return HttpRequestUtil.downloadQRCode(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @RequestMapping(value = "/uploadQRCodeImage",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> uploadQRCodeImage(@Param("courseId") Integer courseId){

        LOGGER.info("获取并上传二维码入参,courseId：{}",courseId);

        return weChatService.uploadQRCode(courseId);
    }

    @RequestMapping(value = "/getJsapiTicket",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> getJsapiTicket(){
        String accessToken = weChatService.getAccessTokenById(1);

        LOGGER.info("accessToken:{}",accessToken);

        String ticket = weChatService.getTicket(accessToken);
        if (StringUtils.isNotBlank(ticket)){
            Map<String,String> map = new HashMap<>();
            map.put("ticket",ticket);
            return map;
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> signIn(@RequestBody Map<String, String> model) {

        return courseService.signInByMap(model);

    }

    @RequestMapping({"/index"})
    /*     */ public String index(HttpServletResponse response, Model model, @RequestParam(name = "code", required = false) String code, @RequestParam(name = "state", required = false) String state) {
        /*  68 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /*  69 */
        if ((code != null) && (!code.trim().equals(""))) {
            /*  70 */
            OAuth2 oAuth2 = new OAuth2();
            /*     */
            try {
                /*  72 */
                OAuth2Token oAuth2Token = oAuth2.login(Configuration.getOAuthAppId(), Configuration.getOAuthSecret(), code);
                /*  73 */
                model.addAttribute("openid", oAuth2Token.getOpenid());
                /*  74 */
                LOGGER.info("获取的openid值为：" + oAuth2Token.getOpenid());
                /*     */
            } catch (Exception e) {
                /*  76 */
                LOGGER.error("获取openid失败");
                /*  77 */
                e.printStackTrace();
                /*     */
            }
            /*     */
        }
        /*  80 */
        return "weindex";
        /*     */
    }

    /*     */
    /*     */
    @RequestMapping({"/getRedirectUrl"})
    /*     */
    @ResponseBody
    /*     */ public String getRedirectUrl(HttpServletResponse response) {
        /*  86 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /*  87 */
        WeixinExtend weixinExtend = new WeixinExtend();
        /*  88 */
        String url = null;
        /*  89 */
        url = weixinExtend.code();
        /*  90 */
        LOGGER.info("获取url连接:" + url);
        /*  91 */
        return url;
        /*     */
    }

    /*     */
    /*     */
    @RequestMapping({"/init"})
    /*     */ public String init() {
        /*  96 */
        LOGGER.info("初始化界面");
        /*  97 */
        return "init";
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
//    @RequestMapping({"/submitInfo"})
//    /*     */
//    @ResponseBody
//    /*     */ public String submitInfo(HttpServletResponse response, Studentsinfo studentsinfo)
//    /*     */ {
//        /* 109 */
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        /* 110 */
//        if ((studentsinfo.getWechat() == null) || (studentsinfo.getWechat().trim().equals(""))) {
//            /* 111 */
//            return WebUtil.results(0, "0002", "OPENID为空", null);
//            /*     */
//        }
//        /* 113 */
//        if ((studentsinfo.getRealname() == null) || (studentsinfo.getRealname().trim().equals(""))) {
//            /* 114 */
//            return WebUtil.results(0, "0002", "getRealname为空", null);
//            /*     */
//        }
//        /* 116 */
//        if ((studentsinfo.getPhone() == null) || (studentsinfo.getPhone().trim().equals(""))) {
//            /* 117 */
//            return WebUtil.results(0, "0002", "getPhone为空", null);
//            /*     */
//        }
//        /* 119 */
//        if ((studentsinfo.getSummary() == null) || (studentsinfo.getSummary().trim().equals(""))) {
//            /* 120 */
//            return WebUtil.results(0, "0002", "getSummary为空", null);
//            /*     */
//        }
//        /*     */
//        /* 123 */
//        Studentsinfo studentsinfo1 = this.studentsinfoService.selectByPhone(studentsinfo.getPhone());
//        /* 124 */
//        if (studentsinfo1 != null) {
//            /* 125 */
//            if ((studentsinfo1.getWechat() == null) || (studentsinfo1.getWechat().equals(""))) {
//                /* 126 */
//                this.studentsinfoService.updateByPhone(studentsinfo);
//                /* 127 */
//                Studentsinfo studentsinfo3 = this.studentsinfoService.selectByPrimaryKey(studentsinfo.getWechat());
//                /* 128 */
//                this.logger.info(studentsinfo3 != null ? studentsinfo3.getPhone() : "值为空");
//                /* 129 */
//                return WebUtil.results(1, "1000", "保存成功", studentsinfo3);
//            }
//            /* 130 */
//            if (!studentsinfo.getWechat().equals(studentsinfo1.getWechat())) {
//                /* 131 */
//                return WebUtil.results(0, "0003", "此手机号已绑定到其他微信账号", null);
//                /*     */
//            }
//            /* 133 */
//            this.studentsinfoService.updateByPhone(studentsinfo);
//            /* 134 */
//            Studentsinfo studentsinfo3 = this.studentsinfoService.selectByPrimaryKey(studentsinfo.getWechat());
//            /* 135 */
//            this.logger.info(studentsinfo3 != null ? studentsinfo3.getPhone() : "值为空");
//            /* 136 */
//            return WebUtil.results(1, "1000", "保存成功", studentsinfo3);
//            /*     */
//        }
//        /*     */
//        /* 139 */
//        Studentsinfo studentsinfo2 = this.studentsinfoService.selectByPrimaryKey(studentsinfo.getWechat());
//        /* 140 */
//        if (studentsinfo2 != null) {
//            /* 141 */
//            this.studentsinfoService.updateByPrimaryKey(studentsinfo);
//            /* 142 */
//            Studentsinfo studentsinfo3 = this.studentsinfoService.selectByPrimaryKey(studentsinfo.getWechat());
//            /* 143 */
//            this.logger.info(studentsinfo3 != null ? studentsinfo3.getPhone() : "值为空");
//            /* 144 */
//            return WebUtil.results(1, "1000", "保存成功", studentsinfo3);
//            /*     */
//        }
//        /* 146 */
//        studentsinfo.setPassword(MD5.encodeString("123456"));
//        /* 147 */
//        studentsinfo.setArea(Integer.valueOf(0));
//        /* 148 */
//        this.studentsinfoService.insert(studentsinfo);
//        /* 149 */
//        this.logger.info("插入一条个人信息成功");
//        /* 150 */
//        Studentsinfo studentsinfo3 = this.studentsinfoService.selectByPrimaryKey(studentsinfo.getWechat());
//        /* 151 */
//        this.logger.info(studentsinfo3 != null ? studentsinfo3.getPhone() : "值为空");
//        /* 152 */
//        return WebUtil.results(1, "1000", "插入信息成功", studentsinfo3);
//        /*     */
//    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
//    @RequestMapping({"/enter"})
//    /*     */
//    @ResponseBody
//    /*     */ public String enter(HttpServletResponse response, @RequestParam(name = "openid", required = true) String openid)
//    /*     */ {
//        /* 174 */
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        /* 175 */
//        if ((openid != null) && (!openid.trim().equals(""))) {
//            /* 176 */
//            Studentsinfo studentsinfo = this.studentsinfoService.selectByPrimaryKey(openid);
//            /* 177 */
//            if (studentsinfo != null) {
//                /* 178 */
//                this.logger.info("获取个人信息成功");
//                /* 179 */
//                return WebUtil.results(1, "0000", "获取信息成功", studentsinfo);
//                /*     */
//            }
//            /* 181 */
//            return WebUtil.results(1, "1000", "新用户", null);
//            /*     */
//        }
//        /*     */
//        /* 184 */
//        return WebUtil.results(0, "0002", "OPENID为空", null);
//        /*     */
//    }

}
