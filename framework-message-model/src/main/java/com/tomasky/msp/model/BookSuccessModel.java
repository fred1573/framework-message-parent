package com.tomasky.msp.model;

/**
 * 
 *    预订成功 核心参数
 * 
 * @author Administrator
 *
 */
public class BookSuccessModel extends   WeChatMessage {

	private static final long serialVersionUID = 1L;
	
	
	public  WeChatMessage  setOrderId(String id){
		getData().put("OrderID", new Value().setValue(id).setColor("#000000"));
		return this;
	}
	
	public  WeChatMessage  setPersonName(String name){
		getData().put("PersonName", new Value().setValue(name).setColor("#000000"));
		return this;
	}
	
	public  WeChatMessage  setHotelName(String name){
		getData().put("HotelName", new Value().setValue(name).setColor("#000000"));
		return this;
	}
	
	public  WeChatMessage  setCheckInDate(String date){
		getData().put("CheckInDate", new Value().setValue(date).setColor("#000000"));
		return this;
	}
	
	public  WeChatMessage  setCheckOutDate(String date){
		getData().put("CheckOutDate", new Value().setValue(date).setColor("#000000"));
		return this;
	}

}
