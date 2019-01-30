package com.panchen.martinet.server.transport;

import java.net.InetSocketAddress;
import java.util.List;

import com.panchen.martinet.common.lifecycle.LifecycleEvent;
import com.panchen.martinet.common.transport.NettyTransport;
import com.panchen.martinet.common.transport.TransportListener;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerTransport extends NettyTransport {

    private ServerBootstrap sbs = new ServerBootstrap();

    // todo:netty sbs for cluster transport
    private void createClusterBootstrap() {}

    // netty sbs for client transport
    private void createServerBootstrap() {
        sbs.channel(NioServerSocketChannel.class);
        sbs.option(ChannelOption.SO_KEEPALIVE, true);
        sbs.option(ChannelOption.TCP_NODELAY, true);
        sbs.group(acceptorGroup(2));
        sbs.childHandler(getChannelInitializer());
        sbs.validate();
    }

    @Override
    public boolean getStats() {
        return running;
    }

    @Override
    protected void initInternal() {
        createServerBootstrap();
        createClusterBootstrap();
    }

    protected void startInternal(InetSocketAddress serverAdderss, InetSocketAddress clusterAddress) {
        try {
            if (!running) {
                running = true;
                startServerBootstrap(serverAdderss);
                startClusterBootstrap(clusterAddress);
            }
        } catch (Exception e) {
        } finally {
            running = false;
        }
    }

    private void startServerBootstrap(InetSocketAddress InetSocketAddress) throws InterruptedException {
        Channel c = bind(sbs, InetSocketAddress);
        c.closeFuture().sync();
    }

    private void startClusterBootstrap(InetSocketAddress InetSocketAddress) throws InterruptedException {}

    protected class ServerChannelInitializer extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast("dispatcher", new ServerHandler(listeners));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }

    @Override
    protected ChannelHandler getChannelInitializer() {
        return new ServerChannelInitializer();
    }

    @Override
    protected void startInternal() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void registListener(List<TransportListener> listeners) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void send(Object message) {
        // TODO Auto-generated method stub
        
    }
}
