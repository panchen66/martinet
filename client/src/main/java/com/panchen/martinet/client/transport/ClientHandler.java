package com.panchen.martinet.client.transport;

import java.util.List;
import com.panchen.martinet.common.transport.TransportHandler;
import com.panchen.martinet.common.transport.TransportListener;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends TransportHandler {

    protected ClientHandler(List<TransportListener> listeners) {
        super(listeners);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ping(ctx);
    }

}
