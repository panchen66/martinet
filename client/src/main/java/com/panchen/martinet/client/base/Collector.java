package com.panchen.martinet.client.base;

import com.panchen.martinet.common.io.TransportByte;
import com.panchen.martinet.common.lifecycle.LifecycleBase;

public abstract class Collector extends LifecycleBase{

    public abstract void collect(TransportByte transportByte);

    abstract void flush();

}
