package com.tomasky.msp.model;

import java.util.List;

public class WeiXinModel {


	private List<String>  receivers ; //接收人的iopenid
	private String tip ; //第一行提示信息
	private String orderId ; //订单id
	private String personName ;//客户名称
	private String checkInDate ;//入住时间
	private String checkOutDate;// 离开时间
	private String roomType ;//房间类型名称
	private String totalMoney ;//总价
	private String paymentMoney; //预付款
	private String mobile ;//电话
	private String cannelTime ;//酒店名称
	private String roomNum; //房间数量
	private String roomNo;  // 房间号码
	private String remark  ;//备注最后一行显示信息
	private Integer type; 
	
	public List<String> getReceivers() {
		return receivers;
	}
	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getPaymentMoney() {
		return paymentMoney;
	}
	public void setPaymentMoney(String paymentMoney) {
		this.paymentMoney = paymentMoney;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCannelTime() {
		return cannelTime;
	}
	public void setCannelTime(String cannelTime) {
		this.cannelTime = cannelTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

}
