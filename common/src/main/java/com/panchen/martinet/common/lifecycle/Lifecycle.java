package com.panchen.martinet.common.lifecycle;

/**
 * consult tomcat's lifecycle
 *  
 * @author pc
 *
 */
public interface Lifecycle {

    public static final String WAIT_STATE = "wait";

    public static final String INITIALIZED_STATE = "Initialized";

    public static final String RUNNING_STATE = "running";

    public static final String INIT_EVENT = "init";

    public static final String START_EVENT = "start";

    void registerLifecycleListener(LifecycleListener listener);

    void removeLifecycleListener(LifecycleListener listener);

    void init();

    void start();

    String getStateName();


}
