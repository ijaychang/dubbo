package com.alibaba.dubbo.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class GroupChatServer {
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final int port;

    public GroupChatServer(int port) throws IOException {
        this.selector = Selector.open();
        this.port = port;
        // 打开serverSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", port);

        serverSocketChannel.bind(address);
        serverSocketChannel.configureBlocking(false);
        // 把serverSocketChannel注册到选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    public void listen() throws IOException {
        for (; ; ) {
            //获取监听的事件总数
            if (selector.select(3000) == 0) {
                System.out.println("服务器等待3秒，没连接事件");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();

            for (; it.hasNext(); ) {
                SelectionKey selectionKey = it.next();
                // 获取连接事件
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println(socketChannel.getRemoteAddress() + "上线了~");
                }
                // 读就绪事件
                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = null;
                    try {
                        socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        socketChannel.read(buffer);
                        String message = "From " + socketChannel.getRemoteAddress() + " say " + new String(buffer.array(), StandardCharsets.UTF_8);
                        System.out.println(message);
                        notifyAllClients(message, socketChannel);
                    } catch (IOException ex) {
                        System.out.println(socketChannel.getRemoteAddress() + "离线了~");
                        try {
                            // 取消注册
                            selectionKey.cancel();
                            // 关闭通道
                            socketChannel.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }
                it.remove();
            }
        }
    }

    /**
     * 通知其他客户端
     *
     * @param message              消息内容
     * @param excludeSocketChannel 不需要发的SocketChannel
     * @throws IOException
     */
    private void notifyAllClients(String message, SocketChannel excludeSocketChannel) throws IOException {
        Set<SelectionKey> selectionKeys = selector.keys();
        for (SelectionKey selectionKey : selectionKeys) {
            SelectableChannel channel = selectionKey.channel();
            if (channel instanceof SocketChannel && channel != excludeSocketChannel) {
                SocketChannel socketChannel = (SocketChannel) channel;
                socketChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        GroupChatServer groupChatServer = new GroupChatServer(6667);
        groupChatServer.listen();

    }
}
