package com.panchen.martinet.common.transport;

import java.net.InetSocketAddress;
import java.util.List;

import com.panchen.martinet.common.lifecycle.LifecycleBase;
import com.panchen.martinet.common.lifecycle.LifecycleListener;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;

public abstract class NettyTransport extends LifecycleBase implements LifecycleListener, Transprot {

    // for stats
    protected volatile boolean running = false;
    protected List<TransportListener> listeners;

    protected abstract void registListener(List<TransportListener> listeners);

    protected abstract ChannelHandler getChannelInitializer();

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
}
