package com.panchen.martinet.common.transport;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import com.panchen.martinet.common.lifecycle.LifecycleBase;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;

public abstract class NettyTransport extends LifecycleBase implements Transprot {

    // for stats
    protected volatile boolean running = false;
    // port->listeners
    protected Map<Integer, List<TransportListener>> listeners;

    protected void registListener(List<TransportListener> listener, int port) {
        listeners.put(port, listener);
    }

    protected abstract ChannelHandler getChannelInitializer();

    protected abstract void addDefalutListeners();

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

    public void send(Object message, Object channel) {
        ((Channel) channel).write(message);
        ((Channel) channel).flush();
    }
}
