package com.tomasky.msp.client.support;

import com.alibaba.fastjson.JSON;
import com.tomasky.msp.client.model.BalanceModel;
import com.tomasky.msp.client.model.MonthReportModel;
import com.tomasky.msp.client.model.PendingNotify;
import com.tomasky.msp.enumeration.MessageType;
import com.tomasky.msp.enumeration.PushChannel;
import com.tomasky.msp.enumeration.PushPlatform;
import com.tomasky.msp.enumeration.SmsChannel;
import com.tomasky.msp.model.*;
import com.tomasky.msp.support.util.PKGenerator;

import java.util.Date;
import java.util.List;

/**
 * 消息构建对象
 * Created by 番茄桑 on 2015/5/18.
 */
public class MessageBuilder {
    /**
     * 构造手机短信消息对象
     *
     * @param receiver 短信接受者集合，List保存手机号码字符串对象
     * @param content  短信内容
     * @return 短信消息对象
     */
    public static SmsMessage buildSmsMessage(List<String> receiver, SmsChannel channel, String content) {
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setId(PKGenerator.generate());//生成主键
        smsMessage.setChannel(channel);
        smsMessage.setReceiver(receiver);
        smsMessage.setContent(content);
        return smsMessage;
    }


    /**
     * 构建新订单微信对象
     *
     */
    public static WeChatMessage buildWechatMessage(WeiXinModel model
    ) {
        if (model.getType().equals(WeChatMessageType.OTA_OPERATE_ORDER_TYPE_CONFIRM) ||
                model.getType().equals(WeChatMessageType.OTA_OPERATE_ORDER_TYPE_RECEIVE)) {
            return buildOrderWechatMessage(model);
        } else if (model.getType().equals(WeChatMessageType.OTA_OPERATE_ORDER_TYPE_CANCEL)) {
            return buildCannelOrderWechatMessage(model);
        } else if (model.getType().equals(WeChatMessageType.PMS_BOOK_2_CHECKIN) || model.getType().equals(WeChatMessageType.PMS_BOOK)) {
            return buildCheckInMessage(model);
        }
        return null;
    }

    /**
     * 创建微信月度账单订单模板消息
     *
     * @param model
     * @return
     */
    public static WeChatMessage buildMonthReportWechatMessage(MonthReportModel model
    ) {
        com.tomasky.msp.model.MonthReportModel mrm = new com.tomasky.msp.model.MonthReportModel();
        mrm.setBillDate(model.getBillDate());
        mrm.setDescription(model.getDescription());
        mrm.setId(PKGenerator.generate());//生成主键
        mrm.setRemark(model.getRemark());
        mrm.setMoney(model.getMoney());
        mrm.setTip(model.getTip());
        mrm.setReceiver(model.getReceivers());
        mrm.setTemplate_id(PropertiesReader.getMonthReportTemplet());
        mrm.setMessageType(MessageType.WECHAT);
        return mrm;
    }


    /**
     * 创建微信待处理任务
     *
     * @return
     */
    public static WeChatMessage buildPendingNotifyWechatMessage(PendingNotify pendingNotify
    ) {
        PendingNotifyMessage message = new PendingNotifyMessage();
        message.setPendingTask(pendingNotify.getPendingTask());
        message.setNotifyTime(pendingNotify.getNotifyTime());
        message.setNotifyType(pendingNotify.getNotifyType());
        message.setTip(pendingNotify.getTip());
        message.setReceiver(pendingNotify.getReceivers());
        message.setTemplate_id(PropertiesReader.getPendingNotify());
        message.setMessageType(MessageType.WECHAT);
        message.setDescription(pendingNotify.getDescription());
        return message;
    }


    /**
     * 构建微信 结算通知
     *
     * @param model
     * @return
     */
    public static WeChatMessage buildBalanceWechatMessage(BalanceModel model
    ) {
        com.tomasky.msp.model.BalanceModel bm = new com.tomasky.msp.model.BalanceModel();
        bm.setTemplate_id(PropertiesReader.getBalanceTemplet());
        bm.setTotalMoney(model.getTotalMoney());
        bm.setId(PKGenerator.generate());//生成主键
        bm.setTimeSlot(model.getTimeSlot());
        bm.setTip(model.getTip());
        bm.setReceiver(model.getReceivers());
        bm.setDescription(model.getDescription());
        bm.setMessageType(MessageType.WECHAT);
        return bm;
    }


