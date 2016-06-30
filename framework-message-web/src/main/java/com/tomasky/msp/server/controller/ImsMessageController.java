package com.tomasky.msp.server.controller;

import com.alibaba.fastjson.JSON;
import com.tomasky.msp.core.service.ImsService;
import com.tomasky.msp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.tomasky.msp.model.AjaxResult.ERROR;
import static com.tomasky.msp.model.AjaxResult.SUCCESS;


@Controller
@RequestMapping("/message")
public class ImsMessageController {

	@Autowired
	private ImsService imsService;

	/**
	 *   更新消息，并根据markRead参数决定是否将消息标为已读
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public   AjaxResult updateRead(String  messageJson, boolean markRead){
		ImsMessage im = JSON.parseObject(messageJson, ImsMessage.class);
		if(markRead) {
			im.setHasRead(true);
		}
		imsService.saveOrUpdate(im);
		return SUCCESS(ResponseCode.SUCCESS.getCode());
	}

	/**
	 *   根据ID获取消息详情
	 * @param id 消息ID
	 * @return
	 */
	@RequestMapping(value="/getMessageById",method=RequestMethod.GET)
	@ResponseBody
	public   AjaxResult getMessageById(String  id){
		ImsMessage im = imsService.findOne(id);
		if(null == im){
			return ERROR();
		}
		return SUCCESS(im);
	}

	/**
	 *  分页获取所有的消息
	 */
	@RequestMapping(value="/all/list",method=RequestMethod.GET)
	@ResponseBody
	public   AjaxResult page(Page<ImsMessage> page,QueryFrom queryFrom){
		if(null==queryFrom.getInnId() || null==queryFrom.getChannelId()){
			return ERROR();
		}
		return SUCCESS(imsService.pageAll(page,queryFrom));
	}




	/**
	 *   分页获取所有的消息(获取成功之后  会标记当前消息为已读状态)
	 */
	@RequestMapping(value="/unread/poll",method=RequestMethod.GET)
	@ResponseBody
	public   AjaxResult poll(Page<ImsMessage> page,QueryFrom queryFrom){
		if(null==queryFrom.getInnId() || null==queryFrom.getChannelId()){
			return ERROR();
		}
		return SUCCESS(imsService.pageAndMarkMessageRead(page, queryFrom));
	}


	/**
	 *  未读消息汇总
	 * @return 总条数
	 */
	@RequestMapping(value="/count",method=RequestMethod.GET)
	@ResponseBody
	public   AjaxResult count(QueryFrom queryFrom){
		if(null== queryFrom.getInnId() || null==queryFrom.getChannelId()){
			return ERROR();
		}
		return SUCCESS(imsService.countUnreadMessage(queryFrom));
	}

	/**
	 *  获取未读消息
	 */
	@RequestMapping(value="/unread/list",method=RequestMethod.GET)
	@ResponseBody
	public  AjaxResult unRead(Page<ImsMessage> page,QueryFrom queryFrom){
		if(null==queryFrom.getInnId() || null==queryFrom.getChannelId()){
			return ERROR();
		}
		return SUCCESS(imsService.pageUnRead(page, queryFrom));
	}

	@RequestMapping("/v-for-vendetta")
	@ResponseBody
	public AjaxResult jsonFormatRepair() {
		return SUCCESS(imsService.jsonFormatRepair());
	}

}
