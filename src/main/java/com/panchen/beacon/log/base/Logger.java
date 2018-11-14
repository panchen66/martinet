package com.panchen.beacon.log.base;

import java.util.concurrent.CopyOnWriteArrayList;
import com.panchen.beacon.log.appender.Appender;

public class Logger {

    private final CopyOnWriteArrayList<Appender> appenders = new CopyOnWriteArrayList<>();

    private Context context = Context.getContext();

    private int levelValue;

    private Class c;

    public boolean isLogEnabled(LogLevel logLevel) {
        return LogLevel.ofValue(logLevel) > levelValue;
    }

    public void log(LogLevel logLevel, String message, Throwable t) {
        LogMetadata logMetadata = new LogMetadata(logLevel, message, t, c, context.getConverter());
        if (null != appenders) {
            appenders.forEach(appender -> {
                appender.publish(logMetadata);
            });
        }
    }

}
