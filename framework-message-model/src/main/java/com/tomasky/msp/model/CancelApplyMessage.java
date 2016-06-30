package com.tomasky.msp.model;

public class CancelApplyMessage extends WeChatMessage {

    private static final long serialVersionUID = 1L;

    /**
     * 设置提示标题
     */
    public CancelApplyMessage setTitle(String title) {
        getData().put("first", new Value().setValue(title).setColor("#000000"));
        return this;
    }

    /**
     * 设置服务项目
     */
    public CancelApplyMessage setProject(String project) {
        getData().put("keyword1", new Value().setValue(project).setColor("#000000"));
        return this;
    }

    /**
     * 设置申请时间
     */
    public CancelApplyMessage setApplyTime(String applyTime) {
        getData().put("keyword2", new Value().setValue(applyTime).setColor("#000000"));
        return this;
    }

    /**
     * 设置订单号
     */
    public CancelApplyMessage setOrderNo(String orderNo) {
        getData().put("keyword3", new Value().setValue(orderNo).setColor("#000000"));
        return this;
    }

    /**
     * 设置订单人
     */
    public CancelApplyMessage setApplier(String applier) {
        getData().put("keyword4", new Value().setValue(applier).setColor("#000000"));
        return this;
    }

    /**
     * 备注
     */
    public CancelApplyMessage setRemark(String remark) {
        getData().put("remark", new Value().setValue(remark).setColor("#000000"));
        return this;
    }

}
