package com.alibaba.dubbo.my.spring.config;

import java.io.Serializable;
import java.util.Map;

public class ServiceBeanConfig implements Serializable {
    /**
     * 引用bean id
     */
    private String ref;

    private Object reference;
    /**
     * 接口全限定名
     */
    private String interfaceClassName;

    private Class<?> interfaceClass;

    private Map<String, String> parameters;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getInterfaceClassName() {
        return interfaceClassName;
    }

    public void setInterfaceClassName(String interfaceClassName) {
        this.interfaceClassName = interfaceClassName;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Object getReference() {
        return reference;
    }

    public void setReference(Object reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "ServiceBeanConfig{" +
                "ref='" + ref + '\'' +
                "reference='" + reference + '\'' +
                ", interfaceClassName='" + interfaceClassName + '\'' +
                ", interfaceClass=" + interfaceClass +
                ", parameters=" + parameters +
                '}';
    }
}
