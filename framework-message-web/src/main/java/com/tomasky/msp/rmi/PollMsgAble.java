package com.tomasky.msp.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.tomasky.msp.model.AjaxResult;
import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.Page;
import com.tomasky.msp.model.QueryFrom;

public interface PollMsgAble extends Remote	 {
	
	
	public  String ping()  throws RemoteException;
	
	public  AjaxResult  poll(Page<ImsMessage> page,QueryFrom queryFrom)  throws RemoteException;
	
	public  AjaxResult unread(Page<ImsMessage> page,QueryFrom queryFrom) throws RemoteException;

}
