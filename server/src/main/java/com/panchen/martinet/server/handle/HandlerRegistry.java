package com.panchen.martinet.server.handle;

import java.util.Collection;
import java.util.TreeMap;

public class HandlerRegistry {

    private static final HandlerRegistry INSTANCE = new HandlerRegistry();

    public static final HandlerRegistry instance() {
        return INSTANCE;
    }

    private final TreeMap<Integer, MartinetHandler> handlers = new TreeMap<Integer, MartinetHandler>();

    private HandlerRegistry() {}

    public void remove(String name) {
        handlers.values().forEach(handler -> {
            if (name.equals(handler.getName())) {
                handlers.remove(handler.getOrder(), handler);
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
        this.handlers.put(handler.getOrder(), handler);
    }

    public int size() {
        return this.handlers.size();
    }

    public Collection<MartinetHandler> getAllFilters() {
        return this.handlers.values();
    }

}
