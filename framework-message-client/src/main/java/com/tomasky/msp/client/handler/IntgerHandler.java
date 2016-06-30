package com.tomasky.msp.client.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tomasky.msp.model.AjaxResult;


public class IntgerHandler extends Handler<Integer>{

	@Override
	protected Integer execute(AjaxResult ar) {
		if(ar.getCode()==SUCCESS){
			return  JSON.parseObject(ar.getData().toString(),new TypeReference<Integer>() {} );
		}
		throw new RuntimeException(ar.getMsg());
	}
	

}
