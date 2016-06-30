package com.tomasky.msp.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tomasky.msp.core.service.IMessageService;
import com.tomasky.msp.core.support.helper.ShortMessageHelper;
import com.tomasky.msp.enumeration.MessageType;
import com.tomasky.msp.model.Message;

/**
 * Created by 番茄桑 on 2015/5/5.
 */
@Controller
@RequestMapping("/")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private IMessageService messageService;
    @Autowired
    private ShortMessageHelper shortMessageHelper;
    
    
    /**
     * 将消息放入队列
     *
     * @param messageJson 消息对象的json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public void sendMessage(String messageJson, MessageType messageType){
//        if (!MessageType.SMS.equals(messageType)) {
//            return;
//        }
        // TODO 测试语句，正式版本删除
        LOGGER.info("------------------------------new message:" + messageJson);
        // 将消息对象放入队列
           messageService.sendMessage2Queue(messageJson);
    }



	
    /**
     * 根据客栈ID，返回redis中保存的该客栈的全部未读站内消息
     *
     * @param innId 客栈ID
     * @return
     */
    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Message> getMessage(@RequestParam("innId") Integer innId) {
        return messageService.achieveMessageByKey(innId);
    }

    /**
     * 根据客栈ID，返回redis中保存的该客栈的全部未读站内消息
     * 获取后删除redis中的数据存档
     *
     * @param innId 客栈ID
     * @return
     */
    @RequestMapping(value = "/pollMessage", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Message> pollMessage(@RequestParam("innId") Integer innId) {
//        return messageService.achieveMessageByKey(innId);
        return null;
    }



    /**
     * 根据消息ID（mogodb生成的ID），删除redis中的消息对象
     * @param mgId
     */
    @ResponseBody
    @RequestMapping(value = "/removeMessage", method = RequestMethod.POST)
    public void removeMessage(@RequestParam("mgId") String mgId) {
        messageService.removeFromCache(mgId);
    }
    
    
}
