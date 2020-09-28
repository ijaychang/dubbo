package com.alibaba.dubbo.rpc;

import com.alibaba.dubbo.common.compiler.Compiler;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * <p>
 *  TODO 类作用描述
 * </p>
 *
 * @author zhangjie
 * @since 2020-09-28
 */
public class JavassistCompilerTest {
    @Test
    public void test_compileProtocol() throws Exception {
        String code = "package com.alibaba.dubbo.rpc;\n" + "\n" + "import com.alibaba.dubbo.common.extension.ExtensionLoader;\n" + "\n" + "@SuppressWarnings(\"ALL\")\n"
                + "public class Protocol$Adaptive implements com.alibaba.dubbo.rpc.Protocol {\n" + "    public void destroy() {\n"
                + "        throw new UnsupportedOperationException(\"method public abstract void com.alibaba.dubbo.rpc.Protocol.destroy() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!\");\n"
                + "    }\n" + "\n" + "    public int getDefaultPort() {\n"
                + "        throw new UnsupportedOperationException(\"method public abstract int com.alibaba.dubbo.rpc.Protocol.getDefaultPort() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!\");\n"
                + "    }\n" + "\n" + "    public com.alibaba.dubbo.rpc.Invoker refer(java.lang.Class arg0, com.alibaba.dubbo.common.URL arg1) throws com.alibaba.dubbo.rpc.RpcException {\n"
                + "        if (arg1 == null)\n" + "            throw new IllegalArgumentException(\"url == null\");\n" + "        com.alibaba.dubbo.common.URL url = arg1;\n"
                + "        String extName = (url.getProtocol() == null ? \"dubbo\" : url.getProtocol());\n" + "        if (extName == null)\n"
                + "            throw new IllegalStateException(\"Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(\" + url.toString() + \") use keys([protocol])\");\n"
                + "        com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol) ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);\n"
                + "        return extension.refer(arg0, arg1);\n" + "    }\n" + "\n"
                + "    public com.alibaba.dubbo.rpc.Exporter export(com.alibaba.dubbo.rpc.Invoker arg0) throws com.alibaba.dubbo.rpc.RpcException {\n" + "        if (arg0 == null)\n"
                + "            throw new IllegalArgumentException(\"com.alibaba.dubbo.rpc.Invoker argument == null\");\n" + "        if (arg0.getUrl() == null)\n"
                + "            throw new IllegalArgumentException(\"com.alibaba.dubbo.rpc.Invoker argument getUrl() == null\");\n" + "        com.alibaba.dubbo.common.URL url = arg0.getUrl();\n"
                + "        String extName = (url.getProtocol() == null ? \"dubbo\" : url.getProtocol());\n" + "        if (extName == null)\n"
                + "            throw new IllegalStateException(\"Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(\" + url.toString() + \") use keys([protocol])\");\n"
                + "        com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol) ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);\n"
                + "        return extension.export(arg0);\n" + "    }\n" + "}";

        Compiler compiler = ExtensionLoader.getExtensionLoader(Compiler.class).getDefaultExtension();
        Class<?> clazz = compiler.compile(code, JavassistCompilerTest.class.getClassLoader());
        Assert.assertNotNull("clazz must not null",clazz);
    }
}
