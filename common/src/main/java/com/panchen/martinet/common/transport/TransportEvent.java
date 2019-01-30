package com.panchen.martinet.common.transport;

import java.util.EventObject;

import io.netty.channel.ChannelHandlerContext;

public class TransportEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public ChannelHandlerContext ctx;

    public TransportEvent(Object msg, ChannelHandlerContext ctx) {
        super(msg);
        this.ctx = ctx;
    }

}
