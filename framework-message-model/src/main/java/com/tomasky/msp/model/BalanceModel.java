package com.tomasky.msp.model;

public class BalanceModel extends WeChatMessage {

	private static final long serialVersionUID = 1L;
	
	
	public  BalanceModel  setTimeSlot(String timeSlot){
		getData().put("keyword1",  new Value().setValue(timeSlot).setColor("#000000"));
		return this;
	}
	
	public  BalanceModel  setTotalMoney(String totalMoney){
		getData().put("keyword2",  new Value().setValue(totalMoney).setColor("#000000"));
		return this;
	}

	

}
