package com.panchen.martinet.client.autoconfigure;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;
import com.panchen.martinet.client.base.MartinetClient;

public class EnableMartinetClientImportSelector implements ImportSelector {

    private static final String MAXTHREADSIZE_KEY = "maxThreadSize";
    private static final String PORT_KEY = "port";
    private static final String SERVER_KEY = "server";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attributes =
                importingClassMetadata.getAllAnnotationAttributes(EnableMartinetClient.class.getName());
        // start Martinet client
        new MartinetClient().new MartinetClientBuilder().maxQueueLength((int) attributes.get(MAXTHREADSIZE_KEY).get(0))
                .port((int) attributes.get(PORT_KEY).get(0)).server((String) attributes.get(SERVER_KEY).get(0)).start();
        return new String[0];
    }

}
