package com.panchen.martinet.server.transport;

import com.panchen.martinet.common.handler.HandlerRegistry;
import com.panchen.martinet.common.io.TransportMeta;
import com.panchen.martinet.common.transport.TransportEvent;
import com.panchen.martinet.common.transport.TransportListener;

public class BusinessListener extends TransportListener {

    public BusinessListener(HandlerRegistry handlerRegistry) {
        super(handlerRegistry);
    }

    @Override
    public boolean fireAfterTransportEventInvoked(TransportEvent transportEvent) {
        handlerRegistry.getAllHandles().forEach(handler -> handler.handler((TransportMeta) transportEvent.getSource()));
        return true;
    }

    @Override
    public TransportMeta reply() {
        return null;
    }

}
