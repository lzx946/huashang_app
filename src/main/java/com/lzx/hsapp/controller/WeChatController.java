package com.lzx.hsapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzx.hsapp.constant.Constant;
import com.lzx.hsapp.entity.AccessToken;
import com.lzx.hsapp.entity.msg.Message;
import com.lzx.hsapp.service.EnlistService;
import com.lzx.hsapp.service.WeChatService;
import com.lzx.hsapp.service.WxEventService;
import com.lzx.hsapp.service.msg.IMsgService;

import com.lzx.hsapp.util.SignUtil;
import com.lzx.hsapp.util.XmlUtil;
import com.lzx.hsapp.util.aes.AesException;
import com.lzx.hsapp.util.aes.WXBizMsgCrypt;
import com.lzx.hsapp.util.mapUtil.GetLocationMsg;
import com.lzx.hsapp.util.mapUtil.Gps;
import com.lzx.hsapp.util.mapUtil.PositionUtil;
import com.lzx.hsapp.util.mapUtil.UserDetails;
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
@RestController
@RequestMapping("/wechat")
@CrossOrigin(origins = "*")
public class WeChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private EnlistService enlistService;

    @Autowired
    private WxEventService wxEventService;

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
    public Result<String> signIn(@RequestBody Map<String, String> model) {              //, HttpServletRequest request
//        String latitude = request.getParameter("latitude");
//        String longitude = request.getParameter("longitude");
        String latitude = model.get("latitude");
        String longitude = model.get("longitude");
        Integer studentId = Integer.valueOf(model.get("studentId"));
        Integer courseId = Integer.valueOf(model.get("courseId"));
        System.out.println(latitude);
        System.out.println(longitude);
        if (latitude!=null & longitude!=null) {
            double lat = Double.valueOf(latitude).doubleValue();
            double lon = Double.valueOf(longitude).doubleValue();

            // 微信是GPS需要转化地图
            Gps gps = new Gps(lat, lon);
            LOGGER.info("gps:{}",gps);
            Gps gcj = PositionUtil.gps84_To_Gcj02(gps.getWgLat(), gps.getWgLon());
            LOGGER.info("gcj:{}",gcj);
            String jsonStr = GetLocationMsg.GetLocationMs(gcj.getWgLat(), gcj.getWgLon());
            LOGGER.info("jsonStr:{}",jsonStr);

            // 因为嵌套太多先解析
            JSONObject jsonObj = JSONArray.parseObject(jsonStr);
            JSONArray jsonArray = (JSONArray) jsonObj.get("results");
            List list = new ArrayList();// 用list保存全部数据
            for (int i = 0; i < jsonArray.size(); i++) {
                UserDetails user = (UserDetails) JSONObject.toJavaObject(jsonArray.getJSONObject(i), UserDetails.class);
                // System.out.println(user.toString());
                list.add(user.getFormatted_address());
            }
            String json = JSON.toJSONString(list); // list转json
            LOGGER.info("json:{}",json);
            System.out.println(json);
            // 页面上
//            return json;
            return enlistService.signIn(courseId,studentId);
        }else {
            return Result.result("NACK","未获取到位置信息");
        }

    }
}
