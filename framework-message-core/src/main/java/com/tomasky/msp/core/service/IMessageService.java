package com.tomasky.msp.core.service;

import com.tomasky.msp.core.support.MessageSendResult;
import com.tomasky.msp.enumeration.MessageType;
import com.tomasky.msp.model.*;

import java.util.List;

/**
 * 消息
 * Created by 番茄桑 on 2015/5/9.
 */
public interface IMessageService {
    /**
     * 将指定类型的消息对象放入队列
     * @param message
     */
    void sendMessage2Queue(Object message);

    /**
     * 根据客栈ID查询消息
     * @param key message key
     * @return list
     */
    List<Message> achieveMessageByKey(Integer key);

    /**
     * 发送短信类型的消息
     * @param smsMessage
     * @return result
     */
    MessageSendResult sendSmsMessage(SmsMessage smsMessage);


    /**
     * 发送推送消息
     * @param pushMessage
     * @return
     */
    MessageSendResult sendPushMessage(PushMessage pushMessage);

    /**
     * 发送站内消息
     * @param imsMessage
     * @return
     */
    MessageSendResult sendImsMessage(ImsMessage imsMessage);

    /**
     * 发送电子邮件消息
     * @param imsMessage
     * @return
     */
    MessageSendResult sendMailMessage(MailMessage imsMessage);

    /**
     * 发送微信消息
     * @param weChatMessage
     * @return
     */
    MessageSendResult sendWeChatMessage(WeChatMessage weChatMessage);

    /**
     * 从缓存中删除站内信
     * @param id
     * @return
     */
    void removeFromCache(String id);
}
