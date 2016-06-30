package com.tomasky.msp.client.support;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class MessageQueryBuild {
  
	private Integer innId;
	
	private Integer channelId;
	
	private String type;
	
	private Integer pageIndex;
	
	private Integer pageSize;
	
    public static class Builder {
      
    	private Integer innId;
    	
    	private Integer channelId;
    	
    	private String type;
    	
    	private Integer pageIndex = 1;
    	
    	private Integer pageSize = 10;
    	
    	public Builder(Integer innId,Integer channelId, Integer pageIndex,Integer pageSize) {
			this.innId = innId;
			this.channelId = channelId;
			this.pageIndex = pageIndex;
			this.pageSize = pageSize;
		}
    	
      	public Builder(Integer innId,Integer channelId) {
    			this.innId = innId;
    			this.channelId = channelId;
    		}
    	
    	
    	public Builder  innId(Integer innId){
    		this.innId = innId;
    		return this;
    	}
    	
    	public Builder  channelId(Integer channelId){
    		this.channelId = channelId;
    		return this;
    	}
    	
    	public Builder  type(String type){
    		this.type = type;
    		return this;
    	}
    	
    	public Builder pageIndex(Integer pageIndex){
    		this.pageIndex = pageIndex;
    		return this;
    	}
    	
    	public Builder pageSize(Integer pageSize){
    		this.pageSize = pageSize;
    		return this;
    	}
    	
 
        public MessageQueryBuild build() { 
            return new MessageQueryBuild(this);
        }
    }
 
    private MessageQueryBuild(Builder b) {
        innId = b.innId;
        channelId = b.channelId;
        type = b.type;
        pageIndex = b.pageIndex;
        pageSize = b.pageSize;
    }
    
    public Map<String, Object> buildQuery() {
        
    	Map<String, Object> params = new HashMap<>();
		params.put("innId", innId);
		params.put("channelId", channelId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		if(StringUtils.isNotEmpty(type)){
			params.put("type", type);
		}
		return params;
    }

}