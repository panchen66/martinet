package com.panchen.collector.log.appender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.panchen.collector.log.base.Context;
import com.panchen.collector.log.base.LogMetadata;

public class FileAppender implements AsyncAppender {

    private Context context = Context.getContext();

    private static Map<File, ReentrantReadWriteLock> locks = new ConcurrentHashMap<File, ReentrantReadWriteLock>();

    private static Map<File, PrintWriter> writers = new ConcurrentHashMap<File, PrintWriter>();

    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(Context.MAX_THREAD);

    @Override
    public void flush() {
        context.logFiles.forEach(file -> {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    ReentrantReadWriteLock lock = locks.get(file);
                    try {
                        lock.writeLock().lock();
                        writers.get(file).flush();
                    } finally {
                        lock.writeLock().unlock();
                    }
                }
            });
        });
    }

    @Override
    public void publish(LogMetadata logMetadata) {
        context.logFiles.forEach(file -> {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    ReentrantReadWriteLock lock = locks.get(file);
                    try {
                        lock.readLock().lock();
                        try {
                            lock.readLock().unlock();
                            lock.writeLock().lock();
                            writers.get(file).print(logMetadata.toString());
                        } finally {
                            lock.writeLock().unlock();
                            lock.readLock().lock();
                        }
                    } finally {
                        lock.readLock().unlock();
                    }
                }
            });
        });
    }



    @Override
    public void init() {
        context.logFiles.forEach(file -> {
            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            locks.put(file, lock);
            lock.writeLock().lock();
            try {
                try {
                    writers.put(file, new PrintWriter(file));
                } catch (FileNotFoundException e) {
                }
            } finally {
                lock.writeLock().unlock();
            }
        });
    }

    
    public FileAppender() {
        init();
    }

}


