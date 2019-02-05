package com.panchen.martinet.common.transport;

import com.panchen.martinet.common.handler.HandlerRegistry;
import com.panchen.martinet.common.io.TransportMeta;

public class HeartbeatTransportListener extends TransportListener {

    public HeartbeatTransportListener(HandlerRegistry handlerRegistry) {
        super(handlerRegistry);
    }

    protected static final String PING_MESSAGE = "MARTINET";

    @Override
    public boolean fireAfterTransportEventInvoked(TransportEvent transportEvent) {
        return true;
    }

    @Override
    public TransportMeta reply() {
        return new TransportMeta(PING_MESSAGE);
    }


}
