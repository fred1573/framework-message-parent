package com.tomasky.msp.support;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Created by Administrator on 2015/5/15.
 */
public class ContextContainer {

    private static final ApplicationContext CTX = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

    public static ApplicationContext getContext(){
        return CTX;
    }

}
