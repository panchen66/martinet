package com.panchen.martinet.server.env;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Storing Node Information
 * 
 * @author pc
 *
 */
public class NodeEnvironment {

    public static final String LOGLEVEL = "LOGLEVEL";
    public static final String CLASSNAME = "CLASSNAME";
    
    // Total acceptance
    public static LongAdder TOTALACCEPTANCE = new LongAdder();

    // Multidimensional statistics
    public static Map<String, ConcurrentHashMap<String, LongAdder>> MULTIDIMENSIONALSTATISTICS = new ConcurrentHashMap<>();

    static {
        MULTIDIMENSIONALSTATISTICS.put(LOGLEVEL, new ConcurrentHashMap<>());
        MULTIDIMENSIONALSTATISTICS.put(CLASSNAME, new ConcurrentHashMap<>());
    }

}
