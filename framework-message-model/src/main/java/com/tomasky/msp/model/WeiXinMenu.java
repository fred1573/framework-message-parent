package com.tomasky.msp.model;

import java.util.List;

public class WeiXinMenu {
	
	private  Integer id;
	
	private String url;
	
	private String type;
	
	private String name;
	
	private String  key;
	
	private Integer pid;
	
	private List<WeiXinMenu> sub_button;
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<WeiXinMenu> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<WeiXinMenu> sub_button) {
		this.sub_button = sub_button;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUrlReturnAppId(String url,String appid) {
		this.url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+url+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

}
