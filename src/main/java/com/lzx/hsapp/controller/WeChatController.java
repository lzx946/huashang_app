package com.lzx.hsapp.controller;

import com.lzx.hsapp.constant.Constant;
import com.lzx.hsapp.entity.msg.Message;
import com.lzx.hsapp.service.WeChatService;
import com.lzx.hsapp.service.msg.IMsgService;
import com.lzx.hsapp.util.XmlUtil;
import com.lzx.hsapp.util.aes.AesException;
import com.lzx.hsapp.util.aes.WXBizMsgCrypt;
import com.lzx.hsapp.utils.CheckOutUtil;
import com.lzx.hsapp.utils.WeiXinResult;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信接入
 */
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private IMsgService msgService;

    @Autowired
    private Message message;

    @RequestMapping(value = "/tokenVerify",produces = "application/json;charset=utf-8")
    public String tokenVerify(HttpServletRequest request, HttpServletResponse response) {
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

        // 微信加密签名
        String msgSignature = request.getParameter("signature");
        // 时间戳
        String timeStamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echoStr = request.getParameter("echostr");
        Map<String, String> map = null;
        String msgType = null;

        message.setSubscribe("感谢您的关注！");

        try {
            // 解析xml数据，将解析结果存储在HashMap中
            map = new HashMap<>();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(request.getInputStream());
            // 得到xml根元素
            Element root = document.getRootElement();
            XmlUtil.parserXml(root, map);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println("key:" + entry.getKey() + "-value:" + entry.getValue());
            }
            if (null != map && !map.isEmpty()) {
                // 消息类型
                msgType = map.get("MsgType");
            }
        } catch (DocumentException e) {
        } catch (IOException e) {
        }

        String result = null;
        try {
            if (StringUtils.isEmpty(msgType)) {
                // 创建加密类
                WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(Constant.WechatAccount.TOKEN, Constant.WechatAccount.ENCODINGAESKEY, Constant.WechatAccount.APPID);
                // 比对msgSignature 用token, timeStamp, nonce加密的参数是否一致，一致证明该接口来自微信，异常则不是来自微信
                result = wxcpt.verifyUrl_WXGZ(msgSignature, Constant.WechatAccount.TOKEN, timeStamp, nonce, echoStr);
            } else {
                if (msgType.equals(Constant.MsgType.TEXT)) {
                    result = msgService.returnText(map,"hello");
                } else if (msgType.equals(Constant.MsgType.EVENT)) { //事件处理
                    String event = map.get("Event");
                    if (event.equals(Constant.Event.SUBSCRIBE)) { // 关注公众号
                        result = msgService.returnText(map, message.getSubscribe());
                    } else if (event.equals(Constant.Event.CLICK)) { // 自定义菜单点击事件
                        String eventKey = map.get("EventKey");
                        if (eventKey.equals("V1001_TODAY_MUSIC")) {
                            result = msgService.returnText(map, "今日歌曲如下：");
                        }
                    } else if (event.equals(Constant.Event.SCAN)) { // 扫码事件
                        String eventKey = map.get("EventKey");
                        if (eventKey.equals("temp_qrcode_test")) { //临时二维码
                            result = msgService.returnText(map, "扫描临时二维码");
                        } else if (eventKey.equals("44")) {
                            result = msgService.returnText(map, "课程ID：" + eventKey);
                        }
                    }
                }
            }
        } catch (AesException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping(value = "/getAccessToken",method = RequestMethod.POST)
    @ResponseBody
    public String getAccessToken(){
        return weChatService.getAccessToken();
    }

    @RequestMapping(value = "/getQRCode",method = RequestMethod.POST)
    @ResponseBody
    public WeiXinResult getQRCode(@Param("courseId") Integer courseId){

        String ticket = weChatService.createForeverTicket(courseId);

        try {
            return weChatService.showQrcode(ticket,"/");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
