package com.panchen.martinet.common.transport;

public interface Transprot {

    boolean getStats();

    void send(Object message);
    
}
