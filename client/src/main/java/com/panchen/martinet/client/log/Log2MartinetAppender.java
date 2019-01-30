package com.panchen.martinet.client.log;

import java.io.Serializable;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.panchen.martinet.client.base.Collector;
import com.panchen.martinet.common.io.TransportByte;

@Component
@Plugin(name = Log2MartinetAppender.PLUGIN_NAME, category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE,
        printObject = true)
public class Log2MartinetAppender extends AbstractAppender {

    @Autowired
    Collector collector;

    public static final String PLUGIN_NAME = "Log2Martinet";

    protected Log2MartinetAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    @Override
    public void append(LogEvent event) {
        collector.collect(new TransportByte(event.getMessage().getFormattedMessage()));
    }

}
