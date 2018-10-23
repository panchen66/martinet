package com.panchen.beacon.log.base;

public enum LogLevel {

    DEBUG("DEBUG", 1),

    INFO("INFO", 2),

    WARN("WARN", 3),

    ERROR("ERROR", 4);

    private final String name;

    private final int value;

    LogLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static int ofValue(LogLevel level) {
        return level.getValue();
    }

    public int getValue() {
        return value;
    }


}
