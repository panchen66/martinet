package com.panchen.martinet.common.io;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class TransportMeta {

    private ByteBuf value;

    public TransportMeta(String value) {
        this.value = Unpooled.copiedBuffer(value.getBytes());
    }

    public ByteBuf value() {
        return value;
    }
}
