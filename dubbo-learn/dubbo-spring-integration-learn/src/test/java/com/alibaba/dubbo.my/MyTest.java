package com.alibaba.dubbo.my;

import com.alibaba.dubbo.my.spring.config.ApplicationConfig;
import com.alibaba.dubbo.my.spring.config.ServiceBeanConfig;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.ws.Service;


public class MyTest {

    @Test
    public void test1(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config/my-spring.xml");
        ctx.start();
        try {
            ApplicationConfig applicationConfig = ctx.getBean(ApplicationConfig.class);
            ServiceBeanConfig serviceBeanConfig = ctx.getBean(ServiceBeanConfig.class);
            System.out.println(applicationConfig);
            System.out.println(serviceBeanConfig);
            System.out.println("started");
        } finally {
            ctx.stop();
            ctx.close();
        }
    }
}
