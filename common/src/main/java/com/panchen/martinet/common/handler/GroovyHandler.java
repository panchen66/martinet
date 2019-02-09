package com.panchen.martinet.common.handler;

import com.panchen.martinet.common.io.TransportMeta;
import com.panchen.martinet.common.transport.TransportEvent;

import groovy.lang.GroovyObject;

public class GroovyHandler extends MartinetHandler {

    private static GroovyObject groovyObject;
    private final static String HANDLER_METHODNAME = "handler";
    private final static String ORDER_METHODNAME = "order";


    @Override
    public void handler(TransportEvent transportEvent) {
        groovyObject.invokeMethod(HANDLER_METHODNAME, transportEvent);
    }

    public GroovyHandler init(GroovyObject groovyObject) {
        GroovyHandler.groovyObject = groovyObject;
        return this;
    }

    @Override
    public int order() {
        return (int) groovyObject.invokeMethod(ORDER_METHODNAME, null);
    }


}
