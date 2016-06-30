package com.tomasky.msp.client.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tomasky.msp.model.AjaxResult;
import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.Page;
import com.tomasky.msp.model.QueryFrom;
import com.tomasky.msp.rmi.PollMsgAble;
import org.springframework.remoting.RemoteLookupFailureException;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PollHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PollHelper.class);

    private static final PollMsgAble poll;

    private static boolean hasTurnOff = false;

    private static final ExecutorService service = Executors.newSingleThreadExecutor();

    static {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "rmi.xml");
        poll = (PollMsgAble) ctx.getBean("pollService");
        service.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (hasTurnOff) {
                            LOGGER.error("服务器已经断开连接-->");
                        }
                        poll.ping();
                        hasTurnOff = false;
                        LOGGER.debug("心跳正常");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        LOGGER.error("心跳线程被意外终止", e);
                    } catch (RemoteException | RemoteLookupFailureException e) {
                        LOGGER.error("心跳返回失败，准备拒绝后续请求", e);
                        hasTurnOff = true;
                    }
                }

            }
        });
    }


    public static AjaxResult poll(Page<ImsMessage> page, QueryFrom queryFrom) {
        if (hasTurnOff) {
            throw new RuntimeException("服务器已断开服务");
        }
        try {
            LOGGER.debug("poll {}", poll);
            return poll.poll(page, queryFrom);
        } catch (Exception e) {
            hasTurnOff = true;
            LOGGER.error("poll异常", e);
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public static AjaxResult unread(Page<ImsMessage> page, QueryFrom queryFrom) {
        if (hasTurnOff) {
            throw new RuntimeException("服务器已断开服务");
        }
        try {
            LOGGER.debug("poll {}", poll);
            return poll.unread(page, queryFrom);
        } catch (Exception e) {
            hasTurnOff = true;
            LOGGER.error("unread异常", e);
            throw new RuntimeException(e.getMessage(),e);
        }
    }

}
