package com.tomasky.msp.core.support.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tomasky.msp.enumeration.MessageType;
import com.tomasky.msp.support.ContextContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("messageProcessorAdapter")
public class MessageProcessorAdapter{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessorAdapter.class);
    private String messageJson;
    private MessageProcessor messageProcessor;

    private MessageProcessor processorFromType(MessageType mType){
        if(MessageType.MAIL.equals(mType)){
            return (MessageProcessor) ContextContainer.getContext().getBean("mailMessageProcessor");
        }else if(MessageType.SMS.equals(mType)){
            return (MessageProcessor)ContextContainer.getContext().getBean("smsMessageProcessor");
        }else if(MessageType.PUSH.equals(mType)){
            return (MessageProcessor) ContextContainer.getContext().getBean("pushMessageProcessor");
        }else if(MessageType.IMS.equals(mType)){
            return (MessageProcessor) ContextContainer.getContext().getBean("imsMessageProcessor");
        }else if(MessageType.WECHAT.equals(mType)){
        	return  (MessageProcessor)ContextContainer.getContext().getBean("wechatMessageProcessor");
        }
        else {
            return null;
        }
    }

    public void process() {
        messageProcessor.process(messageJson);
    }

    /**
     *  ActiveMQ Support
     * @param message
     */
    public void setMessage(Object message) {
        if(message instanceof String){
            LOGGER.debug("【解析处理器------------start】");
            JSONObject jsonObject = JSON.parseObject(message.toString());
            this.messageProcessor = processorFromType(jsonObject.getObject("messageType", MessageType.class));
            if(messageProcessor == null){
                throw new RuntimeException("处理器解析结果为null");
            }
            LOGGER.debug(String.format("【解析处理器， 解析结果:%s】", messageProcessor.getClass().toString()));
            this.messageJson = jsonObject.toJSONString();
        }else{
            throw new RuntimeException("【设置消息时数据类型异常】");
        }
    }

    public static void main(String[] args) {
        new MessageProcessorAdapter().setMessage("{\"content\":\"{\"alertTime\":1449132290067,\"channelId\":11,\"content\":\"水电费了坚实的路口附近222222222\",\"data\":\"{\"content\":\"222222222222\",\"createFlag\":1,\"icon\":1,\"id\":950493,\"isTop\":false,\"remindTime\":\"2015-11-25 10:43\",\"status\":1}\",\"hasRead\":false,\"id\":\"11-1449131990210-7205\",\"imsMessageType\":\"ALERT_MESSAGE\",\"imsType\":\"remind_alert\",\"innId\":11,\"messageType\":\"IMS\",\"receiver\":[],\"sendTime\":1449131990210,\"sender\":\"sender\",\"success\":true,\"title\":\"title\",\"valid\":false}\",\"date\":1449131990553,\"messageType\":\"BIZ_MESSAGE\"}");

    }

}
