package com.tomasky.msp.support.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Http {
	
	
	public  static String  get(String url) throws Exception {
	HttpClient client = HttpClients.createDefault();
	String content = null;
	HttpGet get = new HttpGet(url);
	HttpResponse resp = client.execute(get);
	int status = resp.getStatusLine().getStatusCode();
	System.out.println(url +" ; status:"+ status);
	if(status>=200 && status <= 299){
		HttpEntity entity =  resp.getEntity();
		content =  EntityUtils.toString(entity);
	}
	return content;
	}
	
	
	public  static String  post(String url,String json) throws Exception {
	HttpClient client = HttpClients.createDefault();
	String content = null;
	HttpPost post = new HttpPost(url);
	post.addHeader("Content-Type","application/json");
	StringEntity se = new StringEntity(json, ContentType.create("application/json","utf-8"));
	post.setEntity(se);
	HttpResponse resp = client.execute(post);
	int status = resp.getStatusLine().getStatusCode();
	System.out.println("http post param:"+json);
	System.out.println(url +" ; status:"+ status);
	if(status>=200 && status <= 299){
		HttpEntity entity =  resp.getEntity();
		content =  EntityUtils.toString(entity);
	}
	return content;
	}
	
	

}
