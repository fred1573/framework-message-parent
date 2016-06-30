import com.tomasky.msp.client.service.impl.MessageManageServiceImpl;
import com.tomasky.msp.client.support.MessageBuilder;
import com.tomasky.msp.enumeration.PushChannel;
import com.tomasky.msp.enumeration.PushPlatform;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程压力测试类
 * Created by 番茄桑 on 2015/5/20.
 */
public class MessageTestThread implements Runnable {
    @Override
    public void run() {
        List<String> receivers = new ArrayList<>();
        receivers.add("13666666666");
//        List<String> receiver = new ArrayList<>();
//        receiver.add("xudasen@fanqielaile.com");
//        List<String> receiver = new ArrayList<>();
//        receiver.add("IMS-RECEIVE-1");
//        receiver.add("IMS-RECEIVE-2");
//        receiver.add("IMS-RECEIVE-3");
        for(int i = 0; i < 10; i ++) {
                new MessageManageServiceImpl().sendMessage(MessageBuilder.buildPushMessage(PushChannel.PMS, PushPlatform.ANDROID, "隔壁老王的推送消息", receivers, "老王站内消息标题", "隔壁家有人没？"));
        }
    }

    public static void main(String[] args) {
        MessageTestThread messageTestThread = new MessageTestThread();
        int threadCount = 25 * Runtime.getRuntime().availableProcessors();
        for(int i = 0; i < threadCount; i ++) {
            new Thread(messageTestThread).start();
        }
    }
}
