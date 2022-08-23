package com.alibaba.dubbo.my.spring.config;

import java.io.Serializable;

public class ApplicationConfig implements Serializable {
    private String name;
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ApplicationConfig{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
