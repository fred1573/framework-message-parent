package com.tomasky.msp.support;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tomasky.msp.support.constants.Constants;

/**
 * Created by 番茄桑 on 2015/5/11.
 */
public class HttpClientUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
	

    // HttpClient
    static CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
	
    /**
     * 通过HTTP方式请求指定URL的HTTP接口
     *
     * @param url         HTTP接口的访问路径
     * @param messageJson
     * @return
     */
    public  String httpPost(String url, String messageJson) throws Exception{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("messageJson", messageJson);
        return httpPost(url, params);
    }

    /**
     * 通过HTTP方式请求指定URL的HTTP接口
     *
     * @param url    HTTP接口的访问路径
     * @param params
     * @return
     */
    public static String httpPost(String url, Map<String, Object> params) {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        // 设置参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
        }
        HttpPost httpPost;
        try {
            httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(Constants.HTTP_SOCKET_TIMEOUT).
                    setConnectTimeout(Constants.HTTP_CONNECT_TIMEOUT).
                    setConnectionRequestTimeout(Constants.HTTP_CONNECTION_REQUEST_TIMEOUT).
                    setStaleConnectionCheckEnabled(true).
                    build();
            httpPost.setConfig(requestConfig);

            HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            //获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            //判断响应实体是否为空
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            LOGGER.error("请求["+url+"]出错");
            new RuntimeException("请求["+url+"]出错");
        } catch (IOException e) {
        	LOGGER.error(e.getMessage());
            new RuntimeException(e.getMessage());
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                new RuntimeException(e.getMessage());
            }
        }
        return null;
    }
    
    
    /**
     * 通过HTTP方式请求指定URL的HTTP接口
     *
     * @param url    HTTP接口的访问路径
     * @param params
     * @return
     */
    public static String httpGet(String url, Map<String, Object> params) {
        if (params != null) {
        	boolean isFirst = true;
            for (Map.Entry<String, Object> entry : params.entrySet()) {
            	if(isFirst){
            		url += ("?" + entry.getKey()+ "=" + entry.getValue());  
            		isFirst = false;
            	}else{
            		url += ("&" + entry.getKey()+ "=" + entry.getValue());  
            	}
            }
        }
        HttpGet httpGet  = new HttpGet(url);
        try {
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(Constants.HTTP_SOCKET_TIMEOUT).
                    setConnectTimeout(Constants.HTTP_CONNECT_TIMEOUT).
                    setConnectionRequestTimeout(Constants.HTTP_CONNECTION_REQUEST_TIMEOUT).
                    setStaleConnectionCheckEnabled(true).
                    build();
            httpGet.setConfig(requestConfig);
            Long nowTime = System.currentTimeMillis();
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            System.out.println(System.currentTimeMillis()-nowTime);
            //获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            //判断响应实体是否为空
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            new RuntimeException("请求["+url+"]出错");
        } catch (IOException e) {
            e.printStackTrace();
            new RuntimeException(e.getMessage());
        }
        return null;
    }
    
    
    public static void main(String[] args) {
		
    	
    	for (int i = 0; i < 10; i++) {
    		new Thread(){public void run() {
    	 		for (int j = 0; j < 10000; j++) {
        			Long nowTime = System.currentTimeMillis();
        			httpGet("http://notice.fanqielaile.net/message/all/list?innId=141&channelId="+j, null);
        			System.out.println("t"+ (System.currentTimeMillis()-nowTime)+Thread.currentThread());
        		}
    		};}.start();
		}
	}
    

}
