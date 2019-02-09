package com.panchen.martinet.common.handler;

import com.panchen.martinet.common.transport.TransportEvent;

public abstract class MartinetHandler {

    private String name;

    public abstract void handler(TransportEvent transportEvent);


    public String getName() {
        return name;
    }

    public abstract int order();
}
