package com.dhr.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 *
 * @author haoran.duan
 * @since 11 二月 2019
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boos, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ChannelInitializer() {

                            @Override
                            protected void initChannel(Channel ch) throws Exception {
                                ChannelPipeline p = ch.pipeline();
                                p.addLast(new StringDecoder());
                                p.addLast(new StringEncoder());
                                p.addLast(new HelloServerHandler());

                            }
                        });
                    }
                })
                .bind(8000).sync();
    }

    static class HelloServerHandler extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("获取到信息:"+msg);
            ctx.writeAndFlush("success");
            ctx.close();
        }
    }
}