    private static WeChatMessage buildCheckInMessage(WeiXinModel model) {
        CheckInModel wcMessage = new CheckInModel();
        wcMessage.buildCheckInMessageHead(model.getRoomNo(), model.getCheckInDate(), model.getCheckOutDate());
        wcMessage.setId(PKGenerator.generate());//生成主键
        wcMessage.setTemplate_id(PropertiesReader.getCheckInTemplet());
        wcMessage.setMessageType(MessageType.WECHAT);
        wcMessage.setReceiver(model.getReceivers());
        wcMessage.setTip(model.getTip());
        StringBuffer tempRemark = new StringBuffer();
        String splitLine = "\n";
        tempRemark.append("入住人：" + model.getPersonName() + splitLine);
        tempRemark.append("订单总价：" + model.getTotalMoney() + splitLine);
        tempRemark.append("已付金额：" + model.getPaymentMoney() + splitLine);
        tempRemark.append("客人电话：" + model.getMobile() + splitLine);
        tempRemark.append(model.getRemark());
        wcMessage.setDescription(tempRemark.toString());
        return wcMessage;
    }


    private static WeChatMessage buildOrderWechatMessage(WeiXinModel model) {
        BookSuccessModel wcMessage = new BookSuccessModel();
        wcMessage.setTemplate_id(PropertiesReader.getAddTemplet());
        wcMessage.setId(PKGenerator.generate());//生成主键
        wcMessage.setMessageType(MessageType.WECHAT);
        wcMessage.setReceiver(model.getReceivers());
        wcMessage.setTip(model.getTip());
        wcMessage.setOrderId(model.getOrderId());
        wcMessage.setPersonName(model.getPersonName());
        wcMessage.setCheckInDate(model.getCheckInDate());
        wcMessage.setCheckOutDate(model.getCheckOutDate());
        StringBuffer tempRemark = new StringBuffer();
        String splitLine = "\n";
        tempRemark.append("房型名称：" + model.getRoomType() + splitLine);
        tempRemark.append("房间数量：" + model.getRoomNum() + splitLine);
        tempRemark.append("订单总价：" + model.getTotalMoney() + splitLine);
        tempRemark.append("预付金额：" + model.getPaymentMoney() + splitLine);
        tempRemark.append("客人电话：" + model.getMobile() + splitLine);
        tempRemark.append(model.getRemark());
        wcMessage.setDescription(tempRemark.toString());
        return wcMessage;
    }



    private static WeChatMessage buildCannelOrderWechatMessage(WeiXinModel model) {
        CannelOrderModel wcMessage = new CannelOrderModel();
        wcMessage.setTemplate_id(PropertiesReader.getCannelTemplet());
        wcMessage.setId(PKGenerator.generate());//生成主键
        wcMessage.setMessageType(MessageType.WECHAT);
        wcMessage.setReceiver(model.getReceivers());
        wcMessage.setTip(model.getTip());
        wcMessage.setCannelOrderId(model.getOrderId());
        wcMessage.setCannelTime(model.getCannelTime());
        wcMessage.setCannelRoomTypeName(model.getRoomType());
        wcMessage.setCannelOrderTotalMoney(model.getTotalMoney());
        StringBuffer tempRemark = new StringBuffer();
        String splitLine = "\n";
        tempRemark.append("预付金额：" + model.getPaymentMoney() + splitLine);
        tempRemark.append("入住日期：" + model.getCheckInDate() + splitLine);
        tempRemark.append("离店日期：" + model.getCheckOutDate() + splitLine);
        tempRemark.append("预订间数：" + model.getRoomNum() + splitLine);
        tempRemark.append("客人名称：" + model.getPersonName() + splitLine);
        tempRemark.append("客人电话：" + model.getMobile() + splitLine);
        tempRemark.append(model.getRemark());
        wcMessage.setDescription(tempRemark.toString());
        return wcMessage;
    }

    public static WeChatMessage buildErrorNotifyWechatMessage(String title, String content, String remark, String systemName, List<String>  receivers) {
        ErrorNotifyMessage errorNotifyMessage = new ErrorNotifyMessage();
        errorNotifyMessage.setTemplate_id(PropertiesReader.getErrorNotifyTemplate());
        errorNotifyMessage.setId(PKGenerator.generate());//生成主键
        errorNotifyMessage.setMessageType(MessageType.WECHAT);
        errorNotifyMessage.setReceiver(receivers);
        errorNotifyMessage.setTitle(title);
        errorNotifyMessage.setInfo(content);
        errorNotifyMessage.setRemark(remark);
        errorNotifyMessage.setSystemName(systemName);
        return errorNotifyMessage;
    }

