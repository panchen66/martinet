package com.panchen.martinet.server.rest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.panchen.martinet.server.env.NodeEnvironment;

@RestController
public class AdminApi {

    @GetMapping(value = "/count")
    public String count() {
        return "The total amount of treated log is " + NodeEnvironment.TOTALACCEPTANCE.longValue();
    }

    @GetMapping(value = "/dimension/{name}")
    public String dimension(@PathVariable String name) {
        ConcurrentHashMap<String, LongAdder> value = NodeEnvironment.MULTIDIMENSIONALSTATISTICS.get(name);
        if (null == value) {
            return "The dimension of the name could not be found";
        }
        return JSON.toJSONString(value);
    }
}

