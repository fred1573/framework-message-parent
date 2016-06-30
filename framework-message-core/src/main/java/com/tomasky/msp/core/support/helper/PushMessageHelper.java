package com.tomasky.msp.core.support.helper;

import java.util.ArrayList;
import java.util.List;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.tomasky.msp.core.support.PushUtils;
import com.tomasky.msp.enumeration.PushChannel;
import com.tomasky.msp.model.PushMessage;

/**
 * 推送服务
 * Created by 番茄桑 on 2015/5/13.
 */
public class PushMessageHelper {
    private final static Logger logger = LoggerFactory.getLogger(PushMessageHelper.class);

    // 极光Android推送，连接失败重试次数
    private static final int MAX_REPUSH_TIME = 3;
    private static final int BADGE = 1;
    private static final String SOUND = "default";

    private String  keystore;
    
    private String password;
    // true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
    private boolean isProduction;
    private String pmsMasterSecret;
    private String pmsAppkey;
    private String crmMasterSecret;
    private String crmAppkey;

    public void pushIos(PushMessage pushMessage) throws Exception {
        PushNotificationPayload pushNotificationPayload = new PushNotificationPayload();
        // 设置推送内容
        pushNotificationPayload.addAlert(pushMessage.getContent());
        // 设置iphone应用图标上小红圈上的数值
        pushNotificationPayload.addBadge(BADGE);
        // 设置铃音
        pushNotificationPayload.addSound(SOUND);

        PushNotificationManager pushManager = new PushNotificationManager();
        pushManager.initializeConnection(new AppleNotificationServerBasicImpl(PushUtils.getInstace().getResoucePathStream(keystore), password, isProduction));
        List<PushedNotification> notifications = new ArrayList<PushedNotification>();
        List<String> tokens = pushMessage.getIosReceivers();
        if (!CollectionUtils.isEmpty(tokens)) {
            // 发送push消息
            if (tokens.size() == 1) {
                Device device = new BasicDevice();
                device.setToken(tokens.get(0));
                PushedNotification notification = pushManager.sendNotification(device, pushNotificationPayload, true);
                notifications.add(notification);
            } else {
                List<Device> device = new ArrayList<Device>();
                for (String token : tokens) {
                    device.add(new BasicDevice(token));
                }
                notifications = pushManager.sendNotifications(pushNotificationPayload, device);
            }
        }

        List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
        List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
        int failed = failedNotifications.size();
        int successful = successfulNotifications.size();
        logger.info("ios push,Failed:" + failed + ",Success:" + successful);
    }

    /**
     * 使用极光方式发送Android推送消息
     *
     * @param pushMessage
     */
    public void pushAndroid(PushMessage pushMessage) throws Exception {
        // 根据渠道不同，构造极光推送客户端对象
        JPushClient jPushClient = buildPushClient(pushMessage.getPushChannel());
        // 构造消息对象
        PushPayload pushPayload = PushPayload.newBuilder()
                // 设置移动设备类型为Android
                .setPlatform(Platform.android())
                        // 设置接收推送消息的Tag，即用户的手机号码
                .setAudience(Audience.tag(pushMessage.getAndroidReceivers()))
                        // 设置alert消息的标题和内容
                .setNotification(Notification.android( pushMessage.getContent(),pushMessage.getTitle(), null))
                .build();
        // 发送推送消息
        jPushClient.sendPush(pushPayload);
        logger.info("Android push total:" + pushMessage.getAndroidReceivers().size());
    }

    /**
     * 根据推送消息渠道不同，构建不同的推送客户端
     *
     * @param pushChannel
     * @return
     */
    private JPushClient buildPushClient(PushChannel pushChannel) {
        String masterSecret = "";
        String appkey = "";
        if (PushChannel.PMS.equals(pushChannel)) {
            masterSecret = this.pmsMasterSecret;
            appkey = this.pmsAppkey;
        } else if (PushChannel.CRM.equals(pushChannel)) {
            masterSecret = this.crmMasterSecret;
            appkey = this.crmAppkey;
        }
        return new JPushClient(masterSecret, appkey, MAX_REPUSH_TIME);
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsProduction(boolean isProduction) {
        this.isProduction = isProduction;
    }

    public void setPmsMasterSecret(String pmsMasterSecret) {
        this.pmsMasterSecret = pmsMasterSecret;
    }

    public void setPmsAppkey(String pmsAppkey) {
        this.pmsAppkey = pmsAppkey;
    }

    public void setCrmMasterSecret(String crmMasterSecret) {
        this.crmMasterSecret = crmMasterSecret;
    }

    public void setCrmAppkey(String crmAppkey) {
        this.crmAppkey = crmAppkey;
    }

	public String getKeystore() {
		return keystore;
	}

	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}

    
}
