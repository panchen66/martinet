package com.panchen.martinet.client.transport;

import java.net.InetSocketAddress;
import java.util.List;
import com.panchen.martinet.common.lifecycle.LifecycleEvent;
import com.panchen.martinet.common.transport.NettyTransport;
import com.panchen.martinet.common.transport.TransportListener;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClientTransprot extends NettyTransport {
    
    private Bootstrap b;


    public ChannelHandler getChannelInitializer() {
        return new ClientChannelInitializer();
    }

    private void createClientBootstrap(InetSocketAddress InetSocketAddress, int maxThreadSize) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup(maxThreadSize);
        b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).remoteAddress(InetSocketAddress).handler(getChannelInitializer());
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.option(ChannelOption.TCP_NODELAY, true);
        b.validate();
    }

    protected class ClientChannelInitializer extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast("clientHandler", new ClientHandler(listeners));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }

    public void start() {
        ChannelFuture cf;
        try {
            cf = b.connect().sync();
        } catch (InterruptedException e) {
        }
    }


    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {
        try {
            createClientBootstrap(null, 0);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void lifecycleEvent(LifecycleEvent event) {

    }

    @Override
    protected void registListener(List<TransportListener> listeners) {

    }

    @Override
    public void send(Object message) {
        
    }

}
