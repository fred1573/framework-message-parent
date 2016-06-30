package com.tomasky.msp.client.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tomasky.msp.model.AjaxResult;
import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.Page;


public class PageImsMessageHandler extends Handler<Page<ImsMessage>> {

    @Override
    protected Page<ImsMessage> execute(AjaxResult ar) {
        if (ar.getCode() == SUCCESS) {
            Page<ImsMessage> page = JSON.parseObject(ar.getData().toString(), new TypeReference<Page<ImsMessage>>() {});
            page.calcTotalPage();
            return page;
        }
        throw new RuntimeException(ar.getMsg());
    }


}
