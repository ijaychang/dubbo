package com.alibaba.dubbo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {
    public static void main(String[] args) {
        // 创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 设置服务端通道实现类别
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.group(bossGroup,workerGroup);
            // 设置线程队列得到连接数
            bootstrap.option(ChannelOption.SO_BACKLOG,128);
            // 设置保持活动连接状态
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
            // 使用匿名内部类形式初始化通道对象
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //给pipeline管道设置处理器
                    socketChannel.pipeline().addLast(new MyServerHandler());
                }
            });
            System.out.println("netty 服务端已准备就绪");
            ChannelFuture channelFuture = bootstrap.bind(9876).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
