package com.dhr.io;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 11 二月 2019
 */
public class IOClient {


    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8099);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
        EventLoopGroup group = new NioEventLoopGroup(1, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) {
            }
        });
        ChannelFuture connect = bootstrap.connect("127.0.0.1", 8099);
        connect.sync();
        if (connect.isSuccess()) {
            connect.channel().writeAndFlush("abasiduwhaiudhwaiu".getBytes());
            System.out.println("发送成功！");
        }
        connect.channel().closeFuture().sync();

    }

}
