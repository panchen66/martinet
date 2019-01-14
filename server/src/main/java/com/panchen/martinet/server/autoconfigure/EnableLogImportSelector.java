package com.panchen.martinet.server.autoconfigure;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class EnableLogImportSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attributes = importingClassMetadata.getAllAnnotationAttributes(EnableLog.class.getName());
        // context.setLogFileUrl((String) attributes.get("logFileUrl").get(0));
        // Don't to ioc
        return new String[0];
    }

}
