// 该代码是由com.alibaba.dubbo.common.extension.ExtensionLoader.createAdaptiveExtensionClassCode生成，即运行时生成的
// 这里只是把这段字符串代码给拷出来了，以便结合生成的代码理解dubbo ExtensionLoader的动态编译过程，同时也可以用来debug了 (￣▽￣)
package com.alibaba.dubbo.common.extensionloader.ext2;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class Ext2$Adaptive implements com.alibaba.dubbo.common.extensionloader.ext2.Ext2 {
    public java.lang.String bang(com.alibaba.dubbo.common.URL arg0, int arg1) {
        throw new UnsupportedOperationException("method public abstract java.lang.String com.alibaba.dubbo.common.extensionloader.ext2.Ext2.bang(com.alibaba.dubbo.common.URL,int) of interface com.alibaba.dubbo.common.extensionloader.ext2.Ext2 is not adaptive method!");
    }

    public java.lang.String echo(com.alibaba.dubbo.common.extensionloader.ext2.UrlHolder arg0, java.lang.String arg1) {
        if (arg0 == null)
            throw new IllegalArgumentException("com.alibaba.dubbo.common.extensionloader.ext2.UrlHolder argument == null");
        if (arg0.getUrl() == null)
            throw new IllegalArgumentException("com.alibaba.dubbo.common.extensionloader.ext2.UrlHolder argument getUrl() == null");
        com.alibaba.dubbo.common.URL url = arg0.getUrl();
        String extName = url.getParameter("ext2");
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.common.extensionloader.ext2.Ext2) name from url(" + url.toString() + ") use keys([ext2])");
        com.alibaba.dubbo.common.extensionloader.ext2.Ext2 extension = (com.alibaba.dubbo.common.extensionloader.ext2.Ext2) ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.common.extensionloader.ext2.Ext2.class).getExtension(extName);
        return extension.echo(arg0, arg1);
    }
}
