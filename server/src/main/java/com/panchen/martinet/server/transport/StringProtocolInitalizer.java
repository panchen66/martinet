package com.panchen.martinet.server.transport;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class StringProtocolInitalizer extends ChannelInitializer<SocketChannel> {

    StringDecoder stringDecoder;

    StringEncoder stringEncoder;

    ServerHandler serverHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", stringDecoder);
        pipeline.addLast("handler", serverHandler);
        pipeline.addLast("encoder", stringEncoder);
    }

}
