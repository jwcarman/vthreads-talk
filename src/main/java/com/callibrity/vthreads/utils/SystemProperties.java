package com.callibrity.vthreads.utils;

import java.util.function.Function;

import static java.util.Optional.ofNullable;

public class SystemProperties {
    private SystemProperties() {

    }

    public static <T> T getProperty(String key, T defaultValue, Function<String, T> parser) {
        return ofNullable(System.getProperty(key))
                .map(parser)
                .orElse(defaultValue);
    }

    public static String stringProperty(String key, String defaultValue) {
        return getProperty(key, defaultValue, Function.identity());
    }

    public static int intProperty(String key, int defaultValue) {
        return getProperty(key, defaultValue, Integer::parseInt);
    }

    public static long longProperty(String key, long defaultValue) {
        return getProperty(key, defaultValue, Long::parseLong);
    }

}
