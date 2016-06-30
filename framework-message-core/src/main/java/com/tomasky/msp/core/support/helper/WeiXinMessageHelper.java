package com.tomasky.msp.core.support.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tomasky.msp.core.support.Constants;
import com.tomasky.msp.support.util.Http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WeiXinMessageHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinMessageHelper.class);

    private String modelTipUrl; //  发送消息地址
    private String wxHost;  // 微信项目
    private String wxTokenUrl;  // 获取微信token url
    private String weixinId;  // 获取token 的请求参数

    public String getModelTipUrl() {
        return modelTipUrl;
    }

    public void setModelTipUrl(String modelTipUrl) {
        this.modelTipUrl = modelTipUrl;
    }

    public void setWxHost(String wxHost) {
        this.wxHost = wxHost;
    }

    public void setWxTokenUrl(String wxTokenUrl) {
        this.wxTokenUrl = wxTokenUrl;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getAccessToken() {
        String url = null;
        try {
            url = wxHost + wxTokenUrl + "?weixinId=" + weixinId;
            String result = Http.get(url);
            LOGGER.info("request token : " + result);
            JSONObject jsonObject = JSON.parseObject(result);
            if(Constants.STATUS_SUCESS.equals(jsonObject.getInteger("status"))) {
                return jsonObject.getString("token");
            }
        } catch (Exception e) {
            LOGGER.error("获取accessToken异常， url=" + url);
        }
        return null;
    }
}
