package com.alibaba.dubbo.my.spring;

public class DemoServiceImpl implements DemoService{
    @Override
    public String say() {
        return "say hello";
    }
}
