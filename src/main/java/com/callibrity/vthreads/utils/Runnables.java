package com.callibrity.vthreads.utils;

import java.util.function.IntConsumer;

public class Runnables {
    private Runnables() {
        // Prevent instantiation!
    }

    public static Runnable forever(Runnable inner) {
        return () -> {
            while (true) {
                inner.run();
            }
        };
    }

    public static Runnable repeatWithIteration(int n, IntConsumer inner) {
        return () -> {
            for (int iteration = 0; iteration < n; ++iteration) {
                inner.accept(iteration);
            }
        };
    }

    public static Runnable repeat(int n, Runnable inner) {
        return () -> {
            for (int iteration = 0; iteration < n; ++iteration) {
                inner.run();
            }
        };
    }

    public static Runnable serial(Runnable... runnables) {
        return () -> {
            for (Runnable runnable : runnables) {
                runnable.run();
            }
        };
    }

    public static Runnable noop() {
        return () -> {
            // Do nothing!
        };
    }


}
