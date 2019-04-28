package com.lzx.hsapp.service.Impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.lzx.hsapp.dao.AccessTokenMapper;
import com.lzx.hsapp.entity.AccessToken;
import com.lzx.hsapp.entity.WeiXinQRCode;
import com.lzx.hsapp.service.WeChatService;
import com.lzx.hsapp.util.HttpRequest;
import com.lzx.hsapp.utils.HttpRequestUtil;
import com.lzx.hsapp.utils.WeiXinResult;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
@Component
public class WeChatServiceImpl implements WeChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServiceImpl.class);

    // 临时二维码
    private final static String QR_SCENE = "QR_SCENE";
    // 永久二维码
    private final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
    // 永久二维码(字符串)
    private final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
    // 创建二维码
    private String create_ticket_path = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    // 通过ticket换取二维码
    private String showqrcode_path = "https://mp.weixin.qq.com/cgi-bin/showqrcode";

    private static String appID = "wxf58544e27a43135f";

    private static String appSecret = "20d998726a507348901e3afdf88eccfa";

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    @Autowired
    private HttpRequestUtil httpRequestUtil;

    /**
     * 创建临时带参数二维码
     * @expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @param sceneId 场景Id
     * @return
     */
    @Override
    public String createTempTicket(String expireSeconds, int sceneId) {             //String accessToken,
        WeiXinQRCode wxQRCode = null;

        AccessToken token = accessTokenMapper.findById(1);

        String accessToken = token.getAccessToken();

        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token", accessToken);
        Map<String,Integer> intMap = new HashMap<String,Integer>();
        intMap.put("scene_id",sceneId);
        Map<String,Map<String,Integer>> mapMap = new HashMap<String,Map<String,Integer>>();
        mapMap.put("scene", intMap);
        //
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("expire_seconds", expireSeconds);
        paramsMap.put("action_name", QR_SCENE);
        paramsMap.put("action_info", mapMap);
        String data = new Gson().toJson(paramsMap);
        data = HttpRequestUtil.HttpsDefaultExecute(HttpRequestUtil.POST_METHOD,create_ticket_path,params,data);
        try {
            wxQRCode = new Gson().fromJson(data, WeiXinQRCode.class);
        } catch (JsonSyntaxException e) {
            wxQRCode = null;
            e.printStackTrace();
        }
        return wxQRCode==null?null:wxQRCode.getTicket();

    }

    /**
     * 创建永久二维码(数字)
     * @param sceneId 场景Id
     * @return
     */
    @Override
    public String createForeverTicket(int sceneId) {                //String accessToken,

        AccessToken token = accessTokenMapper.findById(1);

        String accessToken = token.getAccessToken();

        LOGGER.info("token:{}",token);

        LOGGER.info("access_token:{}",accessToken);

        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token", accessToken);
        //output data
        Map<String,Integer> intMap = new HashMap<String,Integer>();
        intMap.put("scene_id",sceneId);
        Map<String,Map<String,Integer>> mapMap = new HashMap<String,Map<String,Integer>>();
        mapMap.put("scene", intMap);

        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("action_name", QR_LIMIT_SCENE);
        paramsMap.put("action_info", mapMap);
        String data = new Gson().toJson(paramsMap);
        data =  HttpRequestUtil.HttpsDefaultExecute(HttpRequestUtil.POST_METHOD,create_ticket_path,params,data);
        WeiXinQRCode wxQRCode = null;
        try {
            wxQRCode = new Gson().fromJson(data, WeiXinQRCode.class);
        } catch (JsonSyntaxException e) {
            wxQRCode = null;
            e.printStackTrace();
        }

        LOGGER.info("ticket:{}",wxQRCode.getTicket());

        return wxQRCode==null?null:wxQRCode.getTicket();
    }

    /**
     * 创建永久二维码(字符串)
     *
     * @param sceneStr 场景str
     * @return
     */
    @Override
    public String createForeverStrTicket(String sceneStr){                  //String accessToken,

        AccessToken token = accessTokenMapper.findById(1);

        String accessToken = token.getAccessToken();

        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("access_token", accessToken);
        //output data
        Map<String,String> intMap = new HashMap<String,String>();
        intMap.put("scene_str",sceneStr);
        Map<String,Map<String,String>> mapMap = new HashMap<String,Map<String,String>>();
        mapMap.put("scene", intMap);

        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("action_name", QR_LIMIT_STR_SCENE);
        paramsMap.put("action_info", mapMap);
        String data = new Gson().toJson(paramsMap);
        data =  HttpRequestUtil.HttpsDefaultExecute(HttpRequestUtil.POST_METHOD,create_ticket_path,params,data);
        WeiXinQRCode wxQRCode = null;
        try {
            wxQRCode = new Gson().fromJson(data, WeiXinQRCode.class);
        } catch (JsonSyntaxException e) {
            wxQRCode = null;
        }
        return wxQRCode==null?null:wxQRCode.getTicket();
    }

    /**
     * 获取二维码ticket后，通过ticket换取二维码图片展示
     * @param ticket
     * @return
     */
    @Override
    public String showQrcode(String ticket){
        Map<String,String> params = new TreeMap<String,String>();
        params.put("ticket", HttpRequestUtil.urlEncode(ticket, HttpRequestUtil.DEFAULT_CHARSET));
        try {
            showqrcode_path = HttpRequestUtil.setParmas(params,showqrcode_path,"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showqrcode_path;
    }

    /**
     * 获取二维码ticket后，通过ticket换取二维码图片
     * @param ticket
     * @param savePath  保存的路径,例如 F:\\test\test.jpg
     * @return      Result.success = true 表示下载图片下载成功
     */
    @Override
    public WeiXinResult showQrcode(String ticket, String savePath) throws Exception{
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("ticket", HttpRequestUtil.urlEncode(ticket, HttpRequestUtil.DEFAULT_CHARSET));
        WeiXinResult result = httpRequestUtil.downMeaterMetod(params,HttpRequestUtil.GET_METHOD,showqrcode_path,savePath);       //,savePath
        return result;
    }

    @Override
    public String getAccessTokenById(Integer id){
        AccessToken accessToken = accessTokenMapper.findById(id);

        if (accessToken != null){
            return accessToken.getAccessToken();
        }else {
            return null;
        }
    }

    /**
     * 获取jsapi_ticket 调用微信JS接口的临时票据
     * @return
     */
    @Override
    public String getTicket(String accessToken) {
        String jsapiTicket = null;
        Map<String,String> params = new TreeMap<String,String>();
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
        params.put("access_token",accessToken);
        params.put("type", "jsapi");
        String result = null;
        try {
            result = HttpRequestUtil.defaultConnection("GET",url,5000,5000,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(StringUtils.isNotBlank(result)){
            LOGGER.info("result:{}",result);
            jsapiTicket = (String) JSONObject.fromObject(result).get("ticket");
            return jsapiTicket;
        }

        return null;
    }

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
