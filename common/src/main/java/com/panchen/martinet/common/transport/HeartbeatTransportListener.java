package com.panchen.martinet.common.transport;

public class HeartbeatTransportListener implements TransportListener {

    protected static final String PING_MESSAGE = "MARTINET";

    @Override
    public boolean fireAfterTransportEventInvoked(TransportEvent transportEvent) {
        return false;
    }

    @Override
    public Object reply() {
        return PING_MESSAGE;
    }


}
