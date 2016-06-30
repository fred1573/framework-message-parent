package com.tomasky.msp.model;

	public enum ResponseCode{
		
		SUCCESS(200,"ok"),PARAM_ERROR(400,"参数错误");
		private int code;
		private String msg;
		
		public String getMsg() {
			return msg;
		}
		
		public int getCode() {
			return code;
		}
		
		private ResponseCode(Integer code,String msg){
			this.code  = code;
			this.msg = msg;
		}
		
	}