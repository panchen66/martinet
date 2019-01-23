package com.panchen.martinet.common.lifecycle;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * wait->init->Initialized->start->running
 * 
 * @author pc
 *
 */
public abstract class LifecycleBase implements Lifecycle {


    /**
     * the list of Observers
     */
    private final List<LifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<>();

    /**
     * default state
     */
    private volatile String state = Lifecycle.WAIT_STATE;


    public void registerLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.add(listener);
    }

    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.remove(listener);
    }


    public synchronized void init() {
        if (WAIT_STATE.equals(getStateName())) {
            switchState(INIT_EVENT, null);
            initInternal();
        } else {
            // todo
        }
    }

    protected abstract void initInternal();

    public synchronized void start() {
        if (WAIT_STATE.equals(getStateName())) {
            init();
        }
        if (INITIALIZED_STATE.equals(getStateName())) {
            switchState(START_EVENT, null);
            startInternal();
        } else {
            // todo
        }
    }

    protected abstract void startInternal();

    private void switchState(String eventType, Object data) {
        if (null != lifecycleListeners) {
            pushLifecycleEvent(eventType, data);
        }
    }

    protected void pushLifecycleEvent(String eventType, Object data) {
        LifecycleEvent event = new LifecycleEvent(this, eventType, data);
        lifecycleListeners.forEach(lifecycleListener -> {
            lifecycleListener.lifecycleEvent(event);
        });
    }

    public String getStateName() {
        return state;
    }
}
