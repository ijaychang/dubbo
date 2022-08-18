// 该代码是由com.alibaba.dubbo.common.extension.ExtensionLoader.createAdaptiveExtensionClassCode生成，即运行时生成的
// 这里只是把这段字符串代码给拷出来了，以便结合生成的代码理解dubbo ExtensionLoader的动态编译过程，同时也可以用来debug了 (￣▽￣)
package com.alibaba.dubbo.common.extensionloader.ext3;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class UseProtocolKeyExt$Adaptive implements UseProtocolKeyExt {
    // com.alibaba.dubbo.common.extensionloader.ext3.UseProtocolKeyExt.echo接口方法加了@Adaptive({"key1", "protocol"})注解，所以生成的代码String extName = url.getParameter("key1", url.getProtocol() == null ? "impl1" : url.getProtocol());
    // 当arg0(com.alibaba.dubbo.common.URL)中key1,protocol对应的值都存在且都不为空，那么key1的优先级大于protocol
    public String echo(com.alibaba.dubbo.common.URL arg0, String arg1) {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        com.alibaba.dubbo.common.URL url = arg0;
        String extName = url.getParameter("key1", url.getProtocol() == null ? "impl1" : url.getProtocol());
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt) name from url(" + url.toString() + ") use keys([simple.ext])");
        UseProtocolKeyExt extension = (UseProtocolKeyExt) ExtensionLoader.getExtensionLoader(UseProtocolKeyExt.class).getExtension(extName);
        return extension.echo(arg0, arg1);
    }


    // com.alibaba.dubbo.common.extensionloader.ext3.UseProtocolKeyExt#yell接口方法加了@Adaptive({"protocol", "key2"})注解，所以生成的代码String extName = url.getProtocol() == null ? (url.getParameter("key2","impl1")) : url.getProtocol();
    // 当arg0(com.alibaba.dubbo.common.URL)中protocol,key2对应的值都存在且都不为空，那么protocol的优先级大于key2
    public String yell(com.alibaba.dubbo.common.URL arg0, String arg1) {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        com.alibaba.dubbo.common.URL url = arg0;
        String extName = url.getProtocol() == null ? (url.getParameter("key2", "impl1")) : url.getProtocol();
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt) name from url(" + url.toString() + ") use keys([key1, key2])");
        UseProtocolKeyExt extension = (UseProtocolKeyExt) ExtensionLoader.getExtensionLoader(UseProtocolKeyExt.class).getExtension(extName);
        return extension.yell(arg0, arg1);
    }
}