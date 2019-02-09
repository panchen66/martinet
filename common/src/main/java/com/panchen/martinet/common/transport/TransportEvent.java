package com.panchen.martinet.common.transport;

import java.util.EventObject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class TransportEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public ChannelHandlerContext ctx;

    public String value;

    public TransportEvent(Object msg, ChannelHandlerContext ctx) {
        super(msg);
        this.ctx = ctx;
        if (msg instanceof ByteBuf) {
            ByteBuf buf = (ByteBuf) msg;
            if (buf.hasArray()) {
                value = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
            } else {
                byte[] bytes = new byte[buf.readableBytes()];
                buf.getBytes(buf.readerIndex(), bytes);
                value = new String(bytes, 0, buf.readableBytes());
            }
        }
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }
    
    public String stringValue() {
        return value;
    }
}
