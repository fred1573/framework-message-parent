package com.tomasky.msp.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class BuildImsMessageQuery {
  
	private Integer innId;
	
	private Integer channelId;
	
	private String type;
	
	private Boolean hasRead;

	private boolean valid = false;

    public static class Builder {
      
    	private Integer innId;
    	
    	private Integer channelId;
    	
    	private String type;
    	
    	private Boolean hasRead;

		private boolean valid;

    	public Builder() {
		}
    	
    	public Builder(QueryFrom queryFrom) {
    		this.innId = queryFrom.getInnId();
    		this.channelId = queryFrom.getChannelId();
    		this.type = queryFrom.getType();
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
    	
    	public Builder hasRead(Boolean hasRead){
    		this.hasRead = hasRead;
    		return this;
    	}

		public Builder valid(boolean valid) {
			this.valid = valid;
			return this;
		}

        public BuildImsMessageQuery build() {
            return new BuildImsMessageQuery(this);
        }
    }
 
    private BuildImsMessageQuery(Builder b) {
        innId = b.innId;
        channelId = b.channelId;
        type = b.type;
        hasRead = b.hasRead;
		valid = b.valid;
    }
    
    public Query buildQuery() { // 构建，返回一个新对象
        Query query = new Query();
		query.addCriteria(Criteria.where("innId").is(innId));
		query.addCriteria(Criteria.where("channelId").is(channelId));
		if(valid) {
			query.addCriteria(Criteria.where("valid").is(valid));
		}
		if(StringUtils.isNotEmpty(type)){
			query.addCriteria(Criteria.where("imsType").in(type.split(",")));
		}
		if(null!=hasRead){
			query.addCriteria(Criteria.where("hasRead").is(hasRead));
		}
        return  query;
    }

}