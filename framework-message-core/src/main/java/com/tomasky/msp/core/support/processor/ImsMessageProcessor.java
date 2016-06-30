package com.tomasky.msp.core.support.processor;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.alibaba.fastjson.JSON;
import com.tomasky.msp.core.service.ImsService;
import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.ImsMessageType;

/**
 *
 * Created by Administrator on 2015/5/15.
 */
public class ImsMessageProcessor implements MessageProcessor{

    private MongoTemplate mongoTemplate;
    
    @Autowired
    private ImsService ImsService;
    
    static Logger logger = Logger.getLogger(ImsMessageProcessor.class);

	@Override
    public void process(String messageJson) {
    	logger.info("IMS消息准备保存");
        ImsMessage newIm = JSON.parseObject(messageJson, ImsMessage.class);
        if( newIm.getImsMessageType().equals(ImsMessageType.ALERT_MESSAGE) ){
        	List<ImsMessage> oldIms = null;
        	oldIms = ImsService.findNotMarkAlertMessage(newIm.getOrderId());
        	for (ImsMessage imsMessage : oldIms) {
        			newIm.setId(imsMessage.getId());
        			imsMessage.setAlertTime(newIm.getAlertTime());
        			break;
        	}
        }
        logger.info("-------开始保存到mongo--------");
        mongoTemplate.save(newIm);
        logger.info(messageJson+"创建成功");
        
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
