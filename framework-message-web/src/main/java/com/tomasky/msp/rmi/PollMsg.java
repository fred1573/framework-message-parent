package com.tomasky.msp.rmi;

import static com.tomasky.msp.model.AjaxResult.ERROR;
import static com.tomasky.msp.model.AjaxResult.SUCCESS;

import java.io.Serializable;
import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tomasky.msp.core.service.ImsService;
import com.tomasky.msp.model.AjaxResult;
import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.Page;
import com.tomasky.msp.model.QueryFrom;


public class PollMsg  /*extends UnicastRemoteObject*/ implements PollMsgAble, Serializable {

    private final static Logger LOGGER = LoggerFactory.getLogger(PollMsg.class);

    protected PollMsg() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    @Autowired
    private ImsService imsService;

    public void setImsService(ImsService imsService) {
        this.imsService = imsService;
    }

    @Override
    public AjaxResult poll(Page<ImsMessage> page, QueryFrom queryFrom) throws RemoteException {
        if (null == queryFrom.getInnId() || null == queryFrom.getChannelId()) {
            return ERROR();
        }
        AjaxResult success = SUCCESS(imsService.pageAndMarkMessageRead(page, queryFrom));
        return success;
    }

    @Override
    public AjaxResult unread(Page<ImsMessage> page, QueryFrom queryFrom)
            throws RemoteException {
        if (null == queryFrom.getInnId() || null == queryFrom.getChannelId()) {
            return ERROR();
        }
        AjaxResult success = SUCCESS(imsService.pageUnRead(page, queryFrom));
        return success;
    }

    @Override
    public String ping() throws RemoteException {
        LOGGER.info("---------收到RMI客户端心跳请求-------------");
        return "pong";
    }

}
