package com.panchen.martinet.server.bootstrap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.panchen.martinet.common.lifecycle.LifecycleBase;
import com.panchen.martinet.common.transport.NettyTransport;
import com.panchen.martinet.server.transport.NettyServerTransport;

public class MartinetServer extends LifecycleBase {

    private final static Log logger = LogFactory.getLog(MartinetServer.class);

    private static MartinetServer singleton = new MartinetServer();

    private NettyTransport nettyTransport = new NettyServerTransport();
    private int port = 1901;
    private int acceptorCount = 2;

    public static MartinetServer getApplicationContent() {
        return singleton;
    }

    public class MartinetServerBuilder {

        MartinetServer martinetServer;

        public MartinetServerBuilder() {
            martinetServer = getApplicationContent();
        }

        public MartinetServerBuilder transprot(NettyTransport nettyTransport) {
            martinetServer.nettyTransport = nettyTransport;
            return this;
        }

        public MartinetServerBuilder acceptorCount(int acceptorCount) {
            martinetServer.acceptorCount = acceptorCount;
            return this;
        }

        public MartinetServerBuilder port(int port) {
            martinetServer.port = port;
            return this;
        }

        public void start() {
            logger.info("Hi! Martinet start, the port is " + port + ", the acceptorCount is " + acceptorCount);
            nettyTransport.start();
        }
    }

    @Override
    protected void initInternal() {}

    @Override
    protected void startInternal() {
        nettyTransport.start();
    }

    public int getAcceptorCount() {
        return acceptorCount;
    }

    public void setAcceptorCount(int acceptorCount) {
        this.acceptorCount = acceptorCount;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
