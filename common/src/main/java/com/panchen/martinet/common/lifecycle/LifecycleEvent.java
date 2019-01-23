package com.panchen.martinet.common.lifecycle;

import java.util.EventObject;

public final class LifecycleEvent extends EventObject {


    private static final long serialVersionUID = 1L;


    private final Object data;

    private final String eventType;

    public LifecycleEvent(Lifecycle lifecycle, String eventType, Object data) {
        super(lifecycle);
        this.eventType = eventType;
        this.data = data;
    }

}
