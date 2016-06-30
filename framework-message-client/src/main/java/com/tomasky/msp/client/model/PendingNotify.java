package com.tomasky.msp.client.model;

import java.util.List;

public class PendingNotify {

	/**
	 *  用于第一行字自定义内容
	 */
	private String tip;
	
	/**
	 * 用于最后一行自定义内容
	 */
	private String description;
	
	
	/**
	 *  待处理任务
	 */
	private String pendingTask;
	
	/**
	 *  通知类型
	 */
	private String notifyType;
	
	/**
	 *  通知时间
	 */
	private String notifyTime;
	
	/**
	 *  消息接受者
	 */
	private List<String>  receivers ; //接收人的iopenid

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPendingTask() {
		return pendingTask;
	}

	public void setPendingTask(String pendingTask) {
		this.pendingTask = pendingTask;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public List<String> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}
	
	
	
}
