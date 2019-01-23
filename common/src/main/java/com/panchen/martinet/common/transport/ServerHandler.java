package com.panchen.martinet.common.transport;

import java.net.InetAddress;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String clientIdToLong = ctx.channel().id().asLongText();
        String clientIdToShort = ctx.channel().id().asShortText();
        if (msg.indexOf("bye") != -1) {
            // close
            ctx.channel().close();
        } else {
            // send to client
            ctx.channel().writeAndFlush("Yoru msg is:" + msg);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.channel().writeAndFlush(
                "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }



}
