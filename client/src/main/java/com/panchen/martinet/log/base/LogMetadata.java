package com.panchen.collector.log.base;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.panchen.collector.log.convert.Converter;


/**
 * logMesage:
 * 
 * INFO_seq_xxx.class_yyyy:MM:dd HH:mm:ss : /thread message throwable/
 * 
 * @author PC
 *
 */
public class LogMetadata {

    private AtomicLong seqNum = new AtomicLong();

    private DateFormat dateFormat = DateFormat.getDateTimeInstance();

    private static String UNDERLINE = "_";

    private static String ASSOCIATEDCHARACTER = " : ";

    private static String SEPARATOR = "/";

    private static String CHANGELINE = "\\r\\n";

    // class info + time
    private String logHead;

    // thread + Throwable + message
    private String logContent;

    public LogMetadata(LogLevel logLevel, String message, Throwable t, Class<?> c) {
        handleHead(logLevel, c);
        handleContent(message, t);
    }

    public LogMetadata(LogLevel logLevel, String message, Throwable t, Class<?> c, Converter converter) {
        converter.convert(new LogMetadata(logLevel, message, t, c));
    }

    private void handleHead(LogLevel logLevel, Class<?> c) {
        StringBuffer sb = new StringBuffer(logLevel.getName());
        sb.append(UNDERLINE);
        sb.append(seqNum.incrementAndGet());
        sb.append(UNDERLINE);
        sb.append(c.getName());
        sb.append(UNDERLINE);
        sb.append(dateFormat.format(new Date()));
        sb.append(ASSOCIATEDCHARACTER);
        this.logHead = sb.toString();
    }

    private void handleContent(String message, Throwable t) {
        StringBuffer sb = new StringBuffer(SEPARATOR);
        sb.append(CHANGELINE);
        sb.append(message);
        sb.append(CHANGELINE);
        sb.append(Thread.currentThread().toString());
        if (null != t) {
            sb.append(CHANGELINE);
            sb.append(t.getMessage());
        }
        sb.append(SEPARATOR);
        this.logContent = sb.toString();
    }


    public String toString() {
        return this.logHead + this.logContent;
    }
}
