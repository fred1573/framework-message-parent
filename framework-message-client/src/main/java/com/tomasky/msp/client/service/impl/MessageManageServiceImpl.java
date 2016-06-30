package com.tomasky.msp.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.tomasky.msp.client.handler.ImsMessageHandler;
import com.tomasky.msp.client.handler.IntgerHandler;
import com.tomasky.msp.client.handler.PageImsMessageHandler;
import com.tomasky.msp.client.service.IMessageManageService;
import com.tomasky.msp.client.support.MessageQueryBuild;
import com.tomasky.msp.client.support.PollHelper;
import com.tomasky.msp.client.support.PropertiesReader;
import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.Message;
import com.tomasky.msp.model.Page;
import com.tomasky.msp.model.QueryFrom;
import com.tomasky.msp.support.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息服务接口实现类
 * Created by 番茄桑 on 2015/5/11.
 */
public class MessageManageServiceImpl implements IMessageManageService {

    @Override
    public void sendMessage(Message message) {
        if (message != null) {
            Map<String, Object> params = new HashMap<>();
            params.put("messageJson", JSON.toJSONString(message));
            params.put("messageType", message.getMessageType());
            HttpClientUtils.httpPost(PropertiesReader.getSendMessageUrl(), params);
        }
    }

    @Override
    public Page<ImsMessage> pollImsMessages(int innId, int channelId, String type, int pageIndex, int pageSize) {
        Map<String, Object> params = new MessageQueryBuild.Builder(innId, channelId, pageIndex, pageSize).type(type).build().buildQuery();
        String result = HttpClientUtils.httpGet(PropertiesReader.getUnreadMessagePoll(), params);
        return new PageImsMessageHandler().handler(result);
    }

    @Override
    public void removeMessage(String mgId) {
        Map<String, Object> params = new HashMap<>();
        params.put("mgId", mgId);
        HttpClientUtils.httpPost(PropertiesReader.getRemoveMessageUrl(), params);
    }


    @Override
    public Page<ImsMessage> getAllImsMessages(int innId, int channelId, String type,
                                              int pageIndex, int pageSize) {
        Map<String, Object> params = new MessageQueryBuild.Builder(innId, channelId, pageIndex, pageSize).type(type).build().buildQuery();
        String result = HttpClientUtils.httpGet(PropertiesReader.getAllMessaage(), params);
        Page<ImsMessage> page = new PageImsMessageHandler().handler(result);
        return page;
    }

    @Override
    public Integer updateMessageRead(String messageId, Object ownContent, boolean markRead) {
        ImsMessage ims = getImsMessageById(messageId);
        if (null == ims) {
            throw new RuntimeException(messageId + "错误的ID");
        }
        if (null != ownContent) {
            ims.setData(JSON.toJSONString(ownContent));
        }
        Map<String, Object> params = new HashMap<>();
        params.put("messageJson", JSON.toJSONString(ims));
        params.put("markRead", markRead);
        String result = HttpClientUtils.httpPost(PropertiesReader.getUpdateMessageRead(), params);
        return new IntgerHandler().handler(result);
    }


    public Integer updateMessageRead(String messageId) {
        return updateMessageRead(messageId, null, true);
    }

    @Override
    public Page<ImsMessage> getUnreadImsMessages(int innId, int channelId, String type,
                                                 int pageIndex, int pageSize) {
        Map<String, Object> params = new MessageQueryBuild.Builder(innId, channelId, pageIndex, pageSize).type(type).build().buildQuery();
        String result = HttpClientUtils.httpGet(PropertiesReader.getUnreadMessage(), params);
        return new PageImsMessageHandler().handler(result);
    }

    @Override
    public Integer getUnreadCount(Integer innId, Integer channelId, String type) {
        Map<String, Object> params = new MessageQueryBuild.Builder(innId, channelId).type(type).build().buildQuery();
        String result = HttpClientUtils.httpGet(PropertiesReader.getUnreadMessageCount(), params);
        return new IntgerHandler().handler(result);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<ImsMessage> rmiPollImsMessages(int innId, int channelId,
                                               String type, int pageIndex, int pageSize) {
        Page<ImsMessage> page = new Page<>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        QueryFrom queryFrom = new QueryFrom();
        queryFrom.setInnId(innId);
        queryFrom.setChannelId(channelId);
        queryFrom.setType(type);
        return (Page<ImsMessage>) (PollHelper.poll(page, queryFrom).getData());
    }

    @Override
    public Page<ImsMessage> rmiUnreadImsMessages(int innId, int channelId,
                                                 String type, int pageIndex, int pageSize) {
        Page<ImsMessage> page = new Page<>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        QueryFrom queryFrom = new QueryFrom();
        queryFrom.setInnId(innId);
        queryFrom.setChannelId(channelId);
        queryFrom.setType(type);
        return (Page<ImsMessage>) (PollHelper.unread(page, queryFrom).getData());
    }

    @Override
    public ImsMessage getImsMessageById(String msgId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", msgId);
        String result = HttpClientUtils.httpGet(PropertiesReader.getMessageById(), params);
        return new ImsMessageHandler().handler(result);
    }
}
