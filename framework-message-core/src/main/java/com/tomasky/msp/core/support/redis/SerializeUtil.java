package com.tomasky.msp.core.support.redis;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2015/5/12.
 */
public class SerializeUtil {

    static Logger log = Logger.getLogger(SerializeUtil.class);
    /**
     *
     * <p>Title: ObjTOSerialize</p>
     * <p>Description: 序列化一个对象</p>
     * @param obj
     * @return
     * @author guangshuai.wang
     */
    public static byte[] objTOSerialize(Object obj){
        ObjectOutputStream oos;
        ByteArrayOutputStream byteOut;
        try{
            byteOut = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(byteOut);
            oos.writeObject(obj);
            byte[] bytes = byteOut.toByteArray();
            return bytes;
        }catch (Exception e) {
            log.error("serialize object failed");
        }
        return null;
    }
    /**
     *
     * <p>Title: unSerialize</p>
     * <p>Description: 反序列化</p>
     * @param bytes
     * @return
     * @author guangshuai.wang
     */
    public static Object unSerialize(byte[] bytes){
        ByteArrayInputStream in;
        try{
            in = new ByteArrayInputStream(bytes);
            ObjectInputStream objIn = new ObjectInputStream(in);
            return objIn.readObject();
        }catch (Exception e) {
            log.error("反序列化失败");
        }
        return null;
    }
}
