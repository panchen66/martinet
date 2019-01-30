package com.panchen.martinet.client.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.panchen.martinet.client.transport.NettyClientTransprot;
import com.panchen.martinet.common.io.TransportByte;

public class DefalutCollector implements Collector {

    private TransportByte transportByte;
    private Martinet martinet = Martinet.getApplicationContent();
    private NettyClientTransprot nettyClientTransprot = (NettyClientTransprot) martinet.getTransprot();
    private BlockingQueue<TransportByte> queue = new ArrayBlockingQueue<TransportByte>(martinet.getMaxQueueLength());

    @Override
    public void collect(TransportByte transportByte) {
        queue.add(transportByte);
    }

    @Override
    public void flush() {
        transportByte = queue.poll();
        if (null != transportByte) {
            nettyClientTransprot.send(transportByte);
        }
    }

    class flusher extends Thread {

        public void run() {
            while (Boolean.TRUE) {
                flush();
            }
        }
    }

}
