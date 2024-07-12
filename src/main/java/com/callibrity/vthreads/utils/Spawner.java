package com.callibrity.vthreads.utils;

import java.util.HashSet;
import java.util.Set;

public class Spawner implements AutoCloseable {

    private final Thread.Builder threadBuilder;
    private final Set<Thread> threads = new HashSet<>();

    public static Spawner ofVirtual() {
        return new Spawner(Thread.ofVirtual());
    }

    public static Spawner ofVirtual(String prefix) {
        return new Spawner(Thread.ofVirtual().name(prefix, 0));
    }

    public static Spawner ofPlatform() {
        return new Spawner(Thread.ofPlatform());
    }

    public static Spawner ofPlatform(String prefix) {
        return new Spawner(Thread.ofPlatform().name(prefix, 0));
    }

    private Spawner(Thread.Builder threadBuilder) {
        this.threadBuilder = threadBuilder;
    }

    public void spawn(Runnable runnable) {
        threads.add(threadBuilder.start(runnable));
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
