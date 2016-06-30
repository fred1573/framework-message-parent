package com.tomasky.msp.model;

public class ActivityNotifyMessage extends WeChatMessage{

	private static final long serialVersionUID = 1L;

    /**
     * 设置提示标题
     */
    public ActivityNotifyMessage setTitle(String title) {
        getData().put("first",  new Value().setValue(title).setColor("#000000"));
        return this;
    }

    /**
     * 设置活动名
     */
	public ActivityNotifyMessage setActivityName(String activityName) {
		getData().put("keyword1",  new Value().setValue(activityName).setColor("#000000"));
		return this;
	}
	
	/**
	 *  设置审核结果
	 */
	public ActivityNotifyMessage setAuditResult(String auditResult) {
		getData().put("keyword2",  new Value().setValue(auditResult).setColor("#000000"));
		return this;
	}
	
	/**
	 *  备注
	 */
	public ActivityNotifyMessage setRemark(String remark) {
		getData().put("remark",  new Value().setValue(remark).setColor("#000000"));
		return  this;
	}

}
