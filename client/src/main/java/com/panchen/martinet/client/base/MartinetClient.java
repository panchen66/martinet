package com.panchen.martinet.client.base;

import com.panchen.martinet.client.transport.NettyClientTransprot;
import com.panchen.martinet.common.lifecycle.LifecycleBase;
import com.panchen.martinet.common.transport.NettyTransport;

public class MartinetClient extends LifecycleBase {

    private static MartinetClient singleton = new MartinetClient();

    // default transprot
    private NettyTransport nettyTransport = new NettyClientTransprot();
    private String server;
    private int maxQueueLength = 1024;
    private int maxThreadSize = 2;
    private Collector collector = new DefalutCollector();
    private int port = 1517;

    public static MartinetClient getApplicationContent() {
        return singleton;
    }

    public class MartinetClientBuilder {

        private MartinetClient martinetClient;

        public MartinetClientBuilder() {
            martinetClient = getApplicationContent();
        }

        public MartinetClientBuilder maxQueueLength(int maxQueueLength) {
            martinetClient.maxQueueLength = maxQueueLength;
            return this;
        }

        public MartinetClientBuilder transprot(NettyTransport nettyTransport) {
            martinetClient.nettyTransport = nettyTransport;
            return this;
        }

        public MartinetClientBuilder maxThreadSize(int maxThreadSize) {
            martinetClient.maxThreadSize = maxThreadSize;
            return this;
        }

        public MartinetClientBuilder server(String server) {
            martinetClient.server = server;
            return this;
        }

        public MartinetClientBuilder collector(Collector collector) {
            martinetClient.collector = collector;
            return this;
        }

        public MartinetClientBuilder port(int port) {
            martinetClient.port = port;
            return this;
        }

        public void start() {
            martinetClient.nettyTransport.start();
            martinetClient.collector.start();
        }
    }

    public NettyTransport getNettyTransport() {
        return nettyTransport;
    }

    public void setNettyTransport(NettyTransport nettyTransport) {
        this.nettyTransport = nettyTransport;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getMaxQueueLength() {
        return maxQueueLength;
    }

    public void setMaxQueueLength(int maxQueueLength) {
        this.maxQueueLength = maxQueueLength;
    }

    public int getMaxThreadSize() {
        return maxThreadSize;
    }

    public void setMaxThreadSize(int maxThreadSize) {
        this.maxThreadSize = maxThreadSize;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    protected void initInternal() {}

    @Override
    protected void startInternal() {
        collector.start();
        nettyTransport.start();
    }

}
