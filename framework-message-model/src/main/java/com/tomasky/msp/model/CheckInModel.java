package com.tomasky.msp.model;

/**
 * 
 *  入住通知核心参数 
 * 
 * @author Administrator
 *
 */
public class CheckInModel extends WeChatMessage {

	
	private static final long serialVersionUID = 1L;
	
	
	public CheckInModel buildCheckInMessageHead(String roomNum,String checkInData,String checkOutDate){
		getData().put("keyword1", new Value().setValue(roomNum).setColor("#000000"));
		getData().put("keyword2", new Value().setValue(checkInData).setColor("#000000"));
		getData().put("keyword3", new Value().setValue(checkOutDate).setColor("#000000"));
		return  this;
	}
	

}
