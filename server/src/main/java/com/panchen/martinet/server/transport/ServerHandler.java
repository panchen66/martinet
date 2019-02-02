package com.panchen.martinet.server.transport;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.panchen.martinet.common.transport.TransportHandler;
import com.panchen.martinet.common.transport.TransportListener;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends TransportHandler {
    
    private final static Log logger = LogFactory.getLog(ServerHandler.class);
    
    protected ServerHandler(List<TransportListener> listeners) {
        super(listeners);
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info(ctx.channel().config());
    }
    
}
