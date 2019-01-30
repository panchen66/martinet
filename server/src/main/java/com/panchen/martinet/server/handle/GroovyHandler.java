package com.panchen.martinet.server.handle;

import com.panchen.martinet.common.io.TransportByte;

import groovy.lang.GroovyObject;

public class GroovyHandler extends MartinetHandler {

    private static GroovyObject groovyObject;
    private final static String HANDLER_METHODNAME = "handler";
    private final static String ORDER_METHODNAME = "order";


    @Override
    void handler(TransportByte transportByte) {
        groovyObject.invokeMethod(HANDLER_METHODNAME, transportByte);
    }

    public GroovyHandler init(GroovyObject groovyObject) {
        GroovyHandler.groovyObject = groovyObject;
        return this;
    }

    @Override
    void setOrder() {
        this.order = (int) groovyObject.invokeMethod(ORDER_METHODNAME, null);
    }

}
