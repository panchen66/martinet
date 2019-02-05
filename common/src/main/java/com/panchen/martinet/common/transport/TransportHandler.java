package com.panchen.martinet.common.transport;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class TransportHandler extends SimpleChannelInboundHandler<Object> {

    protected static final String PING_MESSAGE = "MARTINET";
    protected List<TransportListener> listeners;

    protected TransportHandler(List<TransportListener> listeners) {
        this.listeners = listeners;
    }

    protected void ping(ChannelHandlerContext ctx) {
        ByteBuf encoded = ctx.alloc().buffer(4 * PING_MESSAGE.length());
        encoded.writeBytes(PING_MESSAGE.getBytes());
        ctx.writeAndFlush(encoded);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        for (TransportListener listener : listeners) {
            if (listener.fireAfterTransportEventInvoked(new TransportEvent(msg, ctx))) {
                break;
            }
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {}
}

