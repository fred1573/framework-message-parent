package com.tomasky.msp.client.support;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件加载
 * Created by 番茄桑 on 2015/5/12.
 */


public class PropertiesReader {
    // 消息服务访问地址
    private  static String serviceUrl = null;
    
    private static String addTemplet;
    
    private static String cannelTemplet;
    
    private static String checkInTemplet;
    
    private static String monthReportTemplet ;
    
    private static String balanceTemplet ;

    private static  String errorNotifyTemplate;

    private static  String activityNotifyTemplate;

    private static  String cancelApplyTemplate;

    private static  String newOrderNotifyTemplate;

    private static String applyRespNotifyTemplate;

    private static String joinAlertTemplate;

    /**
     *  待处理通知微信模板
     */
    private static String pendingNotify;
    

    static {
        InputStream in = null;
        try {
            String path = System.getProperty("spring.profiles.active");
            if (StringUtils.isEmpty(path)) {
                path = "production";
            }
            if (path.equals("pro")) {
                path = "production";
            }
            in = PropertiesReader.class.getResourceAsStream("/" + path + "/config.properties");
            Properties properties = new Properties();
            properties.load(in);
            serviceUrl = properties.getProperty("service.url");
            addTemplet = properties.getProperty("add.templet");
            cannelTemplet = properties.getProperty("cannel.templet");
            checkInTemplet = properties.getProperty("check.in.templet");
            monthReportTemplet=properties.getProperty("month.report.templet");
            balanceTemplet=properties.getProperty("balance.templet");
            pendingNotify = properties.getProperty("pending.templet");
            errorNotifyTemplate = properties.getProperty("error.notify.templet");
            activityNotifyTemplate = properties.getProperty("audit.notify.templet");
            cancelApplyTemplate = properties.getProperty("cancel.apply.templet");
            newOrderNotifyTemplate = properties.getProperty("neworder.notify.templet");
            applyRespNotifyTemplate = properties.getProperty("apply.resp.notify.templet");
            joinAlertTemplate = properties.getProperty("join.alert.templet");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	if(in != null){
            		in.close();
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getServiceUrl() {
        return serviceUrl;
    }


    public static String getSendMessageUrl() {
        if (!serviceUrl.endsWith("/")) {
            serviceUrl = serviceUrl + "/";
        }
        return serviceUrl + Constants.SEND_MESSAGE_URL;
    }

    public static String getGetMessageUrl() {
        if (!serviceUrl.endsWith("/")) {
            serviceUrl = serviceUrl + "/";
        }
        return serviceUrl + Constants.GET_MESSAGE_URL;
    }

    public static String getPollMessageUrl() {
        if (!serviceUrl.endsWith("/")) {
            serviceUrl = serviceUrl + "/";
        }
        return serviceUrl + Constants.POLL_MESSAGE_URL;
    }

    public static String getRemoveMessageUrl() {
        return serviceUrl + Constants.REMOVE_MESSAGE_URL;
    }


	public static String getAllMessaage() {
		return  serviceUrl + Constants.GET_ALL_MESSAGE;
	}


	public static String getUpdateMessageRead() {
		return  serviceUrl + Constants.UPDATE_MESSAGE_READ;
	}


	public static String getUnreadMessage() {
		return  serviceUrl + Constants.GET_ALL_UNREAD_MESSAGE;
	}
	
	
	public static String getMessageById() {
		return serviceUrl + Constants.GET_MESSAGE_BY_ID;
	}


	public static String getUnreadMessageCount() {
		return serviceUrl + Constants.GET_UNREAD_MESSAGE_UNREAD;
	}

	public static String getUnreadMessagePoll() {
		return serviceUrl + Constants.GET_UNREAD_MESSAGE_POLL;
	}
	
	public static String getAddTemplet() {
		return addTemplet;
	}
	
	public static String getCannelTemplet() {
		return cannelTemplet;
	}
	
	public static String getCheckInTemplet() {
		return checkInTemplet;
	}
	
	public static String getBalanceTemplet() {
		return balanceTemplet;
	}
	
	public static String getMonthReportTemplet() {
		return monthReportTemplet;
	}
	
	public static String getPendingNotify() {
		return pendingNotify;
	}

    public static String getErrorNotifyTemplate() {
        return errorNotifyTemplate;
    }

    public static String getActivityNotifyTemplate() {
        return activityNotifyTemplate;
    }

    public static String getCancelApplyTemplate() {
        return cancelApplyTemplate;
    }

    public static String getNewOrderNotifyTemplate() {
        return newOrderNotifyTemplate;
    }

    public static String getApplyRespNotifyTemplate() {
        return applyRespNotifyTemplate;
    }

    public static String getJoinAlertTemplate() {
        return joinAlertTemplate;
    }
}
