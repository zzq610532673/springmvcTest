package com.zzq.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for (Channel channel1 : channels) {
            if (channel1 != channel) {
                channel1.writeAndFlush("[欢迎: " + channel.remoteAddress() + "] 进入聊天室！\n");
            }
        }
        channels.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for (Channel channel1 : channels) {
            channel1.writeAndFlush("[再见: ]" + channel1.remoteAddress() + " 离开聊天室！\n");
        }
        channels.remove(channel);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();
        for (Channel channel1 : channels) {
            if (channel1 != channel) {
                channel1.writeAndFlush("[用户" + channel1.remoteAddress() + " 说：]" + s + "\n");
            }else{
                channel1.writeAndFlush("[我说：]" + s + "\n");
            }
        }
    }

    /**
     * 当服务器监听到客户端活动时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("["+channel.remoteAddress()+"]:在线");
    }

    /**
     * 离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[" + channel.remoteAddress() + "]: 离线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"通讯异常");
        ctx.close();
    }
}
