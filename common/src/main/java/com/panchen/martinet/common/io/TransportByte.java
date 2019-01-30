package com.panchen.martinet.common.io;

public class TransportByte {

    private byte[] bytes;

    public TransportByte(byte[] bytes) {
        this.bytes = bytes;
    }

    public TransportByte(String message) {
        this.bytes = message.getBytes();
    }
}
