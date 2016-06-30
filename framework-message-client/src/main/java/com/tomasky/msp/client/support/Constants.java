package com.tomasky.msp.client.support;

public class Constants {
	
    // 发送消息的服务名称    --  save
    public final static String SEND_MESSAGE_URL = "sendMessage";
    // 获取未读消息的服务名称   --  delete
    public final static String GET_MESSAGE_URL = "getMessage";
    // 获取未读消息，并删除记录的，服务名称  -- delete
    public final static String POLL_MESSAGE_URL = "pollMessage";
    // 删除消息的服务名称   -- delete
    public final static String REMOVE_MESSAGE_URL = "removeMessage";
    
    // 获取所有的消息列表  -- save
    public final static String GET_ALL_MESSAGE = "message/all/list" ;
    
    // 标记消息已读   -- save
    public final static String UPDATE_MESSAGE_READ = "message/update";
    
    // 获取未读消息列表  -- delete
    public final static String GET_ALL_UNREAD_MESSAGE = "message/unread/list";
    
    // 获取未读消息总数  -- delete
    public final static String GET_UNREAD_MESSAGE_UNREAD = "message/count";
    
    //  获取消息之后标记消息已读   -- delete
    public final static String GET_UNREAD_MESSAGE_POLL = "message/unread/poll";

    //  -- save
    public final static String GET_MESSAGE_BY_ID = "message/getMessageById";

}
