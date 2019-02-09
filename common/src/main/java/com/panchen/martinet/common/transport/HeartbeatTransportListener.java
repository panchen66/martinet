package com.panchen.martinet.common.transport;

import com.panchen.martinet.common.handler.HandlerRegistry;

public class HeartbeatTransportListener extends TransportListener {

    public HeartbeatTransportListener(HandlerRegistry handlerRegistry) {
        super(handlerRegistry);
    }

    private TransportEvent transportEvent;
    protected static final String PING_MESSAGE = "MARTINET";

    @Override
    public boolean fireAfterTransportEventInvoked(TransportEvent transportEvent) {
        if (PING_MESSAGE.equals(transportEvent.value)) {
            this.transportEvent = transportEvent;
            return true;
        }
        return false;
    }

    @Override
    public void reply() {
        transportEvent.getCtx().channel().writeAndFlush(transportEvent.getSource());
    }


}
