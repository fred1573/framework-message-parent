package com.tomasky.msp.model;

import java.util.Date;

public class ImsModel {

	public ImsModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ImsModel(int channelId,int innId,String type,String title,String content) {
		this.channelId = channelId;
		this.innId = innId;
		this.type = type;
		this.title = title;
		this.content = content;
	}
	
	public ImsModel(int channelId,int innId,String type,String title,String content,String orderId) {
		this.channelId = channelId;
		this.innId = innId;
		this.type = type;
		this.title = title;
		this.content = content;
		this.orderId = orderId;
	}
	
	
	private  int innId;
	private int channelId; 
	private String sender;
	private String title;
	private String content; 
	private String type;
	private Date alertTime;
	private Date invalidTime; 
	private Object data;
	
	private String orderId;
	
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public int getInnId() {
		return innId;
	}
	public void setInnId(int innId) {
		this.innId = innId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getAlertTime() {
		return alertTime;
	}
	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}
	public Date getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
