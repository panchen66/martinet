package com.panchen.martinet.plugin.collect.base;

import com.panchen.martinet.plugin.collect.transport.NettyClientTransprot;
import com.panchen.martinet.plugin.collect.transport.Transprot;

public class ApplicationContent {

    private static final ApplicationContent singleton = new ApplicationContent();

    private String collectDir;

    // default transprot
    private Transprot transprot = new NettyClientTransprot();

    private String applicationName;

    private String version;

    private String serverHost;

    public static ApplicationContent getApplicationContent() {
        return singleton;
    }

    public static class Builder {

        private ApplicationContent applicationContent = getApplicationContent();

        private Builder collectDir(String collectDir) {
            applicationContent.collectDir = collectDir;
            return this;
        }

        private Builder transprot(Transprot transprot) {
            applicationContent.transprot = transprot;
            return this;
        }

        private Builder applicationName(String applicationName) {
            applicationContent.applicationName = applicationName;
            return this;
        }

        private Builder version(String version) {
            applicationContent.version = version;
            return this;
        }

        private Builder serverHost(String serverHost) {
            applicationContent.serverHost = serverHost;
            return this;
        }
    }

}
