/*    */ package com.lzx.hsapp.util.weChatWebAccess;
/*    */ 
/*    */

/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeixinExtend
/*    */ {
/*    */   public String code()
/*    */   {
/* 13 */     OAuth2 oAuth2 = new OAuth2();
/* 14 */     String requestUrl = null;
/* 15 */     requestUrl = oAuth2.getOAuth2CodeBaseUrl(Configuration.getOAuthAppId(), Configuration.getProperty("weixin4j.oauth.url"));
/* 16 */     return requestUrl;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\hswx\WEB-INF\classes\!\top\tsama\baseapiserviceweb\weixinextend\WeixinExtend.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */