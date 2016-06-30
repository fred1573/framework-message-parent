package com.tomasky.msp.client.handler;

import com.alibaba.fastjson.JSON;
import com.tomasky.msp.model.AjaxResult;



public abstract class Handler<T> {
	
	protected final static int SUCCESS = 200;
	
	private  AjaxResult json2Object(String json){
		AjaxResult ar = JSON.parseObject(json,   AjaxResult.class);
		
		if(null==ar) 
			throw new NullPointerException("json字符串为NULL");
		
		return ar;
	}
	
	protected abstract   T execute(AjaxResult ar);
	
	public  T handler(String json){
		AjaxResult ar = json2Object(json);
		return execute(ar);
	}
	
	
}