    public static WeChatMessage buildactivityNotifyWechatMessage(String title, String activityName, String auditResult, String remark, List<String>  receivers) {
        ActivityNotifyMessage activityNotifyMessage = new ActivityNotifyMessage();
        activityNotifyMessage.setTemplate_id(PropertiesReader.getActivityNotifyTemplate());
        activityNotifyMessage.setId(PKGenerator.generate());//生成主键
        activityNotifyMessage.setMessageType(MessageType.WECHAT);
        activityNotifyMessage.setReceiver(receivers);
        activityNotifyMessage.setTitle(title);
        activityNotifyMessage.setActivityName(activityName);
        activityNotifyMessage.setAuditResult(auditResult);
        activityNotifyMessage.setRemark(remark);
        return activityNotifyMessage;
    }

    public static WeChatMessage buildCancelApplyWechatMessage(String title, String project, String applyTime, String orderNo, String applier, String remark, List<String>  receivers) {
        CancelApplyMessage cancelApplyMessage = new CancelApplyMessage();
        cancelApplyMessage.setTemplate_id(PropertiesReader.getCancelApplyTemplate());
        cancelApplyMessage.setId(PKGenerator.generate());//生成主键
        cancelApplyMessage.setMessageType(MessageType.WECHAT);
        cancelApplyMessage.setReceiver(receivers);
        cancelApplyMessage.setTitle(title);
        cancelApplyMessage.setProject(project);
        cancelApplyMessage.setApplyTime(applyTime);
        cancelApplyMessage.setOrderNo(orderNo);
        cancelApplyMessage.setApplier(applier);
        cancelApplyMessage.setRemark(remark);
        return cancelApplyMessage;
    }

    /**
     * 构造站内消息对象
     *
     * @return 返回站内消息对象
     */
    public static ImsMessage buildImsMessage(ImsModel model) {
        ImsMessage imsMessage = new ImsMessage();
        imsMessage.setId(PKGenerator.generate(model.getInnId()));//生成主键
        imsMessage.setMessageType(MessageType.IMS);
        imsMessage.setInnId(model.getInnId());
        imsMessage.setChannelId(model.getChannelId());
        imsMessage.setSender(model.getSender());
        imsMessage.setTitle(model.getTitle());
        imsMessage.setContent(model.getContent());
        if (null != model.getData()) {
            String mmJson = JSON.toJSONString(model.getData());
            imsMessage.setData(mmJson);
        }
        imsMessage.setOrderId(model.getOrderId());
        imsMessage.setImsType(model.getType());
        if (null != model.getAlertTime()) {
            imsMessage.setAlertTime(model.getAlertTime());
            imsMessage.setValid(false);
            imsMessage.setImsMessageType(ImsMessageType.ALERT_MESSAGE);
        }
        if (null != model.getInvalidTime()) {
            imsMessage.setInvalidTime(model.getInvalidTime());
            imsMessage.setImsMessageType(ImsMessageType.EXPIRE_MESSAGE);
        }
        return imsMessage;
    }

    /**
     * 构造手机推送消息对象
     *
     * @param pushChannel  消息渠道PMS/CRM
     * @param pushPlatform 推送平台Android/IOS
     * @param sender       消息发送者
     * @param receiver     消息接收者集合
     * @param title        消息标题
     * @param content      消息内容
     * @return 手机推送消息对象
     */
    public static PushMessage buildPushMessage(PushChannel pushChannel, PushPlatform pushPlatform, String sender, List<String> receiver, String title, String content) {
        PushMessage pushMessage = new PushMessage();
        pushMessage.setId(PKGenerator.generate());//生成主键
        pushMessage.setMessageType(MessageType.PUSH);
        pushMessage.setPushChannel(pushChannel);
        if (pushPlatform.equals(PushPlatform.IOS)) {
            pushMessage.setIosReceivers(receiver);
        } else {
            pushMessage.setAndroidReceivers(receiver);
        }
        pushMessage.setPushPlatform(pushPlatform);
        pushMessage.setSender(sender);
        //  pushMessage.setReceiver(receiver);
        pushMessage.setTitle(title);
        pushMessage.setContent(content);
        return pushMessage;
    }


