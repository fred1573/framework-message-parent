package com.tomasky.msp.client.service;

import java.rmi.RemoteException;

import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.Message;
import com.tomasky.msp.model.Page;

/**
 * 消息服务接口
 * Created by 番茄桑 on 2015/5/11.
 */
public interface IMessageManageService {
    /**
     * 发送消息
     *
     * @param message 消息对象
     */
    void sendMessage(Message message);

    /**
     * 根据客栈ID ，渠道ID 查询所有的消息
     *
     * @param innId 客栈ID
     * @return 分页对象
     */
    Page<ImsMessage> getAllImsMessages(int innId, int channelId, String type, int pageIndex, int pageSize);

    /**
     * 根据客栈ID ，渠道ID 查询所有的未读消息
     *
     * @param innId 客栈ID
     * @return 分页对象
     */
    Page<ImsMessage> getUnreadImsMessages(int innId, int channelId, String type, int pageIndex, int pageSize);


    /**
     * 标记消息已读
     *
     * @param markRead 是否将消息置为已读
     * @return 200 :成功
     * 201 :参数错误
     */
    Integer updateMessageRead(String messageId, Object ownContent, boolean markRead);


    /**
     * 标记消息已读
     *
     * @param messageId
     * @return 200 :成功
     * 201 :参数错误
     */
    Integer updateMessageRead(String messageId);

    /**
     * 获取当前有多少条未读消息
     */
    Integer getUnreadCount(Integer inneId, Integer channelId, String type);


    /**
     * 根据客栈Id，渠道ID分页查询未读消息，但是此消息一旦获取成功
     * 服务端就会标记当前消息为已状态
     *
     * @param innId 客栈ID
     * @param
     * @return 客栈未读站内消息集合
     */
    Page<ImsMessage> pollImsMessages(int innId, int channelId, String type, int pageIndex, int pageSize);


    /**
     * 调用远程方法，根据客栈Id，渠道ID分页查询未读消息，但是此消息一旦获取成功
     * 服务端就会标记当前消息为已状态
     *
     * @param innId 客栈ID
     * @param
     * @return 客栈未读站内消息集合
     * @throws RemoteException
     */
    Page<ImsMessage> rmiPollImsMessages(int innId, int channelId, String type, int pageIndex, int pageSize) throws RemoteException;


    /**
     * 调用远程方法，根据客栈Id，渠道ID分页查询未读消息
     *
     * @param innId 客栈ID
     * @param
     * @return 客栈未读站内消息集合
     * @throws RemoteException
     */
    Page<ImsMessage> rmiUnreadImsMessages(int innId, int channelId, String type, int pageIndex, int pageSize) throws RemoteException;

    /**
     * 根据消息ID（mogodb生成的ID），删除redis中的消息对象
     *
     * @param mgId mogodb生成的ID
     */
    void removeMessage(String mgId);

    /**
     * 根据msgId获取消息
     * @return
     */
    ImsMessage getImsMessageById(String msgId);

}
