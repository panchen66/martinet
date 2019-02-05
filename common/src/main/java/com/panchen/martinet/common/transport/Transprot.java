package com.panchen.martinet.common.transport;

import com.panchen.martinet.common.io.TransportMeta;

public interface Transprot {

    boolean getStats();

    void send(Object channel, TransportMeta transportMeta);

}
