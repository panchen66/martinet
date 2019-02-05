package com.panchen.martinet.server.handle;

import java.util.concurrent.CountDownLatch;

import com.panchen.martinet.common.handler.MartinetHandler;
import com.panchen.martinet.common.io.TransportMeta;

public class CoutHandler extends MartinetHandler {

    CountDownLatch count = new CountDownLatch(0);

    public void handler(TransportMeta transportMeta) {
        count.countDown();
        System.out.println(count.getCount());
    }

    @Override
    public int order() {
        return 1;
    }


}

