package com.tomasky.msp.core.support.processor;

import com.alibaba.fastjson.JSON;
import com.tomasky.msp.core.service.IMessageService;
import com.tomasky.msp.model.MailMessage;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * Created by Administrator on 2015/5/15.
 */
public class MailMessageProcessor implements MessageProcessor{

    private MongoTemplate mongoTemplate;
    private IMessageService messageService;

    @Override
    public void process(String messageJson) {
        MailMessage mm = JSON.parseObject(messageJson, MailMessage.class);
        mongoTemplate.save(mm);
        messageService.sendMailMessage(mm);
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }
}
