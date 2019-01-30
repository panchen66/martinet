package com.panchen.martinet.server.handle;

import com.panchen.martinet.common.io.TransportByte;

public abstract class MartinetHandler {

    private String name;
    public int order;

    abstract void handler(TransportByte transportByte);

    abstract void setOrder();

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
}
