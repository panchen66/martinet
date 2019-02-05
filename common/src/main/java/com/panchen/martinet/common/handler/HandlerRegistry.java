package com.panchen.martinet.common.handler;

import java.util.Collection;
import java.util.TreeMap;

public class HandlerRegistry {

    private final TreeMap<Integer, MartinetHandler> handlers = new TreeMap<Integer, MartinetHandler>();

    public HandlerRegistry() {}

    public void remove(String name) {
        handlers.values().forEach(handler -> {
            if (name.equals(handler.getName())) {
                handlers.remove(handler.order(), handler);
            }
        });
    }

    public MartinetHandler get(int key) {
        return this.handlers.get(key);
    }


    public MartinetHandler get(String name) {
        for (MartinetHandler handler : handlers.values()) {
            if (name.equals(handler.getName())) {
                return handler;
            }
        }
        return null;
    }

    public void put(MartinetHandler handler) {
        this.handlers.put(handler.order(), handler);
    }

    public int size() {
        return this.handlers.size();
    }

    public Collection<MartinetHandler> getAllHandles() {
        return this.handlers.values();
    }

}
