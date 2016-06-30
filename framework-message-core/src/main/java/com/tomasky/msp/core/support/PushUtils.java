package com.tomasky.msp.core.support;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class PushUtils {
	
	private static final Logger   LOGGER = LoggerFactory.getLogger(PushUtils.class);
	
	private static PushUtils pushUtils = new PushUtils();
	
	
	
	private PushUtils(){
		
	}
	
	public static PushUtils getInstace() {
		return pushUtils;
	}
	
	
	public    InputStream getResoucePathStream(String  keystore){
		try {
			LOGGER.debug("使用证书："+keystore);
			return  new ClassPathResource("/"+keystore, this.getClass().getClassLoader()).getInputStream();
		} catch (IOException e) {
			LOGGER.error("获取IOS证书出错:"+e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
