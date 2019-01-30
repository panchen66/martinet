package com.panchen.martinet.client.base;

import com.panchen.martinet.client.transport.NettyClientTransprot;
import com.panchen.martinet.common.transport.Transprot;

public class Martinet {

    private static final Martinet singleton = new Martinet();

    // default transprot
    private Transprot transprot = new NettyClientTransprot();

    private String server;

    private int maxQueueLength = 1024;

    private int maxThreadSize = 2;

    public static Martinet getApplicationContent() {
        return singleton;
    }

    public static class Builder {

        private Martinet martinet = getApplicationContent();

        private Builder maxQueueLength(int maxQueueLength) {
            martinet.maxQueueLength = maxQueueLength;
            return this;
        }

        private Builder transprot(Transprot transprot) {
            martinet.transprot = transprot;
            return this;
        }

        private Builder maxThreadSize(int maxThreadSize) {
            martinet.maxThreadSize = maxThreadSize;
            return this;
        }

        private Builder server(String server) {
            martinet.server = server;
            return this;
        }
    }

    public Transprot getTransprot() {
        return transprot;
    }

    public void setTransprot(Transprot transprot) {
        this.transprot = transprot;
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
    
}
