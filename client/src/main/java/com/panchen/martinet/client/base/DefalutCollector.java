package com.panchen.martinet.client.base;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import com.panchen.martinet.client.transport.NettyClientTransprot;
import com.panchen.martinet.common.io.TransportMeta;

public class DefalutCollector extends Collector {

    private static MartinetClient martinetClient;
    private TransportMeta transportMeta;
    private NettyClientTransprot nettyClientTransprot;
    // avoid logging streams coming in first
    private BlockingQueue<TransportMeta> queue;
    private volatile boolean lock;

    @Override
    public void collect(TransportMeta transportByte) {
        try {
            queue.add(transportByte);
        } catch (Exception e) {
        }

    }

    @Override
    public void flush() {
        transportMeta = queue.poll();
        if (null != transportMeta) {
            nettyClientTransprot.send(nettyClientTransprot.getChannel(), transportMeta);
        }
    }

    class flusher extends Thread {

        public void run() {
            while (lock) {
                flush();
            }
        }
    }

    @Override
    protected void initInternal() {
        martinetClient = MartinetClient.getApplicationContent();
        nettyClientTransprot = (NettyClientTransprot) martinetClient.getNettyTransport();
        queue = new LinkedBlockingQueue<TransportMeta>(martinetClient.getMaxQueueLength());
    }

    @Override
    protected void startInternal() {
        flusher flusher = new flusher();
        flusher.start();
        lock = true;
    }

    public boolean isLock() {
        return lock;
    }

}
