package com.tomasky.msp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.tomasky.msp.enumeration.MessageType;
import com.tomasky.msp.enumeration.SmsChannel;

/**
 * 手机短信
 * Created by 番茄桑 on 2015/5/11.
 */
@Document(collection = "message_sms")
public class SmsMessage extends Message {
	
	private static final long serialVersionUID = 1L;

	public SmsMessage() {
		this.setMessageType(MessageType.SMS);
	}
	
	private SmsChannel channel;

	public SmsChannel getChannel() {
		return channel;
	}

	public void setChannel(SmsChannel channel) {
		this.channel = channel;
	}

}
