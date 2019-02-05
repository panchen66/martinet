package com.panchen.martinet.common.handler;

import java.io.File;
import java.util.Set;

public class HandlerManage {

    private static HandlerGroovyLoader handlerGroovyLoader;
    private boolean running;
    
    class HandlerFileManager {

        private int pollIntervalSeconds = 5;
        private String directory;
        private Thread poller;
        private Set<File> handlerFiles;

        public HandlerFileManager(int pollIntervalSeconds, String directory) {
            this.pollIntervalSeconds = pollIntervalSeconds;
        }


        public void start() {
            poller.setDaemon(Boolean.TRUE);
            poller = new Thread() {
                public void run() {
                    while (running) {
                        try {
                            loadGroovyHandler(directory);
                            sleep(pollIntervalSeconds * 1000);
                        } catch (Exception e) {
                        }
                    }
                }
            };
            poller.start();
        }

        private void loadGroovyHandler(String directory) {
            mangeFile(directory);
            loadHandler();
        }

        private void mangeFile(String directory) {
            File root = new File(directory);
            File[] files = root.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    mangeFile(file.getAbsolutePath());
                } else {
                    handlerFiles.add(file);
                }
            }
        }

        private void loadHandler() {
            handlerFiles.forEach(File -> {
                handlerGroovyLoader.load(File);
            });
        }
    }
}
