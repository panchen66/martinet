package com.panchen.beacon.log.base;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Handler;

public class Logger {

    private Context Context;

    private final CopyOnWriteArrayList<Handler> handlers = new CopyOnWriteArrayList<>();

    private int levelValue;

    public boolean isLogEnabled(LogLevel logLevel) {
        return LogLevel.ofValue(logLevel) > levelValue;
    }

    public void log(LogLevel logLevel, String message, Throwable t) {

    }
}
