package com.panchen.collector.log.base;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.panchen.collector.log.convert.Converter;

public class Context {

    private static final Context singleton = new Context();

    private String logFileUrl;

    public boolean isFileAppender = true;

    public boolean isStreamAppender = true;

    // todo
    public static int MAX_THREAD;

    public List<File> logFiles;

    private static String COMMA = ",";

    private Converter converter;

    public static Context getContext() {
        return singleton;
    }

    private void getLogFiles() {
        String[] fileUrls = this.logFileUrl.split(COMMA);
        if (0 < fileUrls.length) {
            MAX_THREAD = fileUrls.length;
            Arrays.asList(fileUrls).stream().forEach(fileUrl -> this.logFiles.add(new File("fileUrl")));
        }
    }

    public Converter getConverter() {
        return singleton.converter;
    }

    public void setConverter(Converter converter) {
        singleton.converter = converter;
    }

    public String getLogFileUrl() {
        return logFileUrl;
    }

    public void setLogFileUrl(String logFileUrl) {
        this.logFileUrl = logFileUrl;
    }

    public boolean isFileAppender() {
        return isFileAppender;
    }

    public void setFileAppender(boolean isFileAppender) {
        this.isFileAppender = isFileAppender;
    }

    public boolean isStreamAppender() {
        return isStreamAppender;
    }

    public void setStreamAppender(boolean isStreamAppender) {
        this.isStreamAppender = isStreamAppender;
    }

}
