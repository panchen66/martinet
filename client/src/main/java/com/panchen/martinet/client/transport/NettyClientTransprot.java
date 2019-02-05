package com.panchen.martinet.client.transport;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import com.panchen.martinet.client.base.MartinetClient;
import com.panchen.martinet.client.transport.ClientHandler;
import com.panchen.martinet.common.transport.HeartbeatTransportListener;
import com.panchen.martinet.common.transport.NettyTransport;
import com.panchen.martinet.common.transport.TransportListener;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClientTransprot extends NettyTransport {

    private Bootstrap b;
    private Channel channel;

    public ChannelHandler getChannelInitializer() {
        return new ClientChannelInitializer(listeners.get(MartinetClient.getApplicationContent().getPort()));
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

        ClientChannelInitializer(List<TransportListener> listeners) {
            this.listeners = listeners;
        }

        private List<TransportListener> listeners;

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast("clientHandler", new ClientHandler(listeners));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }


    private InetSocketAddress analysisServer() {
        String[] InetSocketAddressinfos = StringUtils.split(MartinetClient.getApplicationContent().getServer(), ":");
        InetSocketAddress serverInetSocketAddress =
                new InetSocketAddress(InetSocketAddressinfos[0], Integer.valueOf(InetSocketAddressinfos[1]));
        return serverInetSocketAddress;
    }

    @Override
    protected void initInternal() {
        addDefalutListeners();
        try {
            createClientBootstrap(analysisServer(), MartinetClient.getApplicationContent().getMaxThreadSize());
        } catch (InterruptedException e) {
        }
    }

    @Override
    protected void startInternal() {
        try {
            b.bind(MartinetClient.getApplicationContent().getPort());
            channel = b.connect().sync().channel();
        } catch (InterruptedException e) {
        }
    }


    protected void addDefalutListeners() {
        List<TransportListener> listeners = new ArrayList<>();
        // hearbeat
        listeners.add(new HeartbeatTransportListener(handlerRegistry));
        registListener(listeners, MartinetClient.getApplicationContent().getPort());
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    protected void addDefalutHandlers() {
        // TODO Auto-generated method stub

    }


}
