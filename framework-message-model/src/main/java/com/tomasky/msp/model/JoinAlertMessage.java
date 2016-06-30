package com.tomasky.msp.model;

/**
 * 参与活动即将开始提醒
 */
public class JoinAlertMessage extends WeChatMessage {

    private static final long serialVersionUID = 1L;

    /**
     * 设置提示标题
     */
    public JoinAlertMessage setTitle(String title) {
        getData().put("first", new Value().setValue(title).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 设置服务项目
     */
    public JoinAlertMessage setUserName(String userName) {
        getData().put("keyword1", new Value().setValue(userName).setColor(WechatTitleColor.ORANGE.getColor()));
        return this;
    }

    /**
     * 设置申请时间
     */
    public JoinAlertMessage setTheme(String theme) {
        getData().put("keyword2", new Value().setValue(theme).setColor(WechatTitleColor.ORANGE.getColor()));
        return this;
    }

    /**
     * 设置订单号
     */
    public JoinAlertMessage setTime(String time) {
        getData().put("keyword3", new Value().setValue(time).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 设置订单人
     */
    public JoinAlertMessage setLocation(String location) {
        getData().put("keyword4", new Value().setValue(location).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

    /**
     * 备注
     */
    public JoinAlertMessage setRemark(String remark) {
        getData().put("remark", new Value().setValue(remark).setColor(WechatTitleColor.BLACK.getColor()));
        return this;
    }

}
