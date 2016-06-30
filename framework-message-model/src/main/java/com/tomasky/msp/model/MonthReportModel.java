package com.tomasky.msp.model;
/**
 * 
 *  月度报表核心参数
 * 
 * @author Administrator
 *
 */
public class MonthReportModel extends WeChatMessage {

	private static final long serialVersionUID = 1L;
	
	public  MonthReportModel  setBillDate(String billDate){
		getData().put("keyword1",  new Value().setValue(billDate).setColor("#000000"));
		return this;
	}
	
	public  MonthReportModel  setMoney(String money){
		getData().put("keyword2",  new Value().setValue(money).setColor("#000000"));
		return this;
	}

	public MonthReportModel  setRemark(String remark){
		getData().put("keyword3", new Value().setValue(remark).setColor("#000000"));
		return this;
	}
	
	
}
