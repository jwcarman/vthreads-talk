package com.callibrity.vthreads.utils;

import java.util.concurrent.Semaphore;
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

    public static Runnable withSemaphore(Semaphore semaphore, Runnable inner) {
        return () -> {
            semaphore.acquireUninterruptibly();
            try {
                inner.run();
            } finally {
                semaphore.release();
            }
        };
    }

    public static Runnable withLock(Lock lock, Runnable inner) {
        return () -> {
            lock.lock();
            try {
                inner.run();
            } finally {
                lock.unlock();
            }
        };
    }

    public static Runnable withLock(Runnable inner) {
        return withLock(new ReentrantLock(), inner);
    }

    public static Runnable withMonitor(final Object monitor, Runnable inner) {
        return () -> {
            synchronized (monitor) {
                inner.run();
            }
        };
    }

    public static Runnable withMonitor(Runnable inner) {
        return withMonitor(new Object(), inner);
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
