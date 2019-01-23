package com.panchen.martinet.client.base;

import com.panchen.martinet.common.io.LogByte;

public interface Collector {

    void collect(LogByte logByte);

    void flush();

}
