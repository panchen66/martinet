package com.panchen.martinet.client.log;

import java.io.Serializable;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import com.panchen.martinet.client.base.Collector;
import com.panchen.martinet.client.base.MartinetClient;
import com.panchen.martinet.common.io.TransportMeta;

@Plugin(name = Log2MartinetAppender.PLUGIN_NAME, category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE,
        printObject = true)
public class Log2MartinetAppender extends AbstractAppender {

    private Collector collector = MartinetClient.getApplicationContent().getCollector();

    public static final String PLUGIN_NAME = "Log2Martinet";

    protected Log2MartinetAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    @Override
    public void append(LogEvent event) {
        if (null != collector && collector.isLock()) {
            StringBuilder sb = new StringBuilder();
            sb.append(event.getLevel().toString());
            sb.append("/");
            sb.append(event.getLoggerName());
            sb.append("/");
            sb.append(event.getMessage().getFormattedMessage());
            collector.collect(new TransportMeta(sb.toString()));
        }
    }

    @PluginFactory
    public static Log2MartinetAppender createAppender(@PluginAttribute("name") String name,
            @PluginElement("Filter") final Filter filter, @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginAttribute("ignoreExceptions") boolean ignoreExceptions) {
        if (name == null) {
            LOGGER.error("no name defined in conf.");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new Log2MartinetAppender(name, filter, layout);
    }
}
