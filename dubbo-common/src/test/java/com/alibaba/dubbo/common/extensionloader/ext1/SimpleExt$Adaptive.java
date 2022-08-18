// 该代码是由com.alibaba.dubbo.common.extension.ExtensionLoader.createAdaptiveExtensionClassCode生成，即运行时生成的
// 这里只是把这段字符串代码给拷出来了，以便结合生成的代码理解dubbo ExtensionLoader的动态编译过程，同时也可以用来debug了 (￣▽￣)
package com.alibaba.dubbo.common.extensionloader.ext1;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class SimpleExt$Adaptive implements com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt {
    public java.lang.String echo(com.alibaba.dubbo.common.URL arg0, java.lang.String arg1) {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        com.alibaba.dubbo.common.URL url = arg0;
        String extName = url.getParameter("simple.ext", "impl1");
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt) name from url(" + url.toString() + ") use keys([simple.ext])");
        com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt extension = (com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt) ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt.class).getExtension(extName);
        return extension.echo(arg0, arg1);
    }

    // com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt.yell 接口方法@Adaptive注解的value值是{"key1", "key2"}，所以生成代码String extName = url.getParameter("key1", url.getParameter("key2", "impl1"));
    // 当arg0(com.alibaba.dubbo.common.URL)中key1,key2都存在且都不为空，那么key1的优先级大于key2
    public java.lang.String yell(com.alibaba.dubbo.common.URL arg0, java.lang.String arg1) {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        com.alibaba.dubbo.common.URL url = arg0;
        String extName = url.getParameter("key1", url.getParameter("key2", "impl1"));
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt) name from url(" + url.toString() + ") use keys([key1, key2])");
        com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt extension = (com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt) ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt.class).getExtension(extName);
        return extension.yell(arg0, arg1);
    }

    // com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt.bang 接口方法没有@Adaptive注解，生成的代码就是直接抛出UnsupportedOperationException异常
    public java.lang.String bang(com.alibaba.dubbo.common.URL arg0, int arg1) {
        throw new UnsupportedOperationException("method public abstract java.lang.String com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt.bang(com.alibaba.dubbo.common.URL,int) of interface com.alibaba.dubbo.common.extensionloader.ext1.SimpleExt is not adaptive method!");
    }
}