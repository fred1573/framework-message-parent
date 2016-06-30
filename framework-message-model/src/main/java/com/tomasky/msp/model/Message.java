package com.tomasky.msp.model;

import com.tomasky.msp.enumeration.MessageType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消息基类
 * Created by 番茄桑 on 2015/5/9.
 */
public class Message implements Serializable {
    // 消息内容
    private String content;
    // 消息发送人
    private String sender;
    // 消息接收人列表
    private List<String> receiver = new ArrayList<String>();
    // 消息类型
    private MessageType messageType;
    // 消息的ID用于保存redis
    private String id;
    // 消息发送的时间
    private Date sendTime = new Date();
    //是否发送成功
    private boolean success = true;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
