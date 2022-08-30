package com.alibaba.dubbo.nio;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteBufTest {
    public static void main(String[] args) throws IOException {
        // 返回的是HeapByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String msg = "这是Java NIO示例！";
        byteBuffer.put(msg.getBytes(StandardCharsets.UTF_8));

        // 切换为读模式
        byteBuffer.flip();
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        for (;byteBuffer.hasRemaining();) {
            baos.write(byteBuffer.get());
        }
        baos.flush();
        System.out.println(new String(baos.toByteArray(),StandardCharsets.UTF_8));

    }
}
