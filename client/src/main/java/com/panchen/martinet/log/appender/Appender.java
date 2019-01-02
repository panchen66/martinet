package com.panchen.collector.log.appender;

import com.panchen.collector.log.base.LogMetadata;

public interface Appender {

    public void publish(LogMetadata logMetadata);

    public void flush();

}
