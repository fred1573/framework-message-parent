package com.tomasky.msp.model;

import java.io.Serializable;


public class AjaxResult implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	public static AjaxResult ERROR(){
		return new AjaxResult(ResponseCode.PARAM_ERROR);
	}
	
	public static AjaxResult SUCCESS(Object data){
		AjaxResult ar = new AjaxResult(ResponseCode.SUCCESS);
		ar.setData(data);
		return ar;
	}
	
	public static AjaxResult SUCCESS(){
		return new AjaxResult(ResponseCode.SUCCESS);
	}
	
	
	private AjaxResult(ResponseCode rc) {
		this.code = rc.getCode();
		this.msg = rc.getMsg();
	}
	
	
	public  void  setStatus(ResponseCode rc){
		this.code = rc.getCode();
		this.msg = rc.getMsg();
	}
	
	private AjaxResult() {
		this(ResponseCode.SUCCESS);
	}
	
	private int code;
	
	private String msg;
	
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
