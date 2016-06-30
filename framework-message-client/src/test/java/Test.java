import com.alibaba.fastjson.JSON;
import com.tomasky.msp.client.model.PendingNotify;
import com.tomasky.msp.client.service.impl.MessageManageServiceImpl;
import com.tomasky.msp.client.support.MessageBuilder;
import com.tomasky.msp.enumeration.PushChannel;
import com.tomasky.msp.enumeration.PushPlatform;
import com.tomasky.msp.enumeration.SmsChannel;
import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.ImsModel;
import com.tomasky.msp.model.Page;
import com.tomasky.msp.model.WeiXinModel;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
//      System.out.println(System.currentTimeMillis());

//    	 new Test().sendIms();

//        new Test().sendSMS();

//        new Test().sendMail();
		new Test().sendWechat();
    /*	 for (int i = 0; i < 100; i++) {
             new Test().sendPush();
		}*/
        // new Test().sendPush();


//        new Test().sendIms();
//          new Test().getAllList();

        //   new Test().getUnreadCount();

        //   new Test().getUnreadList();

//         	new Test().setMessageRead();
//        new Test().pollMessage();
    	
/*    	try {
			new Test().sendPush();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
//		new Test().updateMsg();
//        new Test().getImsMessageById();
    }

    private class Pojo{
        private String aStr;
        private boolean bBool;
        private int cInt;

        public Pojo(String aStr, boolean bBool, int cInt) {
            this.aStr = aStr;
            this.bBool = bBool;
            this.cInt = cInt;
        }

        public String getaStr() {
            return aStr;
        }

        public void setaStr(String aStr) {
            this.aStr = aStr;
        }

        public boolean isbBool() {
            return bBool;
        }

        public void setbBool(boolean bBool) {
            this.bBool = bBool;
        }

        public int getcInt() {
            return cInt;
        }

        public void setcInt(int cInt) {
            this.cInt = cInt;
        }
    }

    private void updateMsg() {
        new MessageManageServiceImpl().updateMessageRead("49223-1463365622267-1747", new Pojo("a", false, 1), false);
    }

    private void getImsMessageById() {
        ImsMessage imsMessage = new MessageManageServiceImpl().getImsMessageById("48142-1460099973729-2203");
        System.out.println(imsMessage);
    }

    void sendWechat() {
        try {
            //  自己：z（ogyI_t2jvlO3pibrGF9e2ubBdtuY）
            List<String> receivers = new ArrayList<>();
            receivers.add("ogyI_t-do_gfbVBY4dc03Mg3u6wU");
            receivers.add("ogyI_txTcSegJbUbq0PXHmASlGtw");
            receivers.add("ogyI_t0AuldguUSVbpXNZX0fKH0A");
            receivers.add("oSHWEjqKFZ4PVt4jk4YhVEY9Jx0s");
//            receivers.add("ogyI_t4JuZspa5cK5pQNX-zQl6vk");
            //  新订单
        	 WeiXinModel model = new WeiXinModel();
        	 model.setType(1);
        	 model.setReceivers(receivers);
        	 model.setTip("您好，【某某客栈】您的【代销平台or番茄小站】订单（预订成功/等待确认）！");
        	 model.setOrderId("144752465421-5456452");
        	 model.setPersonName("张三");
        	 model.setCheckInDate("2015-01-11");
        	 model.setCheckOutDate("2015-22-22");
        	 model.setRoomType("温馨大床房");
        	 model.setRoomNum("2间");
        	 model.setTotalMoney("200元");
        	 model.setPaymentMoney("200元");
        	 model.setMobile("131111111111");
        	 model.setRemark("下午会早一点来，给我留给干净的床哈！还请及时登录番茄来了系统进行确认！");


            //  取消订单
            /*WeiXinModel model = new WeiXinModel();
        	 model.setType(3);
        	 model.setReceivers(receivers);
        	 model.setTip("您好，【某某客栈】您的【代销平台or番茄小站】订单已经取消！");
        	 model.setOrderId("144752465421-5456452");
        	 model.setPersonName("张三");
        	 model.setCheckInDate("2015-01-11");
        	 model.setCheckOutDate("2015-22-22");
        	 model.setRoomType("温馨大床房");
        	 model.setRoomNum("2间");
        	 model.setTotalMoney("200元");
        	 model.setPaymentMoney("200元");
        	 model.setMobile("131111111111");
        	 model.setRemark("一个小孩子哈。");
        	 model.setCannelTime("2015-01-12");*/

            //  入住通知
        	/* model.setType(5);
        	 model.setReceivers(receivers);
        	 model.setTip("您好，【某某客栈】某账号办理了一个[预订]/[上门]客人入住！");
        	 model.setPersonName("张三");
        	 model.setCheckInDate("2015-01-11");
        	 model.setCheckOutDate("2015-22-22");
        	 model.setRoomNo("201、标间A、海景房102");
        	 model.setTotalMoney("200元");
        	 model.setPaymentMoney("200元");
        	 model.setMobile("131111111111");
        	 model.setRemark("一个小孩子哈。");*/

            PendingNotify pendingNotify = new PendingNotify();
            pendingNotify.setDescription("description");
            pendingNotify.setNotifyTime("notifyTime");
            pendingNotify.setNotifyType("notifyType");
            pendingNotify.setPendingTask("pendingTask");
            pendingNotify.setReceivers(receivers);
            pendingNotify.setTip("tip");

