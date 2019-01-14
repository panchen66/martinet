package com.panchen.martinet.server.transport;

import java.net.InetSocketAddress;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;

public class NettyTransport implements Transport {


    private int acceptorCount;

    private int tcpPort;


    public boolean start() {
        createClusterBootstrap();
       // createServerBootstrap();
        return false;

    }

    // netty sbs for cluster transport
    @SuppressWarnings("unchecked")
    private void createClusterBootstrap() {
        ServerBootstrap sbs = new ServerBootstrap();
        sbs.channel(NioServerSocketChannel.class);

        sbs.option(ChannelOption.SO_KEEPALIVE, true);
        sbs.option(ChannelOption.TCP_NODELAY, true);

        sbs.group(acceptorGroup());


        sbs.childHandler(new ServerChannelInitializer());

        sbs.validate();

        //
        sbs.bind(8888).addListeners(new ClientChannelFutureListener());
        //sbs.bind(8888);
        
    }
    
    
    // netty sbs for client transport
    private void createServerBootstrap() {
        ServerBootstrap sbs = new ServerBootstrap();
        sbs.channel(NioServerSocketChannel.class);
        sbs.option(ChannelOption.SO_KEEPALIVE, true);
        sbs.option(ChannelOption.TCP_NODELAY, true);
        sbs.group(acceptorGroup());
        sbs.validate();
    }


    private NioEventLoopGroup acceptorGroup() {
        return new NioEventLoopGroup(acceptorCount);
    }


    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(tcpPort);
    }


    private class ServerChannelInitializer extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            // TODO Auto-generated method stub
            ch.pipeline().addLast("streamHandler", new MessageChannelHandler());
        }

    }


    private class ClientChannelInitializer extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            // TODO Auto-generated method stub

        }

    }

    private class ClientChannelFutureListener implements ChannelFutureListener {

        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            // TODO Auto-generated method stub
            System.out.print(2);
        }

    }
}
