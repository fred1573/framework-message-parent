package com.tomasky.msp.support.util;

import java.util.Random;

/**
 * Created by Administrator on 2015/5/15.
 */
public class PKGenerator {

    private static final Integer LIMIT_RANDOM = 10000;
    /**
     * pk = ts + '-' + random num in LIMIT_RANDOM
     * @return String
     */
    public static String generate(){
        Long ts = System.currentTimeMillis();
        int ran = new Random().nextInt(LIMIT_RANDOM);
        return new StringBuffer(ts.toString()).append("-").append(ran).toString();
    }

    /**
     * pk = innId + "-" + ts + random num in LIMIT_RANDOM
     * @param innId
     * @return String
     */
    public static String generate(Integer innId){
        String id = innId.toString() + "-" + generate();
        return id;
    }
}
