package com.panchen.martinet.client.base;

import com.panchen.martinet.common.transport.NettyClientTransprot;
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

}
