package com.panchen.martinet.client.autoconfigure;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;
import com.panchen.martinet.client.base.MartinetClient;

public class EnableMartinetClientImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attributes =
                importingClassMetadata.getAllAnnotationAttributes(EnableMartinetServer.class.getName());
        // start Martinet client
        new MartinetClient().new MartinetClientBuilder().maxQueueLength((int) attributes.get("maxThreadSize").get(0))
                .port((int) attributes.get("port").get(0)).server((String) attributes.get("server").get(0)).start();
        return new String[0];
    }

}
