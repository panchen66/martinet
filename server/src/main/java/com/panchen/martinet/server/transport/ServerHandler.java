package com.panchen.martinet.server.transport;

import java.util.List;

import com.panchen.martinet.common.transport.TransportHandler;
import com.panchen.martinet.common.transport.TransportListener;

public class ServerHandler extends TransportHandler {

    protected ServerHandler(List<TransportListener> listeners) {
        super(listeners);
    }

}
