package com.tomasky.msp.model;

/**
 * 新订单通知
 */
public class NewOrderNotifyMessage extends WeChatMessage {

    private static final long serialVersionUID = 1L;

    /**
     * 设置提示标题
     */
    public NewOrderNotifyMessage setTitle(String title) {
        getData().put("first", new Value().setValue(title + "\n").setColor(WechatTitleColor.ORANGE.getColor()));
        return this;
    }

    /**
     * 设置旅店名称
     */
    public NewOrderNotifyMessage setInnName(String innName) {
        getData().put("keyword1", new Value().setValue(innName).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 设置客户名称
     */
    public NewOrderNotifyMessage setRoomName(String roomName) {
        getData().put("keyword2", new Value().setValue(roomName).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 设置入离日期
     */
    public NewOrderNotifyMessage setInOutTime(String inOutTime) {
        getData().put("keyword3", new Value().setValue(inOutTime).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 设置预订间数
     */
    public NewOrderNotifyMessage setBookNum(String bookNum) {
        getData().put("keyword4", new Value().setValue(bookNum).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 设置总金额
     */
    public NewOrderNotifyMessage setAmount(String amount) {
        getData().put("keyword5", new Value().setValue(amount).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 备注
     */
    public NewOrderNotifyMessage setRemark(String remark) {
        getData().put("remark", new Value().setValue(remark).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

}
