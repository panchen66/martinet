package com.panchen.martinet.common.transport;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.panchen.martinet.common.handler.HandlerRegistry;
import com.panchen.martinet.common.io.TransportMeta;
import com.panchen.martinet.common.lifecycle.LifecycleBase;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;

public abstract class NettyTransport extends LifecycleBase implements Transprot {

    // for stats
    protected volatile boolean running = false;
    // port->listeners
    protected Map<Integer, List<TransportListener>> listeners = new ConcurrentHashMap<>();
    public HandlerRegistry handlerRegistry;
    
    protected void registListener(List<TransportListener> listener, int port) {
        listeners.put(port, listener);
    }

    protected abstract ChannelHandler getChannelInitializer();

    protected abstract void addDefalutListeners();
    
    protected abstract void addDefalutHandlers();
    
    protected Channel bind(ServerBootstrap sb, InetSocketAddress address) {
        return sb.bind(address).syncUninterruptibly().channel();
    }

    public NioEventLoopGroup acceptorGroup(int acceptorCount) {
        return new NioEventLoopGroup(acceptorCount);
    }

    public boolean getStats() {
        return running;
    }

    InetSocketAddress tcpPort(int tcpPort) {
        return new InetSocketAddress(tcpPort);
    }

    public void send(Object channel, TransportMeta transportMeta) {
        ((Channel) channel).writeAndFlush(transportMeta.value());
    }
}
