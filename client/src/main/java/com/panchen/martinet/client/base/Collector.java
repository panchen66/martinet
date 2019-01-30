package com.panchen.martinet.client.base;

import com.panchen.martinet.common.io.TransportByte;

public interface Collector {

    void collect(TransportByte transportByte);

    void flush();

}
