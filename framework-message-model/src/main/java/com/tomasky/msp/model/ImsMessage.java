package com.tomasky.msp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 站内消息
 * Created by 番茄桑 on 2015/5/11.
 */
@Document(collection = "message_ims")
public class ImsMessage extends Message {
  
	private static final long serialVersionUID = -2392616867726676098L;
	// 客栈ID
    private int innId;
    // 渠道ID
    private int channelId;
    // 消息标题
    private String title;
    // 用于业务系统保存自定义数据
    private Object data;
    
    private Boolean hasRead = false;
    
    private Boolean valid  = true;
    
    private String imsType;
    
    // 弹窗时间,只有在这个时间点的消息才会有效
    private Date alertTime;
    
    //失效时间，到了这个时间点。未读列表将获取不到改消息
    private Date invalidTime ; 
    
    private String orderId;
    
    private ImsMessageType imsMessageType = ImsMessageType.COMMON_MESSAGE;
    
    
    public ImsMessageType getImsMessageType() {
		return imsMessageType;
	}

	public void setImsMessageType(ImsMessageType imsMessageType) {
		this.imsMessageType = imsMessageType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
		this.data = data;
    }

	public void setData(String data) {
		this.data = data;
	}

	public Boolean getHasRead() {
		return hasRead;
	}

	public void setHasRead(Boolean hasRead) {
		this.hasRead = hasRead;
	}

	public String getImsType() {
		return imsType;
	}

	public void setImsType(String imsType) {
		this.imsType = imsType;
	}

	public Boolean getValid() {
		Long curTime = System.currentTimeMillis();
		switch (this.getImsMessageType()) {
		case ALERT_MESSAGE:
			valid = curTime > this.getAlertTime().getTime();
			break;
		case EXPIRE_MESSAGE:
			valid = curTime < this.getInvalidTime().getTime();
			break;
		default:
			valid = true;
		}	
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	public void markMessageRead(){
		this.hasRead = true;
	}
    
}
