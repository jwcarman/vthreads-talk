package com.callibrity.vthreads.utils;

import java.util.HashSet;
import java.util.Set;

public class ThreadSpawner implements AutoCloseable {

    private final Thread.Builder threadBuilder;
    private final Set<Thread> threads = new HashSet<>();

    public static ThreadSpawner ofVirtual() {
        return new ThreadSpawner(Thread.ofVirtual());
    }

    private ThreadSpawner(Thread.Builder threadBuilder) {
        this.threadBuilder = threadBuilder;
    }

    public void spawn(String name, Runnable runnable) {
        threads.add(threadBuilder.name(name).start(runnable));
    }

    public void close() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        }
    }
}
