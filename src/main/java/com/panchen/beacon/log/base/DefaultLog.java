package com.panchen.beacon.log.base;

public class DefaultLog implements Log {

    private Logger logger;

    public DefaultLog(String name) {}

    public static Log getInstance(String name) {
        return new DefaultLog(name);
    }

    public boolean isDebugEnabled() {
        return logger.isLogEnabled(LogLevel.DEBUG);
    }

    public boolean isInfoEnabled() {
        return logger.isLogEnabled(LogLevel.INFO);
    }

    public boolean isWarnEnabled() {
        return logger.isLogEnabled(LogLevel.WARN);
    }

    public boolean isErrorEnabled() {
        return logger.isLogEnabled(LogLevel.ERROR);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message, null);

    }

    public void debug(String message, Throwable t) {
        log(LogLevel.DEBUG, message, t);

    }

    public void info(String message) {
        log(LogLevel.INFO, message, null);
    }

    public void info(String message, Throwable t) {
        log(LogLevel.INFO, message, t);

    }

    public void warn(String message) {
        log(LogLevel.WARN, message, null);

    }

    public void warn(String message, Throwable t) {
        log(LogLevel.WARN, message, t);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message, null);
    }

    public void error(String message, Throwable t) {
        log(LogLevel.ERROR, message, t);
    }

    private void log(LogLevel logLevel, String message, Throwable t) {
        if (logger.isLogEnabled(logLevel)) {
            logger.log(logLevel, message, t);
        }
    }

}
