package com.tomasky.msp.model;

public class ErrorNotifyMessage extends WeChatMessage{

	private static final long serialVersionUID = 1L;

    /**
     * 设置提示标题
     */
    public ErrorNotifyMessage setTitle(String title) {
        getData().put("first",  new Value().setValue(title).setColor("#000000"));
        return this;
    }

    /**
     * 设置系统名称
     */
	public ErrorNotifyMessage setSystemName(String systemName) {
		getData().put("keyword1",  new Value().setValue(systemName).setColor("#000000"));
		return this;
	}
	
	/**
	 *  设置错误信息
	 */
	public ErrorNotifyMessage setInfo(String info) {
		getData().put("keyword2",  new Value().setValue(info).setColor("#000000"));
		return this;
	}
	
	/**
	 *  通知时间
	 */
	public ErrorNotifyMessage setRemark(String remark) {
		getData().put("remark",  new Value().setValue(remark).setColor("#000000"));
		return  this;
	}

}
