package com.panchen.martinet.common.handler;

import com.panchen.martinet.common.io.TransportMeta;

public abstract class MartinetHandler {

    private String name;

    public abstract void handler(TransportMeta transportMeta);


    public String getName() {
        return name;
    }

    public abstract int order();
}
