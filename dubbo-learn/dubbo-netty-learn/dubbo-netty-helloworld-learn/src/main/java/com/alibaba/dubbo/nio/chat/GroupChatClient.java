package com.alibaba.dubbo.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class GroupChatClient {
    private final Selector selector;
    private final int port;
    private final SocketChannel socketChannel;
    private final String userName;

    public GroupChatClient(int port) throws IOException {
        this.port = port;

        selector = Selector.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", this.port);
        socketChannel = SocketChannel.open(address);
        socketChannel.configureBlocking(false);
        socketChannel.register(this.selector, SelectionKey.OP_READ);
        //获取用户名
        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(userName + " is ok~");
    }

    // 发送消息给服务端
    public void sendMessage(String message) {
        try {
            socketChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取服务端发送过来的消息
    public void readMessage() throws IOException {
        int count = selector.select();
        if (count == 0) {
            return;
        }
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> it = selectionKeys.iterator();

        for (; it.hasNext(); ) {
            SelectionKey selectionKey = it.next();
            if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                socketChannel.read(buffer);
                System.out.println(new String(buffer.array(), StandardCharsets.UTF_8));
            }
            it.remove();
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatClient groupChatClient = new GroupChatClient(6667);

        new Thread(() -> {
            for (; ; ) {
                try {
                    groupChatClient.readMessage();
                    Thread.sleep(3000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        for (; scanner.hasNextLine(); ) {
            String line = scanner.nextLine();
            if ("end".equals(line)) {
                break;
            }
            groupChatClient.sendMessage(line);
        }
    }
}
