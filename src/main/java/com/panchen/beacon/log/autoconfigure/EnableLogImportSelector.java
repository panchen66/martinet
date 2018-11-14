package com.panchen.beacon.log.autoconfigure;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;
import com.panchen.beacon.log.base.Context;

public class EnableLogImportSelector implements ImportSelector {

    private Context context = Context.getContext();

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attributes = importingClassMetadata.getAllAnnotationAttributes(EnableLog.class.getName());
        context.setLogFileUrl((String) attributes.get("logFileUrl").get(0));
        context.setStreamAppender((boolean) attributes.get("streamAppender").get(0));
        context.setFileAppender((boolean) attributes.get("fileAppender").get(0));
        // Don't to ioc
        return new String[0];
    }

}
