package com.tomasky.msp.client.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tomasky.msp.model.AjaxResult;
import com.tomasky.msp.model.ImsMessage;


public class ImsMessageHandler extends Handler<ImsMessage>{

	@Override
	protected ImsMessage execute(AjaxResult ar) {
		if(ar.getCode()==SUCCESS){
			return JSON.parseObject(ar.getData().toString(),new TypeReference<ImsMessage>() {} );
		}
		throw new RuntimeException(ar.getMsg());
	}
	

}
