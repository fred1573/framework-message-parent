package com.tomasky.msp.core.service;

import java.util.List;

import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.Page;
import com.tomasky.msp.model.QueryFrom;


public interface ImsService{
	
	/**
	 *  设置消息已读
	 * @param ims
	 */
	 void setMessageRead(ImsMessage ims);
	 

	 
	 /**
	  *  获取未读列表
	  * @param innId 客栈ID
	  * @param channelId 渠道ID
	  * @param type 消息类型
	  * @param hasRead 是否已读
	  * @return 总数
	  */
	 Long countUnreadMessage(QueryFrom queryFrom);

	 
	 
	 /**
	  *  保存IMS消息
	  * @param ims
	  */
	 void saveOrUpdate(ImsMessage ims);

	 /**
	  *  通过ID查询站内信
	  * @param id
	  * @return
	  */
	 ImsMessage findOne(String id);
	 
	 /**
	  *  查询订单的定时消息（未触发的定时消息）
	  * @param orderId
	  * @param valid
	  * @return
	  */
	 List<ImsMessage> findNotMarkAlertMessage(String orderId);

	 
	 /**
	  *  分页查询所有消息
	  * @param page 分页对象
	  * @param innId 客栈ID
	  * @param channelId 渠道ID
	  * @param type 消息类型
	  * @param hasRead 是否已读
	  * @return 分页对象
	  */
	 Page<ImsMessage> pageAll(Page<ImsMessage> page,QueryFrom queryFrom);
	 
	 
	 /**
	  *  获取未读消息列表
	  * @param page 分页对象
	  * @param innId 客栈ID
	  * @param channelId 渠道ID
	  * @param type 消息类型
	  * @return 分页对象
	  */
	 Page<ImsMessage> pageUnRead(Page<ImsMessage> page,QueryFrom queryFrom);
	 
	 /**
	  *  分页拉取消息（成功返回后将标记消息为已读状态）
	  * @param page 分页对象
	  * @param innId 客栈ID
	  * @param channelId 渠道ID
	  * @param type 消息类型
	  * @return 分页对象
	  */
	 Page<ImsMessage> pageAndMarkMessageRead(Page<ImsMessage> page,QueryFrom queryFrom);

	/**
	 * data字段格式转换，convert obj to jsonstr
	 * @return num  受影响的记录数
     */
	long jsonFormatRepair();
}
