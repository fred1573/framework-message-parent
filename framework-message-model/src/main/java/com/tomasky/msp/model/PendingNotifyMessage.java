package com.tomasky.msp.model;

public class PendingNotifyMessage extends WeChatMessage{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 *  待处理任务
	 */
	public PendingNotifyMessage setPendingTask(String pendingTask) {
		getData().put("keyword1",  new Value().setValue(pendingTask).setColor("#000000"));
		return this;
	}
	
	/**
	 *  通知类型
	 */
	public PendingNotifyMessage setNotifyType(String notifyType) {
		getData().put("keyword2",  new Value().setValue(notifyType).setColor("#000000"));
		return this;
	}
	
	/**
	 *  通知时间
	 */
	public PendingNotifyMessage setNotifyTime(String notifyTime) {
		getData().put("keyword3",  new Value().setValue(notifyTime).setColor("#000000"));
		return  this;
	}
	
	

}
