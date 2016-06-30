package com.tomasky.msp.core.support.processor;

import com.alibaba.fastjson.JSON;
import com.tomasky.msp.core.service.IMessageService;
import com.tomasky.msp.model.SmsMessage;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * Created by Administrator on 2015/5/15.
 */
public class SmsMessageProcessor implements MessageProcessor{

    private MongoTemplate mongoTemplate;
    private IMessageService messageService;

    @Override
    public void process(String messageJson) {
        SmsMessage sm = JSON.parseObject(messageJson, SmsMessage.class);
//        sm.setId(PKGenerator.generate());//改由message客户端build message时设置
        messageService.sendSmsMessage(sm);
        mongoTemplate.save(sm);
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }
}
