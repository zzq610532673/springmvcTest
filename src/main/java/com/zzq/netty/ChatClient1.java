package com.zzq.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatClient1 {
    private String host;
    private int port;

    public ChatClient1(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());
            Channel channel = b.connect(host, port).sync().channel();
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
            while (true) {
                channel.writeAndFlush(input.readLine() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ChatClient1("localhost",8888).start();
    }
}