    /**
     * 构造手机推送消息对象
     *
     * @param pushChannel 消息渠道PMS/CRM
     * @param sender      消息发送者
     * @param title       消息标题
     * @param content     消息内容
     * @return 手机推送消息对象
     */
    public static PushMessage buildPushMessage(PushChannel pushChannel, String sender, List<String> androidReceivers, List<String> iosReceivers,
                                               String title, String content) {
        PushMessage pushMessage = new PushMessage();
        pushMessage.setId(PKGenerator.generate());//生成主键
        pushMessage.setMessageType(MessageType.PUSH);
        pushMessage.setPushChannel(pushChannel);
        pushMessage.setSender(sender);
        pushMessage.setAndroidReceivers(androidReceivers);
        pushMessage.setIosReceivers(iosReceivers);
        pushMessage.setTitle(title);
        pushMessage.setContent(content);
        return pushMessage;
    }

    /**
     * 构造邮件消息对象
     *
     * @param sender      消息发送者
     * @param receiver    消息接收者集合
     * @param subject     邮件标题
     * @param content     邮件内容
     * @param attachments 附件列表
     * @return 邮件消息对象
     */
    public static MailMessage buildMailMessage(String sender, List<String> receiver, String subject, String content, List<String> attachments) {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setId(PKGenerator.generate());//生成主键
        mailMessage.setMessageType(MessageType.MAIL);
        mailMessage.setSender(sender);
        mailMessage.setReceiver(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setSendTime(new Date());
        mailMessage.setContent(content);
        mailMessage.setAttachments(attachments);
        return mailMessage;
    }

    /**
     * 构造微信消息对象
     *
     * @param sender   消息发送者
     * @param receiver 消息接收者集合
     * @param content  邮件内容
     * @return 微信消息对象
     */
    public static WeChatMessage buildWeChatMessage(String sender, List<String> receiver, String content) {
        WeChatMessage weChatMessage = new WeChatMessage();
        weChatMessage.setId(PKGenerator.generate());//生成主键
        weChatMessage.setMessageType(MessageType.WECHAT);
        weChatMessage.setSender(sender);
        weChatMessage.setReceiver(receiver);
        weChatMessage.setContent(content);
        return weChatMessage;
    }

    public static WeChatMessage buildNewOrderNotifyWechatMessage(String title, String innName, String roomName, String inOutTime, String bookNum, String amount, String remark, List<String>  receivers) {
        NewOrderNotifyMessage newOrderNotifyMessage = new NewOrderNotifyMessage();
        newOrderNotifyMessage.setTemplate_id(PropertiesReader.getNewOrderNotifyTemplate());
        newOrderNotifyMessage.setId(PKGenerator.generate());//生成主键
        newOrderNotifyMessage.setMessageType(MessageType.WECHAT);
        newOrderNotifyMessage.setReceiver(receivers);
        newOrderNotifyMessage.setTitle(title);
        newOrderNotifyMessage.setInnName(innName);
        newOrderNotifyMessage.setRoomName(roomName);
        newOrderNotifyMessage.setInOutTime(inOutTime);
        newOrderNotifyMessage.setBookNum(bookNum);
        newOrderNotifyMessage.setAmount(amount);
        newOrderNotifyMessage.setRemark(remark);
        return newOrderNotifyMessage;
    }

    public static WeChatMessage buildApplyResponseNotifyWechatMessage(String title, String applier, String applyTime, String remark, List<String>  receivers) {
        ApplyRespNotifyMessage applyRespNotifyMessage = new ApplyRespNotifyMessage();
        applyRespNotifyMessage.setTemplate_id(PropertiesReader.getApplyRespNotifyTemplate());
        applyRespNotifyMessage.setId(PKGenerator.generate());//生成主键
        applyRespNotifyMessage.setMessageType(MessageType.WECHAT);
        applyRespNotifyMessage.setReceiver(receivers);
        applyRespNotifyMessage.setTitle(title);
        applyRespNotifyMessage.setApplier(applier);
        applyRespNotifyMessage.setApplyTime(applyTime);
        applyRespNotifyMessage.setRemark(remark);
        return applyRespNotifyMessage;
    }

    public static WeChatMessage buildJoinAlertWechatMessage(String title, String userName, String theme, String time, String location, String remark, List<String>  receivers) {
        JoinAlertMessage joinAlertMessage = new JoinAlertMessage();
        joinAlertMessage.setTemplate_id(PropertiesReader.getJoinAlertTemplate());
        joinAlertMessage.setId(PKGenerator.generate());//生成主键
        joinAlertMessage.setMessageType(MessageType.WECHAT);
        joinAlertMessage.setReceiver(receivers);
        joinAlertMessage.setTitle(title);
        joinAlertMessage.setUserName(userName);
        joinAlertMessage.setTheme(theme);
        joinAlertMessage.setTime(time);
        joinAlertMessage.setLocation(location);
        joinAlertMessage.setRemark(remark);
        return joinAlertMessage;
    }
}