//            new MessageManageServiceImpl().sendMessage(MessageBuilder.buildApplyResponseNotifyWechatMessage("油价又涨了", "丢手绢", "2046-02-28/2046-02-28", "no discussion", receivers));
//            new MessageManageServiceImpl().sendMessage(MessageBuilder.buildJoinAlertWechatMessage("油价又涨了", "用户名", "主题", "2046-02-28/2046-02-28", "地点", "no discussion", receivers));
            new MessageManageServiceImpl().sendMessage(MessageBuilder.buildPendingNotifyWechatMessage(pendingNotify));
//            new MessageManageServiceImpl().sendMessage(MessageBuilder.buildWechatMessage(model));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void sendMail() throws Exception {
        List<String> receiver = new ArrayList<>();
        receiver.add("wangxiaohong@fanqielaile.com");
        receiver.add("zhousili@fanqielaile.com");
        new MessageManageServiceImpl().sendMessage(MessageBuilder.buildMailMessage("隔壁老王发邮件", receiver,
                "邮件标题", "隔壁有人在家么？", null));
    }

    void sendPush() {
        List<String> iosReceivers = new ArrayList<>();
        iosReceivers.add("c0ed1d41b202816b7bb5479b14eed3e651a8ac25f7c6488e01772c8369000328");
        
    /*    List<String> androidReceivers = new ArrayList<>();
        androidReceivers.add("136666666666");*/
        new MessageManageServiceImpl()
                .sendMessage(MessageBuilder.buildPushMessage(PushChannel.PMS, PushPlatform.IOS, "隔壁老王", iosReceivers,
                        "title:特大喜讯", "content:全国人民喜迎油价上涨"));
    }


    void sendSMS() throws Exception {
        List<String> receivers = new ArrayList<>();
            receivers.add("13882176139");
//        receivers.add("18215658695");
        new MessageManageServiceImpl().sendMessage(MessageBuilder.buildSmsMessage(receivers, SmsChannel.SEND_TYPE_AUTO, "测试短信发送"));
    }


    void sendIms() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 300);
        Date d = new Date(c.getTimeInMillis());
        System.out.println(d.toLocaleString());
        MyModel mm = new MyModel();
        String mmJson = JSON.toJSONString(mm);
        System.out.println("自定义:" + mmJson);
        ImsModel model = new ImsModel();
        model.setChannelId(888888);
        model.setContent("水3水电费");
        model.setData(mmJson);
        model.setInnId(888888);
        model.setAlertTime(d);
        model.setSender("sender");
        model.setTitle("title");
        model.setType("remind_alert");
        MessageManageServiceImpl a = new MessageManageServiceImpl();
        a.sendMessage(MessageBuilder.buildImsMessage(model));
    }


    /**
     * 获取所有消息
     */
    public void getAllList() {

        MessageManageServiceImpl a = new MessageManageServiceImpl();
        Page<ImsMessage> aa = a.getAllImsMessages(124, 1, "remind_alert", 1, 20);
        for (ImsMessage im : aa.getDatas()) {
            System.out.println(im.getId() + ":" + im.getHasRead() + ":" + im.getValid() + ":" + im.getSendTime());
        }
    }


    /**
     * 获取未读消息总条数
     */
    public void getUnreadCount() {
        MessageManageServiceImpl a = new MessageManageServiceImpl();
        try {
            int aa = a.getUnreadCount(141, 1, null);
            System.out.println(aa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 分页获取未读消息
     */
    public void getUnreadList() {
        MessageManageServiceImpl a = new MessageManageServiceImpl();
        try {
            Page<ImsMessage> aa = a.getUnreadImsMessages(141, 1, null, 1, 10);

            for (ImsMessage im : aa.getDatas()) {
                System.out.println(im.getId() + ":" + im.getHasRead() + ":" + im.getValid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置消息已读
     */
    public void setMessageRead() {
        MessageManageServiceImpl a = new MessageManageServiceImpl();
        Integer result = a.updateMessageRead("888888-1463361717647-986");//消息ID， 自定义消息内容
        System.out.println(result);
    }

    class newObject {

        private int id = 2;
        private String params = "水电费";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }
    }


    /**
     * 获取未读消息，成功返回之后 自动标记当前消息已读
     *
     * @throws RemoteException
     */
    public void pollMessage() {

        MessageManageServiceImpl a = new MessageManageServiceImpl();

        for (int i = 0; i < 1; i++) {
            Page<ImsMessage> aa = a.rmiPollImsMessages(70061, 1, "remind_alert", 1, 1);
//            Page<ImsMessage> aa = a.rmiUnreadImsMessages(58156, 1, "syn_alert", 1, 1);
            //System.out.println(aa.getDatas().size());
            /*for (ImsMessage im : aa.getDatas()) {
                System.out.println(im.getId() + ":" + im.getHasRead() + ":" + im.getValid());
            }*/
            System.out.println("1111111111");
        }

    }

}
