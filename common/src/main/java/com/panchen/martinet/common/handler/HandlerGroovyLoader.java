package com.panchen.martinet.common.handler;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class HandlerGroovyLoader {

    private static GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
    private static HandlerRegistry handlerRegistry = new HandlerRegistry();

    public void load(File handlerFile) {
        String key = handlerFile.getName();
        cached(key);
        Class<?> c = null;
        try {
            c = groovyClassLoader.parseClass(handlerFile);
        } catch (CompilationFailedException e) {
        } catch (IOException e) {
        }
        GroovyObject groovyObject = null;
        try {
            groovyObject = (GroovyObject) c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
        }
        cache(groovyObject);
    }


    private void cached(String key) {
        if (null != handlerRegistry.get(key)) {
            // throw todo
        }
    }

    private void cache(GroovyObject groovyObject) {
        check(groovyObject);
        GroovyHandler groovyHandler = new GroovyHandler();
        groovyHandler.init(groovyObject);
        handlerRegistry.put(groovyHandler);
    }

    // todo
    private void check(GroovyObject groovyObject) {}

}
