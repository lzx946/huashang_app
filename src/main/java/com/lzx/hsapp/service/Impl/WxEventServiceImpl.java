package com.lzx.hsapp.service.Impl;

import com.alibaba.fastjson.JSON;
import com.lzx.hsapp.constant.EventConstant;
import com.lzx.hsapp.dao.CourseMapper;
import com.lzx.hsapp.dao.ExpertsinfoMapper;
import com.lzx.hsapp.dao.SysFileMapper;
import com.lzx.hsapp.entity.Course;
import com.lzx.hsapp.entity.Expertsinfo;
import com.lzx.hsapp.entity.SysFile;
import com.lzx.hsapp.entity.msg.Article;
import com.lzx.hsapp.entity.msg.response.NewsResponseMessage;
import com.lzx.hsapp.entity.msg.response.TextResponseMessage;
import com.lzx.hsapp.service.CourseService;
import com.lzx.hsapp.service.ExpertService;
import com.lzx.hsapp.service.WxEventService;
import com.lzx.hsapp.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Component
public class WxEventServiceImpl implements WxEventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxEventServiceImpl.class);

    @Value("${preview.url}")
    private String preview;

    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ExpertsinfoMapper expertsinfoMapper;

    @Override
    public String processRequest(HttpServletRequest req) {
        //String message = "success";
        String respMessage = null;
        try {
            // 把微信返回的xml信息转义成map
            Map<String, String> map = MessageUtil.parseXml(req);
            //Map<String, String> map = XmlUtil.xmlToMap(req);
            System.out.println(JSON.toJSONString(map));
            boolean isreturn = false;

            String msgType = map.get("MsgType");
            if (msgType != null) {
//                if ("event".equals(map.get("MsgType"))) {
//                    log.info("2.1解析消息内容为：事件推送");
//                    if ("subscribe".equals(map.get("Event")) || "CLICK".equals(map.get("Event"))) {
//                        log.info("2.2用户第一次关注 返回true哦");
//                        isreturn = true;
//                    }
//                }

                //转换XML
                String fromUserName = map.get("FromUserName");// 消息来源用户标识
                String toUserName = map.get("ToUserName");// 消息目的用户标识
//                String msgType = map.get("MsgType");// 消息类型
                String eventKey = map.get("EventKey");// 消息内容
                String eventType = map.get("Event");

                if (msgType.equals(EventConstant.REQ_MESSAGE_TYPE_TEXT)) {
                    TextResponseMessage textResponseMessage = new TextResponseMessage();
                    textResponseMessage.setToUserName(fromUserName);
                    textResponseMessage.setFromUserName(toUserName);
                    textResponseMessage.setCreateTime(new Date().getTime());
                    textResponseMessage.setMsgType(EventConstant.RESP_MESSAGE_TYPE_TEXT);
                    textResponseMessage.setContent("hello！");
                    textResponseMessage.setFuncFlag(0);

                    respMessage = MessageUtil.textMessageToXml(textResponseMessage);

//                    result = msgService.returnText(map,"hello");
                } else if (msgType.equals(EventConstant.REQ_MESSAGE_TYPE_EVENT)) { //事件处理
//                    String event = map.get("Event");
                    if (eventType.equals(EventConstant.EVENT_TYPE_SUBSCRIBE)) { // 关注公众号
                        TextResponseMessage textResponseMessage = new TextResponseMessage();
                        textResponseMessage.setToUserName(fromUserName);
                        textResponseMessage.setFromUserName(toUserName);
                        textResponseMessage.setCreateTime(new Date().getTime());
                        textResponseMessage.setMsgType(EventConstant.RESP_MESSAGE_TYPE_TEXT);
                        textResponseMessage.setContent("感谢您的关注！");

                        respMessage = MessageUtil.textMessageToXml(textResponseMessage);
//                        result = msgService.returnText(map, message.getSubscribe());
                    } else if (eventType.equals(EventConstant.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
                        if (eventKey.equals("V1001_TODAY_MUSIC")) {
//                            result = msgService.returnText(map, "今日歌曲如下：");
                        }
                    } else if (eventType.equals(EventConstant.EVENT_TYPE_SCAN)) { // 扫码事件
                        Course course = courseMapper.findById(Integer.valueOf(eventKey));

                        LOGGER.info("eventKey:{}",eventKey);
                        LOGGER.info("course:{}",course);
                        LOGGER.info("courseID:{}",course.getId());

                        if (course != null){
                            NewsResponseMessage newsMessage = new NewsResponseMessage();
                            newsMessage.setToUserName(fromUserName);
                            newsMessage.setFromUserName(toUserName);
                            newsMessage.setCreateTime(new Date().getTime());
                            newsMessage.setMsgType(EventConstant.RESP_MESSAGE_TYPE_NEWS);
                            newsMessage.setFuncFlag(0);
                            List<Article> articleList = new ArrayList<Article>();

                            Article article = new Article();
                            article.setTitle(course.getName());
                            article.setDescription(course.getSummary());

                            Expertsinfo teacher = expertsinfoMapper.findById(course.getTeacherid());

                            if (teacher != null){
                                SysFile sysFile = sysFileMapper.selectByPrimaryKey(teacher.getPhotoid());
                                if (sysFile != null){
                                    article.setPicUrl(preview + sysFile.getUrl());
                                }
                            }
                            article.setUrl("http://24ry619755.wicp.vip/weixinphone/coursed/" + course.getId() + "#f");
                            articleList.add(article);
                            //多图文的话,新建多个article 放入articleList 中即可实现

                            // 设置图文消息个数
                            newsMessage.setArticleCount(articleList.size());
                            // 设置图文消息包含的图文集合
                            newsMessage.setArticles(articleList);
                            // 将图文消息对象转换成xml字符串
                            respMessage = MessageUtil.newsMessageToXml(newsMessage);
                        }else {
                            TextResponseMessage textResponseMessage = new TextResponseMessage();
                            textResponseMessage.setToUserName(fromUserName);
                            textResponseMessage.setFromUserName(toUserName);
                            textResponseMessage.setCreateTime(new Date().getTime());
                            textResponseMessage.setMsgType(EventConstant.RESP_MESSAGE_TYPE_TEXT);
                            textResponseMessage.setContent("此课程不存在！");

                            respMessage = MessageUtil.textMessageToXml(textResponseMessage);
                        }
                    }
                }
//                return respMessage;

            }
            return respMessage;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
