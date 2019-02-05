package com.panchen.martinet.client.base;

import com.panchen.martinet.common.io.TransportMeta;
import com.panchen.martinet.common.lifecycle.LifecycleBase;

public abstract class Collector extends LifecycleBase {

    public abstract void collect(TransportMeta transportMeta);

    public abstract boolean isLock();

    abstract void flush();

}
