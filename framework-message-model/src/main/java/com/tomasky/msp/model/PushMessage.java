package com.tomasky.msp.model;

import java.util.List;

import com.tomasky.msp.enumeration.PushChannel;
import com.tomasky.msp.enumeration.PushPlatform;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 移动客户端推送消息
 * Created by 番茄桑 on 2015/5/11.
 */
@Document(collection = "message_push")
public class PushMessage extends Message {
    // 推送消息的来源渠道
    private PushChannel pushChannel;
    // 要推送的手机客户端类型
    private PushPlatform pushPlatform;
    // 推送消息的标题
    private String title;
    
    private List<String> androidReceivers;
    
    
    private List<String> iosReceivers;
    
    public List<String> getAndroidReceivers() {
		return androidReceivers;
	}

	public void setAndroidReceivers(List<String> androidReceivers) {
		this.androidReceivers = androidReceivers;
	}

	public List<String> getIosReceivers() {
		return iosReceivers;
	}

	public void setIosReceivers(List<String> iosReceivers) {
		this.iosReceivers = iosReceivers;
	}

	public PushChannel getPushChannel() {
        return pushChannel;
    }

    public void setPushChannel(PushChannel pushChannel) {
        this.pushChannel = pushChannel;
    }

    public PushPlatform getPushPlatform() {
        return pushPlatform;
    }

    public void setPushPlatform(PushPlatform pushPlatform) {
        this.pushPlatform = pushPlatform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
