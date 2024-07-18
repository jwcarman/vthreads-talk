package com.callibrity.vthreads.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loggers {

    @FunctionalInterface
    public interface Function {
        void log(String message, Object... args);
    }

    private Loggers() {

    }

    public static Function byName(Class<?> clazz, String name) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        return switch (name.toLowerCase()) {
            case "debug" -> logger::debug;
            case "error" -> logger::error;
            case "trace" -> logger::trace;
            case "warn" -> logger::warn;
            case "info" -> logger::info;
            default -> throw new IllegalArgumentException("Unknown log level: " + name);
        };
    }

}
