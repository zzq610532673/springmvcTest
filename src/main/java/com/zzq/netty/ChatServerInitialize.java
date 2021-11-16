package com.zzq.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChatServerInitialize extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast("frame", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                .addLast("decode", new StringDecoder())
                .addLast("encode", new StringEncoder())
                .addLast("handler",new ChatServerHandler());
    }
}
