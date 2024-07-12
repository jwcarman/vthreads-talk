package com.callibrity.vthreads.utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    public static Runnable locked(Runnable inner) {
        final Lock lock = new ReentrantLock();
        return () -> {
            lock.lock();
            try {
                inner.run();
            } finally {
                lock.unlock();
            }
        };
    }

    public static Runnable monitored(Runnable inner) {
        final Object monitor = new Object();
        return () -> {
            synchronized (monitor) {
                inner.run();
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

    public static Runnable postDelay(int millis, Runnable inner) {
        return () -> {
            inner.run();
            Sleeps.sleepMillis(millis);
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
