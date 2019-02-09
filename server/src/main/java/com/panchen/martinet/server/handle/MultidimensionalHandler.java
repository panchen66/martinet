package com.panchen.martinet.server.handle;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import org.springframework.util.StringUtils;
import com.panchen.martinet.common.handler.MartinetHandler;
import com.panchen.martinet.common.transport.TransportEvent;
import com.panchen.martinet.server.env.NodeEnvironment;

/**
 * todo: Multidimensional Classified Log
 * 
 * 
 * @author pc
 *
 */
public class MultidimensionalHandler extends MartinetHandler {



    @Override
    public void handler(TransportEvent transportEvent) {
        if (!StringUtils.isEmpty(transportEvent.stringValue())) {
            analysis(transportEvent.stringValue());
        }
    }

    @Override
    public int order() {
        return 2;
    }

    private void analysis(String value) {
        System.out.println("TT:" + value);
        String[] values = value.split("/");
        if (null != values && 3 == values.length) {
            // log level
            checkAndPut(values[0], NodeEnvironment.LOGLEVEL);
            // log className
            checkAndPut(values[0], NodeEnvironment.CLASSNAME);
        }
    }

    private void checkAndPut(String value, String key) {
        if (null != value) {
            LongAdder count;
            ConcurrentHashMap<String, LongAdder> env = NodeEnvironment.MULTIDIMENSIONALSTATISTICS.get(key);
            count = env.get(value);
            if (null != count) {
                count.increment();
            } else {
                env.put(value, new LongAdder());
            }
        }
    }
}
