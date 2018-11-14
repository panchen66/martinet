package com.panchen.beacon.log.appender;

import com.panchen.beacon.log.base.LogMetadata;

public interface Appender {

    public void publish(LogMetadata logMetadata);

    public void flush();

}
