package com.tomasky.msp.model;

/**
 * @author frd
 *         2016/6/21.
 */
public enum WechatTitleColor {

    DEFAULT_COLOR("#173177"),

    ORANGE("#ff7f50"),

    BLACK("#000000");

    private String color;

    WechatTitleColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
