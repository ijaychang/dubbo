package com.alibaba.dubbo.my.spring;

import com.alibaba.dubbo.my.spring.config.ApplicationConfig;
import com.alibaba.dubbo.my.spring.config.ServiceBeanConfig;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


public class MyNameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("application", new MyBeanDefinitionParser(ApplicationConfig.class));
        registerBeanDefinitionParser("service", new MyBeanDefinitionParser(ServiceBeanConfig.class));
    }
}
