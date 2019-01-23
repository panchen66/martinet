package com.panchen.martinet.common.transport;

import java.net.InetSocketAddress;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

public class MessageChannelHandler extends ChannelDuplexHandler {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.print(1);
        if (!(msg instanceof ByteBuf)) {
            return;
        }
        final ByteBuf buffer = (ByteBuf) msg;
        try {
            Channel channel = ctx.channel();
            InetSocketAddress remoteAddress = (InetSocketAddress) channel.remoteAddress();
            System.out.print(remoteAddress.getAddress());
        } finally {
        }
    }

}
