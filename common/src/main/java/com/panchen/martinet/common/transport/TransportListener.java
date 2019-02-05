package com.panchen.martinet.common.transport;

import java.util.EventListener;

import com.panchen.martinet.common.handler.HandlerRegistry;
import com.panchen.martinet.common.io.TransportMeta;

/**
 * 
 * There are different listeners for different types of messages ,and the interface is not open to
 * the outside world.
 * 
 * @author pc
 *
 */
public abstract class TransportListener implements EventListener {

    public static HandlerRegistry handlerRegistry;

    public TransportListener(HandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    public abstract boolean fireAfterTransportEventInvoked(TransportEvent transportEvent);

    public abstract TransportMeta reply();
}
