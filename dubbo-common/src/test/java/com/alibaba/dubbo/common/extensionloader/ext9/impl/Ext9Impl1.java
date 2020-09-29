package com.alibaba.dubbo.common.extensionloader.ext9.impl;

import com.alibaba.dubbo.common.extensionloader.ext9.Foo;
import com.alibaba.dubbo.common.extensionloader.ext9.NoUrlParamButExistsGetMethodInParamExt;

/**
 *
 * <p>
 *  TODO 类作用描述
 * </p>
 *
 * @author zhangjie
 * @since 2020-09-29
 */
public class Ext9Impl1 implements NoUrlParamButExistsGetMethodInParamExt {
    @Override
    public String echo(String name, Foo foo) {
        System.out.println("Ext9Impl1.echo");
        return "Ext9Impl1.echo";
    }
}
