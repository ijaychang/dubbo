package com.alibaba.dubbo.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SocketChannelTest {
    /**
     * 没有用Selector的例子
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", 9876));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        for (; ; ) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            for (; socketChannel.read(byteBuffer) != -1; ) {
                byte[] array = byteBuffer.array();
                System.out.println(new String(array, StandardCharsets.UTF_8));
            }
            byteBuffer.clear();
        }

    }
}
