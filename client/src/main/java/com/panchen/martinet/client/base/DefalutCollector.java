package com.panchen.martinet.client.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.panchen.martinet.client.transport.NettyClientTransprot;
import com.panchen.martinet.common.io.TransportByte;

public class DefalutCollector extends Collector {

    private TransportByte transportByte;
    private static MartinetClient martinetClient = MartinetClient.getApplicationContent();
    private NettyClientTransprot nettyClientTransprot;
    private BlockingQueue<TransportByte> queue;

    @Override
    public void collect(TransportByte transportByte) {
        queue.add(transportByte);
    }

    @Override
    public void flush() {
        transportByte = queue.poll();
        if (null != transportByte) {
            nettyClientTransprot.send(transportByte, nettyClientTransprot.getChannel());
        }
    }

    class flusher extends Thread {

        public void run() {
            while (Boolean.TRUE) {
                flush();
            }
        }
    }

    @Override
    protected void initInternal() {
        nettyClientTransprot = (NettyClientTransprot) martinetClient.getNettyTransport();
        queue = new ArrayBlockingQueue<TransportByte>(martinetClient.getMaxQueueLength());
    }

    @Override
    protected void startInternal() {
        flusher flusher = new flusher();
        flusher.start();
    }

}
