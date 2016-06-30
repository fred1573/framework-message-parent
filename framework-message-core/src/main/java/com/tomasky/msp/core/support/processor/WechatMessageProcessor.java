package com.tomasky.msp.core.support.processor;

import com.alibaba.fastjson.JSON;
import com.tomasky.msp.core.service.IMessageService;
import com.tomasky.msp.model.WeChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * Created by Administrator on 2015/5/15.
 */
public class WechatMessageProcessor implements MessageProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatMessageProcessor.class);

    private MongoTemplate mongoTemplate;
    private IMessageService messageService;

    @Override
    public void process(String messageJson) {
        LOGGER.debug(String.format("【适配器处理微信消息，消息内容:%s】", messageJson));
    	WeChatMessage wcm = JSON.parseObject(messageJson, WeChatMessage.class);
//        im.setId(PKGenerator.generate(im.getInnId()));//改由客户端build message时设置
        LOGGER.debug("【消息保存到mongodb--------start】");
        mongoTemplate.save(wcm);
        LOGGER.debug("【消息保存到mongodb--------end】");
        LOGGER.debug("【发送微信到用户--------start】");
        messageService.sendWeChatMessage(wcm);
        LOGGER.debug("【发送微信到用户--------end】");
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }
}
