package com.tomasky.msp.model;

/**
 * 
 *  房间订单取消通知  核心参数
 * 
 * @author Administrator
 *
 */
public class CannelOrderModel extends WeChatMessage {
	
	
	private static final long serialVersionUID = 1L;

	public  WeChatMessage  setCannelOrderId(String id){
		getData().put("keyword1", new Value().setValue(id).setColor("#000000"));
		return this;
	}
	
	public  WeChatMessage  setCannelTime(String time){
		getData().put("keyword2", new Value().setValue(time).setColor("#000000"));
		return this;
	}
	
	public  WeChatMessage  setCannelRoomTypeName(String name){
		getData().put("keyword3", new Value().setValue(name).setColor("#000000"));
		return this;
	}
	
	public WeChatMessage setCannelOrderTotalMoney(String money){
		getData().put("keyword4", new Value().setValue(money).setColor("#000000"));
		return this;
	}
	

}
