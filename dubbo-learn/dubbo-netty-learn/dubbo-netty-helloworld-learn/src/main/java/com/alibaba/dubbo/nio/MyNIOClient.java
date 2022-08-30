package com.alibaba.dubbo.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class MyNIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 9876));
        if (!connect) {
            while (socketChannel.finishConnect()) {
                System.out.println("连接服务器中...可以做点其他事情");
            }
        }
        socketChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.wrap("这是Java NIO 示例！".getBytes(StandardCharsets.UTF_8));
        socketChannel.write(byteBuffer);
        System.in.read();

    }
}
