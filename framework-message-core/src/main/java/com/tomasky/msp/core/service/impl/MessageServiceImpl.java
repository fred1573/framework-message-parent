package com.tomasky.msp.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.tomato.mq.client.produce.MQProducer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;

import com.google.gson.Gson;
import com.tomasky.msp.core.service.IMessageService;
import com.tomasky.msp.core.support.MessageSendResult;
import com.tomasky.msp.core.support.helper.PushMessageHelper;
import com.tomasky.msp.core.support.helper.ShortMessageHelper;
import com.tomasky.msp.core.support.helper.WeiXinMessageHelper;
import com.tomasky.msp.core.support.redis.SerializeUtil;
import com.tomasky.msp.enumeration.PushPlatform;
import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.MailMessage;
import com.tomasky.msp.model.Message;
import com.tomasky.msp.model.PushMessage;
import com.tomasky.msp.model.SmsMessage;
import com.tomasky.msp.model.WeChatMessage;
import com.tomasky.msp.support.util.Http;
import com.tomato.mq.client.support.MQClientBuilder;
import com.tomato.mq.support.core.TextMessage;

/**
 * 消息
 * Created by 番茄桑 on 2015/5/10.
 */
public class MessageServiceImpl implements IMessageService {

    public static final String MSG_PRODUCER_ID = "MSG_PRODUCER";

    private RedisTemplate<String, Serializable> redisTemplate;

	  private final static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
    private JavaMailSenderImpl mailSender;
    private ShortMessageHelper shortMessageHelper;
    private PushMessageHelper pushMessageHelper;
    private WeiXinMessageHelper weiXinMessageHelper;

    public void sendMessage2Queue(Object message) {
        MQClientBuilder.build().send(new TextMessage(message.toString(), com.tomato.mq.support.message.MessageType.BIZ_MESSAGE, "message-center"));
    }

    private String resolveKey(Integer key) {
        return key.toString() + "*";
    }

    public List<Message> achieveMessageByKey(final Integer key) {
        List<Message> result = redisTemplate.execute(new RedisCallback<List<Message>>() {
            public List<Message> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                List<Message> mList = new ArrayList();
                Set<byte[]> keys = redisConnection.keys(resolveKey(key).getBytes());
                if (CollectionUtils.isEmpty(keys)) {
                    return mList;
                }
                for (byte[] bytes : keys) {
                    byte[] bytesObj = redisConnection.get(bytes);
                    Object obj = SerializeUtil.unSerialize(bytesObj);
                    if (obj instanceof Message) {
                        mList.add((Message) obj);
                    }
                }
                return mList;
            }
        });
        return result;
    }

    @Override
    public MessageSendResult sendSmsMessage(SmsMessage smsMessage) {
        MessageSendResult messageSendResult = null;
        try {
            messageSendResult = shortMessageHelper.sendShortMessage(smsMessage.getReceiver(),smsMessage.getContent(),smsMessage.getChannel());
        } catch (Exception e) {
        	logger.error("发送短信失败:"+e.getMessage());
        }
        return messageSendResult;
    }

    @Override
    public MessageSendResult sendPushMessage(PushMessage pushMessage) {
        PushPlatform pushPlatform = pushMessage.getPushPlatform();
        if (PushPlatform.ANDROID.equals(pushPlatform)) {
        	try {
        		logger.debug("准备推送ANDROID：消息ID["+pushMessage.getId()+"]");
        		pushMessageHelper.pushAndroid(pushMessage);
        		return new MessageSendResult(true);
        	} catch (Exception e) {
        		logger.error("android推送失败，消息ID:["+pushMessage.getId()+"]:------>"+ e.getMessage());
        	}
        } else if (PushPlatform.IOS.equals(pushPlatform)) {
        	try {
        		logger.debug("准备推送IOS：消息ID["+pushMessage.getId()+"]");
        		pushMessageHelper.pushIos(pushMessage);
        		return new MessageSendResult(true);
        	} catch (Exception e) {
        		logger.error("ios推送失败，消息ID:["+pushMessage.getId()+"]:------>"+ e.getMessage());
        	}
        }
        return null;
    }

    @Override
    public MessageSendResult sendImsMessage(final ImsMessage imsMessage) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(imsMessage.getId().getBytes(), SerializeUtil.objTOSerialize(imsMessage));
                return null;
            }
        });
        return null;
    }

    @Override
    public MessageSendResult sendMailMessage(MailMessage mailMessage) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setFrom(mailSender.getUsername());
            List<String> receiver = mailMessage.getReceiver();
            helper.setTo(receiver.toArray(new String[receiver.size()]));
            helper.setSubject(mailMessage.getSubject());
            helper.setText(mailMessage.getContent());
            List<String> attas = mailMessage.getAttachments();
            if (CollectionUtils.isNotEmpty(attas)) {
                for (String atta : attas) {
                    helper.addAttachment(atta.substring(atta.lastIndexOf("\\")), new ClassPathResource(atta));
                }
            }
            mailSender.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return new MessageSendResult(true);
    }

    @Override
    public MessageSendResult sendWeChatMessage(WeChatMessage weChatMessage) {
    	
    	List<String> receivers = weChatMessage.getReceiver();
    	MessageSendResult msr = new MessageSendResult();
    	for (String touser : receivers) {
    		weChatMessage.setTouser(touser);
    		String  postJson = new Gson().toJson(weChatMessage);
    		String ak = weiXinMessageHelper.getAccessToken();
    		Assert.isTrue(StringUtils.isNotEmpty(ak),"ACCESS_TOKEN is empty ");
    		try {
    			String result = Http.post(String.format(weiXinMessageHelper.getModelTipUrl(),ak), postJson);
    			msr.setCode("200");
    			msr.setInfo(result);
    			logger.info("result for sendWechatMsg:"+result);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
    	
        return msr;
    }

    @Override
    public void removeFromCache(final String id) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.del(id.getBytes());
                return null;
            }
        });
    }


    public void setShortMessageHelper(ShortMessageHelper shortMessageHelper) {
        this.shortMessageHelper = shortMessageHelper;
    }

    public void setPushMessageHelper(PushMessageHelper pushMessageHelper) {
        this.pushMessageHelper = pushMessageHelper;
    }

    public void setRedisTemplate(RedisTemplate<String, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void setWeiXinMessageHelper(WeiXinMessageHelper weiXinMessageHelper) {
        this.weiXinMessageHelper = weiXinMessageHelper;
    }
}
