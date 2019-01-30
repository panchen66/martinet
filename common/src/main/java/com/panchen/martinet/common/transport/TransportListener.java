package com.panchen.martinet.common.transport;

import java.util.EventListener;

public interface TransportListener extends EventListener {

    boolean fireAfterTransportEventInvoked(TransportEvent transportEvent);

    Object reply();
}
