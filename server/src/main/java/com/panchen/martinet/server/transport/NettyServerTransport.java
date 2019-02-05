package com.panchen.martinet.server.transport;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import com.panchen.martinet.common.handler.HandlerRegistry;
import com.panchen.martinet.common.handler.MartinetHandler;
import com.panchen.martinet.common.transport.HeartbeatTransportListener;
import com.panchen.martinet.common.transport.NettyTransport;
import com.panchen.martinet.common.transport.TransportListener;
import com.panchen.martinet.server.bootstrap.MartinetServer;
import com.panchen.martinet.server.handle.CoutHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerTransport extends NettyTransport {

    MartinetServer martinetServer = MartinetServer.getApplicationContent();
    private ServerBootstrap sbs = new ServerBootstrap();
    private Channel serverChannel;
    // cache client info
    private Map<Date, String> client = new ConcurrentHashMap<>();
    private HandlerRegistry handlerRegistry;

    // todo:netty sbs for cluster transport
    private void createClusterBootstrap() {}


    // netty sbs for client transport
    private void createServerBootstrap() {
        sbs.channel(NioServerSocketChannel.class);
        sbs.childOption(ChannelOption.SO_KEEPALIVE, true);
        sbs.childOption(ChannelOption.TCP_NODELAY, true);
        sbs.group(acceptorGroup(martinetServer.getAcceptorCount()));
        sbs.childHandler(getChannelInitializer());
        sbs.validate();
    }

    @Override
    public boolean getStats() {
        return running;
    }

    private void startServerBootstrap(InetSocketAddress InetSocketAddress) throws InterruptedException {
        serverChannel = bind(sbs, InetSocketAddress);
    }

    private void startClusterBootstrap(InetSocketAddress InetSocketAddress) throws InterruptedException {}

    protected class ServerChannelInitializer extends ChannelInitializer<Channel> {

        ServerChannelInitializer(List<TransportListener> listeners) {
            this.listeners = listeners;
        }

        private List<TransportListener> listeners;

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
        return new ServerChannelInitializer(listeners.get(martinetServer.getPort()));
    }

    @Override
    protected void initInternal() {
        addDefalutListeners();
        createServerBootstrap();
        createClusterBootstrap();
    }


    @Override
    protected void startInternal() {
        try {
            if (!running) {
                running = true;
                startServerBootstrap(new InetSocketAddress(martinetServer.getPort()));
            }
        } catch (Exception e) {
        } finally {
            running = false;
        }
    }

    @Override
    protected void addDefalutListeners() {
        addDefalutHandlers();
        List<TransportListener> listeners = new ArrayList<>();
        // hearbeat
        listeners.add(new HeartbeatTransportListener(handlerRegistry));
        registListener(listeners, martinetServer.getPort());
    }

    @Override
    protected void addDefalutHandlers() {
        // count
        handlerRegistry.put(new CoutHandler());
    }


}
