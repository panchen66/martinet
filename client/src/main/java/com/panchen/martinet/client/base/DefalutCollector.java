package com.panchen.martinet.client.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.panchen.martinet.common.io.LogByte;

public class DefalutCollector implements Collector {

    private BlockingQueue<LogByte> queue = new ArrayBlockingQueue<LogByte>(0);

    @Override
    public void collect(LogByte logByte) {
        queue.add(logByte);
    }

    @Override
    public void flush() {

    }

}
