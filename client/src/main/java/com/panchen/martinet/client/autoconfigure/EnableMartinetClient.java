package com.panchen.martinet.client.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(EnableMartinetClientImportSelector.class)
public @interface EnableMartinetClient {

    int port() default 1517;

    int maxThreadSize() default 2;

    String server() default "127.0.0.1:1901";

}
