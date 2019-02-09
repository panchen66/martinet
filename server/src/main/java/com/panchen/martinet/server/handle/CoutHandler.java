package com.panchen.martinet.server.handle;

import com.panchen.martinet.common.handler.MartinetHandler;
import com.panchen.martinet.common.transport.TransportEvent;
import com.panchen.martinet.server.env.NodeEnvironment;

public class CoutHandler extends MartinetHandler {


    public void handler(TransportEvent transportEvent) {
        NodeEnvironment.TOTALACCEPTANCE.increment();;
    }

    @Override
    public int order() {
        return 1;
    }


}

