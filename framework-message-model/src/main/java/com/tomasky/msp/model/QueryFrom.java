package com.tomasky.msp.model;

import java.io.Serializable;

public class QueryFrom  implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;

	private Integer innId;
	
	private Integer channelId;
	
	private String type;
	
	public Integer getInnId() {
		return innId;
	}
	
	public void setInnId(Integer innId) {
		this.innId = innId;
	}
	
	public Integer getChannelId() {
		return channelId;
	}
	
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	

}
