package com.tomasky.msp.support.constants;

/**
 * Created by 番茄桑 on 2015/5/12.
 */
public class Constants {
    // 发送消息的URL
    public static final String SEND_MESSAGE_URL = "http://localhost:8080/msp/sendSms";
    // 获得消息的URL
    public static final String GET_MESSAGE_URL = "http://localhost:8080/msp/get";
    // http请求超时时间
    public static final int HTTP_SOCKET_TIMEOUT = 500000;
    // http连接超时时间
    public static final int HTTP_CONNECT_TIMEOUT = 500000;
    // http连接请求超时时间
    public static final int HTTP_CONNECTION_REQUEST_TIMEOUT = 500000;

    // 短信
    public static String SYS_RESOURCE_MSG_URL = "MSG.URL";
    public static String SYS_RESOURCE_MSG_ACCOUNT = "MSG.ACCOUNT";
    public static String SYS_RESOURCE_MSG_PASSWORD = "MSG.PASSWORD";

    // http获取响应类型(all:所有，responseStr:网页字符串，cookies：网页cookies)
    public final static String HTTP_GET_TYPE_ALL = "all";
    public final static String HTTP_GET_TYPE_STRING = "responseStr";
    public final static String HTTP_GET_TYPE_COOKIES = "cookies";

    public final static String HTTP_REQUEST_TYPE_GET = "GET";
    public final static String HTTP_REQUEST_TYPE_POST = "POST";
    public static final String SYS_DIC_PROXY = "proxy";
    
	public  static final String PARAMS_ERROR = "参数错误"; 
	
	public static final String  SUCCESS = "ok";

}
