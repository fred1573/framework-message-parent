package com.tomasky.msp.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 微信消息
 * Created by 番茄桑 on 2015/5/11.
 */
@Document(collection = "message_wechat")
public class WeChatMessage extends Message {

    private static final long serialVersionUID = 5601583373849690129L;

    private Map<String, Object> data = new HashMap<>();

    public Map<String, Object> getData() {
        return data;
    }

    private String touser;

    private String template_id;

    private String url;

    private String topcolor = "#00f";

    private Integer passType;

    public String getTopcolor() {
        return topcolor;
    }


    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }


    public String getTouser() {
        return touser;
    }


    public void setTouser(String touser) {
        this.touser = touser;
    }


    public String getTemplate_id() {
        return template_id;
    }


    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPassType() {
        return passType;
    }


    public void setPassType(Integer passType) {
        this.passType = passType;
    }


    public WeChatMessage setTip(String tip) {
        data.put("first", new Value().setValue(tip + "\n"));
        return this;
    }

    public WeChatMessage setTip(String tip, WechatTitleColor color) {
        data.put("first", new Value().setValue(tip + "\n").setColor(color.getColor()));
        return this;
    }

    public WeChatMessage setDescription(String remark) {
        data.put("remark", new Value().setValue(remark).setColor("#000000"));
        return this;
    }

}


class Value {
    private String value;
    private String color = WechatTitleColor.DEFAULT_COLOR.getColor();

    public Value setValue(String value) {
        this.value = value;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Value setColor(String color) {
        this.color = color;
        return this;
    }

    public String getColor() {
        return color;
    }


}
