package com.alibaba.dubbo.my.spring;

import com.alibaba.dubbo.my.spring.config.ApplicationConfig;
import com.alibaba.dubbo.my.spring.config.ServiceBeanConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.Assert;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.List;

public class MyBeanDefinitionParser implements BeanDefinitionParser {
    private Class<?> beanClass;

    private static final List<Class> allowedBeanClasses = Arrays.asList(new Class[]{ApplicationConfig.class, ServiceBeanConfig.class});

    public MyBeanDefinitionParser(Class<?> beanClass) {
        Assert.notNull(beanClass, "beanClass must not null");
        Assert.isTrue(allowedBeanClasses.contains(beanClass), "beanClass type not allowed");
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        if (ApplicationConfig.class.equals(this.beanClass)) {
            String name = element.getAttribute("name");
            String version = element.getAttribute("version");
            beanDefinition.getPropertyValues().add("name", name);
            beanDefinition.getPropertyValues().add("version", version);
        }
        if (ServiceBeanConfig.class.equals(this.beanClass)) {
            String reference = element.getAttribute("ref");
            String infClazzName = element.getAttribute("interface");
            beanDefinition.getPropertyValues().add("reference", reference);
            try {
                beanDefinition.getPropertyValues().add("interfaceClass", Class.forName(infClazzName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            NodeList paramsNodeList = element.getChildNodes();
            for (int i = 0; i < paramsNodeList.getLength(); i++) {
                Node node = paramsNodeList.item(i);
                System.out.println(node);
            }
        }
        return beanDefinition;
    }
}
