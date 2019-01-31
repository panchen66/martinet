package com.panchen.martinet.server.autoconfigure;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;
import com.panchen.martinet.server.bootstrap.MartinetServer;

public class EnableMartinetServerImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attributes =
                importingClassMetadata.getAllAnnotationAttributes(EnableMartinetServer.class.getName());
        // start Martinet
        new MartinetServer().new MartinetServerBuilder().acceptorCount((int) attributes.get("acceptorCount").get(0))
                .port((int) attributes.get("port").get(0)).start();
        return new String[0];
    }

}
