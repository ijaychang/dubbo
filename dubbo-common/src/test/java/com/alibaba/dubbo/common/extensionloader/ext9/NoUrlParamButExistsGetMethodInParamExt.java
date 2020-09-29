package com.alibaba.dubbo.common.extensionloader.ext9;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 *
 * <p>
 *  TODO 类作用描述
 * </p>
 *
 * @author zhangjie
 * @since 2020-09-29
 */
@SPI("impl1")
public interface NoUrlParamButExistsGetMethodInParamExt {
    @Adaptive
    String echo(String name,Foo foo);
}
