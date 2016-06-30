package com.tomasky.msp.model;

/**
 * 申请反馈通知
 */
public class ApplyRespNotifyMessage extends WeChatMessage {

    private static final long serialVersionUID = 1L;

    /**
     * 设置提示标题
     */
    public ApplyRespNotifyMessage setTitle(String title) {
        getData().put("first", new Value().setValue(title + "\n"));
        return this;
    }

    /**
     * 设置申请人
     */
    public ApplyRespNotifyMessage setApplier(String applier) {
        getData().put("keyword1", new Value().setValue(applier).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 设置申请时间
     */
    public ApplyRespNotifyMessage setApplyTime(String applyTime) {
        getData().put("keyword2", new Value().setValue(applyTime).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 备注
     */
    public ApplyRespNotifyMessage setRemark(String remark) {
        getData().put("remark", new Value().setValue(remark).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

}
