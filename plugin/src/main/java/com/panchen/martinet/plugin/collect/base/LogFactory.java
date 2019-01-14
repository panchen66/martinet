package com.panchen.collector.log.base;

public class LogFactory {

    private static final LogFactory singleton = new LogFactory();


    public static LogFactory getFactory() {
        return singleton;
    }

    public Log getLog(Class<?> clazz) {
        return getLog(clazz.getName());
    }

    public Log getLog(String name) {
        return DefaultLog.getInstance(name);
    }


}
